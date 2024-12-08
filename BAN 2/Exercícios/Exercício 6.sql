--1 Mostre o nome e a função dos mecânicos.
CREATE VIEW nome_funcao (mec_nome, mec_funcao) AS 
SELECT m.nome, m.funcao FROM mecanico m

SELECT * FROM nome_funcao
-- Exemplo de atualização
SELECT nextval('mecanico_codm_seq')
INSERT INTO nome_funcao VALUES ('Mecanico Teste', 'Funcao TESTE')

--2 Mostre o modelo e a marca dos veículos dos clientes.
CREATE VIEW cliente_veiculo AS
SELECT c.nome, v.modelo, v.marca FROM cliente c JOIN veiculo v USING (codc)

SELECT * FROM cliente_veiculo

--3 Mostre o nome dos mecânicos, o nome dos clientes, o modelo dos veículos e a data e hora dos consertos realizados.
CREATE VIEW mec_con_veiculo (mec_nome, cli_nome, modelo, data, hora) AS
SELECT m.nome, c.nome, v.modelo, con.data, con.hora FROM mecanico m JOIN conserto con USING (codm) JOIN veiculo v USING (codv) JOIN cliente c 
	USING (codc)

SELECT * FROM mec_con_veiculo

--4 Mostre o ano dos veículos e a média de quilometragem para cada ano.
CREATE VIEW veiculo_ano (ano, mediakm) AS
SELECT v.ano, AVG(v.quilometragem) FROM veiculo v GROUP BY v.ano 

SELECT * FROM veiculo_ano

--5 Mostre o nome dos mecânicos e o total de consertos feitos por um mecânico em cada dia.
CREATE VIEW mec_consertos_dia(nome, data, n_consertos) AS 
SELECT m.nome, c1.data, COUNT(*) FROM mecanico m JOIN conserto c1 USING (codm) GROUP BY m.nome, c1.data

SELECT * FROM mec_consertos_dia

--6 Mostre o nome dos setores e o total de consertos feitos em um setor em cada dia.
CREATE VIEW setor_consertos_data (nome_setor, data, n_consertos) AS
SELECT s.nome, c.data, COUNT(*) FROM conserto c JOIN mecanico m USING (codm) JOIN setor s USING (cods) GROUP BY s.nome, c.data

SELECT * FROM setor_consertos_data 

--7 Mostre o nome dAS funções e o número de mecânicos que têm uma destAS funções.
CREATE VIEW mec_por_setor(nome_set, quant_mec) AS
SELECT s.nome, COUNT(*) FROM setor s JOIN mecanico m USING (cods) GROUP BY s.nome

SELECT * FROM mec_por_setor

--8 Mostre o nome dos mecânicos e suAS funções e, para os mecânicos que estejam alocados a um setor, informe também o número e nome do setor.
CREATE VIEW mec_setor (mec_nome, mec_funcao, set_cods, set_nome) AS
SELECT m.nome, m.funcao, s.cods, s.nome FROM mecanico m LEFT JOIN setor s USING (cods)

SELECT * FROM mec_setor

--9 Mostre o nome dAS funções dos mecânicos e a quantidade de consertos feitos agrupado por cada função.
--Adicional na questão: para AS funções que tem mais de um conserto
CREATE VIEW quant_consertos AS
SELECT m.funcao, COUNT(*) AS consertos FROM mecanico m JOIN conserto c USING (codm) GROUP BY m.funcao HAVING COUNT(*) > 1

SELECT * FROM quant_consertos
--Para retornar mais de 2 consertos
WHERE consertos > 2