--1 Função para inserção de um mecânico.
CREATE OR REPLACE FUNCTION insere_mecanico(pcpf varchar, pnome varchar, pidade int, pendereco varchar, pcidade varchar, 
										   pfuncao varchar) RETURNS void AS
$$
	INSERT INTO mecanico (cpf, nome, idade, endereco, cidade, funcao) VALUES (pcpf, pnome, pidade, pendereco, pcidade, pfuncao)
$$
LANGUAGE sql;

SELECT insere_mecanico('1111111101', 'teste', 24, 'paulo', 'Joinville', 'som');

--2 Função para exclusão de um mecânico. 
CREATE OR REPLACE FUNCTION exclui_mecanico(pcodm int) RETURNS int AS
$$
DECLARE 
	linhas int DEFAULT 0;
BEGIN
	DELETE FROM mecanico WHERE codm = pcodm;
	GET DIAGNOSTICS linhas = ROW_COUNT;
RETURN linhas;
END;
$$
LANGUAGE plpgsql;

SELECT exclui_mecanico(10);

--3 Função única para inserção, atualizar e exclusão de um cliente.
CREATE OR REPLACE FUNCTION manipula_cliente (condicao varchar, pcodc int, pcpf varchar, pnome varchar, pidade int, pendereco varchar, pcidade varchar) 
	RETURNS void AS
$$
BEGIN 
	IF UPPER(condicao) = 'INSERT' THEN
		INSERT INTO cliente VALUES (pcodc, pcpf, pnome, pidade, pendereco, pcidade);
	ELSIF UPPER(condicao) = 'UPDATE' THEN
		UPDATE cliente SET
			cpf = pcpf,
			nome = pnome,
			idade = pidade,
			endereco = pendereco,
			cidade = pcidade 
		WHERE codc = pcodc;
	ELSIF UPPER(condicao) = 'DELETE' THEN
		DELETE FROM cliente WHERE codc = pcodc;
	ELSE
		RAISE EXCEPTION 'Comando invalido';
	END IF;
	RETURN;
END;
$$
LANGUAGE plpgsql;

SELECT manipula_cliente('INSERT', 10, '10000000011', 'Teste 10', 20, 'Rua Teste', 'Joinville');
SELECT manipula_cliente('UPDATE', 10, '10000000011', 'Teste 110', 20, 'Rua Teste 220', 'Joinville');
SELECT manipula_cliente('DELETE', 10, NULL, NULL, NULL, NULL, NULL);
SELECT manipula_cliente('atualiza', 10, '10000000011', 'Teste 110', 20, 'Rua Teste 220', 'Joinville');

--4 Função que limita o cadastro de no máximo 10 setores na oficina mecânica.
CREATE OR REPLACE FUNCTION limite_setor (pcods int, pnome varchar) RETURNS void AS
$$
DECLARE 
	soma int DEFAULT 0;
BEGIN
	SELECT COUNT(*) FROM setor INTO soma; 
	IF soma < 10 THEN
		INSERT INTO setor VALUES (pcods, pnome);
	ELSE 
		RAISE EXCEPTION 'Limite atingido';
	END IF;
	RETURN;
END;
$$
LANGUAGE plpgsql;

SELECT limite_setor(5, 'teste');
SELECT limite_setor(6, 'teste');
SELECT limite_setor(7, 'teste');
SELECT limite_setor(8, 'teste');
SELECT limite_setor(9, 'teste');
SELECT limite_setor(10, 'teste');
SELECT limite_setor(11, 'teste');

--5 Função que limita o cadastro de um conserto apenas se o mecânico não tiver mais de 3 consertos agendados para o mesmo dia.
CREATE OR REPLACE FUNCTION limite_conserto (pcodm int, pcodv int, pdata date, phora time without time zone) RETURNS void AS
$$
DECLARE
	soma int DEFAULT 0;
BEGIN
	SELECT COUNT(*) FROM conserto GROUP BY codm, data INTO soma;
	RAISE NOTICE 'Valor da soma: %', soma;
	IF soma < 3 THEN
		INSERT INTO conserto VALUES (pcodm, pcodv, pdata, phora);
	ELSE 
		RAISE EXCEPTION 'Limite atingido';
	END IF;
	RETURN;
END;
$$
LANGUAGE plpgsql;

SELECT * FROM conserto
SELECT limite_conserto(1, 2, '2014-06-12', '15:00:00');
SELECT limite_conserto(1, 3, '2014-06-12', '16:00:00');
SELECT limite_conserto(1, 6, '2014-06-12', '17:00:00');

--6 Função para calcular a média geral de idade dos Mecânicos e Clientes.
CREATE OR REPLACE FUNCTION media_geral() RETURNS float AS
$$
DECLARE 
	soma float DEFAULT 0;
    v_idade float DEFAULT 0;
	quant float DEFAULT 0;
BEGIN
	FOR v_idade IN SELECT idade FROM mecanico WHERE idade IS NOT NULL LOOP
	soma := soma + v_idade;
	quant := quant + 1;
	END LOOP;
	FOR v_idade IN SELECT idade FROM cliente WHERE idade IS NOT NULL LOOP
	soma := soma + v_idade;
	quant := quant + 1;
	END LOOP;
	RETURN ROUND((soma / quant)::numeric, 2);
END;
$$ 
LANGUAGE plpgsql;

SELECT media_geral();

--7 Função única que permita fazer a exclusão de um Setor, Mecânico, Cliente ou Veículo.
CREATE OR REPLACE FUNCTION exclusao (tabela varchar, id varchar, valor int) RETURNS int AS
$$
DECLARE 
	sql varchar;
	linhas int DEFAULT 0;
BEGIN
	sql := 'delete FROM '||tabela||' WHERE '||id||' = '||valor;
	RAISE NOTICE 'sql = %', sql;
	EXECUTE sql;
	GET DIAGNOSTICS linhas = ROW_COUNT;
	RETURN linhas;
END;
$$
LANGUAGE plpgsql;

SELECT exclusao('setor', 'cods', 1);

--8 Considerando que na tabela Cliente apenas codc é a chave primária, faça uma função que remova clientes com CPF repetido,
--deixando apenas um cadastro para cada CPF. Escolha o critério que preferir para definir qual cadastro será mantido: 
--aquele com a menor idade, que possuir mais consertos agendados, etc. Para testar a função, não se esqueça de inserir 
--na tabela alguns clientes com este problema.
CREATE OR REPLACE FUNCTION remover_cpf_repetido () RETURNS int AS
$$
DECLARE 
	vcodc int;
	cont int := 0;
	total_linhas int := 0;
	vcpf varchar(11);
	linhas int DEFAULT 0;
BEGIN
	FOR vcpf IN SELECT cpf FROM cliente GROUP BY cpf HAVING COUNT(*) > 1 LOOP
		cont := 0;
		FOR vcodc IN SELECT codc FROM cliente c WHERE c.cpf = vcpf ORDER BY idade LOOP
			IF cont > 0 THEN
				DELETE FROM cliente cli WHERE cli.codc = vcodc;
				GET DIAGNOSTICS linhas = ROW_COUNT;
				total_linhas := total_linhas + linhas;
			END IF;
			cont := cont + 1;
		END LOOP;
	END LOOP;
	RETURN total_linhas;
END;
$$
LANGUAGE plpgsql;

SELECT * FROM cliente;
SELECT manipula_cliente('INSERT', 8, '10000000011', 'Teste 10', 20, 'Rua Teste', 'Joinville');
SELECT manipula_cliente('INSERT', 9, '10000000011', 'Teste 11', 19, 'Rua Teste', 'Joinville');
SELECT manipula_cliente('INSERT', 10, '10000000011', 'Teste 12', 18, 'Rua Teste', 'Joinville');

SELECT remover_cpf_repetido();

--9 Função para calcular se o CPF é válido.
CREATE OR REPLACE FUNCTION validaCPF(pcpf char(11)) RETURNS boolean AS
$$
DECLARE
	cpf integer[11];
	sequencia1 integer[] DEFAULT array [10,9,8,7,6,5,4,3,2];
	sequencia2 integer[] DEFAULT array [11,10,9,8,7,6,5,4,3,2];
	pdigito integer[9];
	sdigito integer[10];
	somapdigito integer DEFAULT 0;
	somasdigito integer DEFAULT 0;
	cont integer DEFAULT 0;
	resto integer;
	digito integer;
BEGIN
	FOR cont IN 1..11 LOOP
		cpf[cont] := cast(substring(pcpf FROM cont FOR 1) AS integer);
	END LOOP;
	--Verifica 1 digito
	FOR cont in 1..9 LOOP
		pdigito[cont] := cpf[cont] * sequencia1[cont];
		somapdigito := somapdigito + pdigito[cont];
	END LOOP;
	resto := somapdigito % 11;
	IF resto < 2 THEN
		digito := 0;
	ELSE 
		digito := 11 - resto;
	END IF ;
	IF digito <> cpf[10] THEN
		RETURN false;
	END IF;
	-- Verifica 2 digito
	FOR cont IN 1..10 LOOP
		sdigito[cont] := cpf[cont] * sequencia2[cont];
		somasdigito := somasdigito + sdigito[cont];
	END LOOP;
	resto := somasdigito % 11;
	IF resto < 2 THEN 
		digito := 0;
	ELSE 
		digito := 11 - resto;
	END IF;
	IF digito <> cpf[11] THEN 
		RETURN false;
	END IF;
	RETURN true;
END;
$$
LANGUAGE plpgsql;

SELECT validaCPF('22233344405');
SELECT validaCPF('12345678911');

--10 Função que calcula a quantidade de horas extras de um mecânico em um mês de trabalho. O número de horas extras é calculado 
--a partir das horas que excedam as 160 horas de trabalho mensais. O número de horas mensais trabalhadas é calculada a partir 
--dos consertos realizados. Cada conserto tem a duração de 1 hora.
CREATE OR REPLACE FUNCTION horas_extra(vcodm int, vmes int, vano int) RETURNS int AS
$$
DECLARE
	horas int DEFAULT 0;
BEGIN
	SELECT COUNT(1) FROM conserto WHERE codm = vcodm AND date_part('month', data) = vmes AND date_part('year', data) = vano INTO horas;
	IF horas > 160 THEN
		RETURN (horas - 160);
	ELSE
		RAISE EXCEPTION 'Sem horas extras';
		RETURN 0;
	END IF;
END;
$$
LANGUAGE plpgsql;

SELECT horas_extra(3, 06, 2014);