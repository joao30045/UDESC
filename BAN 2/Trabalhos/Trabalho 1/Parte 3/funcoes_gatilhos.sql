-- Função para calcular o tempo total de um professor.
CREATE OR REPLACE FUNCTION tempo_total_professor(prof_id int) RETURNS float AS 
$$
DECLARE
   tempo_total float DEFAULT 0;
BEGIN
   SELECT SUM(tempo) INTO tempo_total FROM trabalha WHERE professor_trabalha = prof_id;
   RETURN tempo_total;
END;
$$ 
LANGUAGE plpgsql;

-- Gatilho para impedir um estudante de ser seu próprio aconselhador.
CREATE OR REPLACE FUNCTION impedir_aconselhador() RETURNS TRIGGER AS
$$ 
BEGIN
	IF NEW.id_estudante = NEW.aconselhador THEN
		RAISE EXCEPTION 'O estudante não pode ser seu próprio aconselhador';
		RETURN OLD;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER impedir_aconselhador BEFORE INSERT OR UPDATE ON estudante FOR EACH ROW EXECUTE PROCEDURE impedir_aconselhador();

-- Gatilho para garantir um aconselhador ser mais velho.
CREATE OR REPLACE FUNCTION aconselhador_mais_velho() RETURNS TRIGGER AS
$$
DECLARE 
	idade_a int DEFAULT 0;
	idade_e int DEFAULT 0;
BEGIN
	idade_a := (SELECT idade FROM estudante WHERE new.aconselhador = id_estudante);
	idade_e := (NEW.idade);
	IF idade_a < idade_e THEN
		RAISE EXCEPTION 'O estudante precisa de um aconselhador mais velho';
		RETURN OLD;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER aconselhador_mais_velho BEFORE INSERT OR UPDATE ON estudante FOR EACH ROW EXECUTE PROCEDURE aconselhador_mais_velho();

-- Gatilho para garantir que só terá um professor gerenciando um projeto.
CREATE OR REPLACE FUNCTION verifica_gerenciador() RETURNS TRIGGER AS
$$
BEGIN
    IF (TG_OP = 'UPDATE' OR TG_OP = 'INSERT') THEN
        IF EXISTS (SELECT 1 FROM projeto WHERE id_projeto = NEW.id_projeto AND pesquisador_principal IS NOT DISTINCT FROM NEW.pesquisador_principal) THEN
            RAISE EXCEPTION 'Não é possível adicionar mais de um professor para gerenciar o projeto.';
        END IF;
    END IF;
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER verifica_gerenciador BEFORE INSERT OR UPDATE ON projeto FOR EACH ROW EXECUTE FUNCTION verifica_gerenciador();
