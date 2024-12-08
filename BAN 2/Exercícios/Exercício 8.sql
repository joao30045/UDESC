--1 Gatilho para impedir a inserção ou atualização de Clientes com o mesmo CPF.
--onde: cliente, quando: BEFORE, nivel: ROW LEVEL, operacoes: INSERT OR UPDATE
CREATE OR REPLACE FUNCTION valida_cliente() RETURNS TRIGGER AS 
$$
BEGIN 
	IF TG_OP = 'INSERT' THEN
		IF (SELECT 1 FROM cliente WHERE cpf = NEW.cpf) THEN 
			RAISE EXCEPTION 'Ja existe um cliente com esse cpf';
		END IF;
	ELSIF TG_OP = 'UPDATE' THEN
		IF (SELECT 1 FROM cliente WHERE cpf = NEW.cpf AND NEW.cpf <> OLD.cpf) THEN 
			RAISE EXCEPTION 'Ja existe um cliente com esse cpf';
		END IF;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER valida_cliente BEFORE INSERT OR UPDATE ON cliente FOR EACH ROW EXECUTE PROCEDURE valida_cliente();

INSERT INTO cliente VALUES (20, '11122233305', 'teste');
INSERT INTO cliente VALUES (21, '11122233305', 'teste');
UPDATE cliente SET endereco = 'Rua Lages' WHERE cpf = '11000110000';

--2 Gatilho para impedir a inserção ou atualização de Mecânicos com idade menor que 20 anos.
CREATE TABLE mecanico_teste
(
  codm serial NOT NULL PRIMARY KEY,
  cpf char(11),
  nome character varying(50),
  idade integer CHECK (idade > 20),
  endereco character varying(500),
  cidade character varying(500),
  funcao character varying(50),
  cods integer,
  FOREIGN KEY (cods) REFERENCES setor (cods) MATCH SIMPLE ON UPDATE CASCADE ON DELETE SET NULL
)
INSERT INTO mecanico_teste VALUES (30, '10000000111', 'João', 10, 'América', 'Joinville', 'som', 2);

--onde: mecanico, quando: BEFORE, nivel: ROW LEVEL, operacoes: INSERT e UPDATE
CREATE OR REPLACE FUNCTION verifica_idade() RETURNS TRIGGER AS
$$ 
BEGIN 
	IF NEW.idade < 20 THEN
		RAISE EXCEPTION 'Idade menor que a mínima';
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verifica_idade BEFORE INSERT OR UPDATE ON mecanico FOR EACH ROW EXECUTE PROCEDURE verifica_idade();

INSERT INTO mecanico VALUES (30, '10000000111', 'João', 10, 'América', 'Joinville', 'som', 2);

--3 Gatilho para atribuir um cods (sequencial) para um novo setor inserido.
CREATE SEQUENCE setor_cods_seq START 10;

CREATE OR REPLACE FUNCTION new_cods() RETURNS TRIGGER AS
$$
BEGIN
	NEW.cods := nextval('setor_cods_seq');
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER new_cods BEFORE INSERT ON setor FOR EACH ROW EXECUTE PROCEDURE new_cods();

INSERT INTO setor (nome) VALUES ('Teste 2');

--4 Gatilho para impedir a inserção de um mecânico ou cliente com CPF inválido.
--onde: mecanico, quando: BEFORE, nivel: ROW LEVEL, operacoes: INSERT e UPDATE
--onde: cliente, quando: BEFORE, nivel: ROW LEVEL, operacoes: INSERT e UPDATE
CREATE OR REPLACE FUNCTION verificacao_cpf() RETURNS TRIGGER AS
$$
BEGIN 
	IF NOT (validacpf(NEW.cpf)) THEN
		RAISE EXCEPTION 'CPF inválido';
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verificacao_cpf BEFORE INSERT OR UPDATE ON mecanico FOR EACH ROW EXECUTE PROCEDURE verificacao_cpf();

INSERT INTO mecanico VALUES (30, '10000000111', 'João', 20, 'América', 'Joinville', 'som', 2);

CREATE TRIGGER verificacao_cpf_cliente BEFORE INSERT OR UPDATE ON cliente FOR EACH ROW EXECUTE PROCEDURE verificacao_cpf();

INSERT INTO cliente VALUES (10, '10000000111', 'Ana Teste', 20, 'América', 'Joinville');

--5 Gatilho para impedir que um mecânico seja removido caso não exista outro mecânico com a mesma função.
--onde: mecanico, quando: BEFORE, nivel: ROW LEVEL, operacoes: DELETE e UPDATE
CREATE OR REPLACE FUNCTION mec_funcao() RETURNS TRIGGER AS
$$
BEGIN
	IF (TG_OP = 'DELETE') THEN
		IF (SELECT COUNT(*) FROM mecanico WHERE funcao ILIKE OLD.funcao) <= 1 THEN 
			RAISE EXCEPTION 'Proibido excluir mecanico';
		END IF;
		RETURN OLD;
	END IF;
	IF (TG_OP = 'UPDATE') THEN
		IF (SELECT COUNT(*) FROM mecanico WHERE funcao ILIKE OLD.funcao) <= 1 AND NEW.funcao <> OLD.funcao THEN
			RAISE EXCEPTION 'Proibido atualizar mecanico';
		END IF;
		RETURN NEW;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER mec_funcao BEFORE DELETE OR UPDATE ON mecanico FOR EACH ROW EXECUTE PROCEDURE mec_funcao();

DELETE FROM mecanico WHERE codm = 10;
UPDATE mecanico SET funcao = 'pintura' WHERE codm = 2;

--6 Gatilho que ao inserir, atualizar ou remover um mecânico, reflita as mesmas modificações na tabela de Cliente. Em caso de atualização, 
--se o mecânico ainda não existir na tabela de Cliente, deve ser inserido.
--onde: mecanico, quando: AFTER, nivel: ROW LEVEL, operacoes: INSERT, UPDATE e DELETE
CREATE SEQUENCE cliente_codc_seq START 20;

CREATE OR REPLACE FUNCTION atualiza_cliente() RETURNS TRIGGER AS
$$
BEGIN
	IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
		IF (SELECT 1 FROM cliente WHERE cpf = NEW.cpf) THEN
			UPDATE cliente SET
				nome = NEW.nome,
				idade = NEW.idade,
				endereco = NEW.endereco,
				cidade = NEW.cidade
			WHERE cpf = NEW.cpf;
		ELSE 
			INSERT INTO cliente VALUES (nextval('cliente_codc_seq'), NEW.cpf, NEW.nome, NEW.idade, NEW.endereco, NEW.cidade);
		END IF;
	ELSIF (TG_OP = 'DELETE') THEN
		IF (SELECT 1 FROM cliente WHERE cpf = NEW.cpf) THEN
			DELETE FROM cliente WHERE cpf = OLD.cpf;
		END IF;
	END IF;
	RETURN NULL;				
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER atualiza_cliente AFTER INSERT OR UPDATE OR DELETE ON mecanico FOR EACH ROW EXECUTE PROCEDURE atualiza_cliente();

SELECT * FROM cliente;
SELECT * FROM mecanico;
INSERT INTO mecanico VALUES (6, '12109112050', 'João da Silva', 20, 'Rua Ararangua', 'Joinville', 'Pintor', 1);
INSERT INTO mecanico VALUES (7, '88272685027', 'Silva', 32, 'Rua Ararangua', 'Joinville', 'Pintor', 1);
UPDATE mecanico SET nome = 'João Silva 2' WHERE codm = 6;

--7 Gatilho para impedir que um conserto seja inserido na tabela Conserto se o mecânico já realizou mais de 20 horas extras no mês.
--onde: conserto, quando: BEFORE, nivel: EACH ROW LEVEL, operacoes: INSERT e UPDATE
CREATE OR REPLACE FUNCTION verifica_horas_extras() RETURNS TRIGGER AS
$$
BEGIN
	IF horas_extra(NEW.codm, CAST(date_part('month', NEW.data) AS int), CAST(date_part('year', NEW.data) AS int)) > 10 THEN
		RAISE EXCEPTION 'O mecânico excedeu a quantidade de horas extras';
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER verifica_horas_extras BEFORE INSERT OR UPDATE ON conserto FOR EACH ROW EXECUTE PROCEDURE verifica_horas_extras();

INSERT INTO conserto VALUES
(1, 1, '14/06/2014', '07:00'),
(1, 4, '14/06/2014', '08:00'),
(1, 2, '14/06/2014', '09:00');

--8 Gatilho para impedir que mais de 1 conserto seja agendado no mesmo setor na mesma hora. 
--onde: conserto, quando: BEFORE, nivel: ROW LEVEL, operacoes: INSERT e UPDATE
CREATE OR REPLACE FUNCTION impedir_agendamento() RETURNS TRIGGER AS 
$$
DECLARE 
	alocado int DEFAULT 0;
	old_cods int DEFAULT 0;
BEGIN
	old_cods := (SELECT DISTINCT cods FROM conserto c JOIN mecanico m USING (codm) WHERE codm = new.codm);
	alocado := (SELECT COUNT(*) FROM conserto c JOIN mecanico m USING (codm) WHERE cods = pcods AND (data = new.data) AND 
				(hora = NEW.hora) GROUP BY (data, hora, cods));
	IF (TG_OP = 'INSERT') THEN
		IF alocado > 0 THEN
			RAISE EXCEPTION 'Setor já alocado para esta data';
		END IF;
	ELSIF
		(TG_OP = 'UPDATE') THEN
		IF alocado > 0 THEN 
			RAISE EXCEPTION 'Setor já alocado para esta data';
		END IF;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER impedir_agendamento BEFORE INSERT OR UPDATE ON conserto FOR EACH ROW EXECUTE PROCEDURE impedir_agendamento();