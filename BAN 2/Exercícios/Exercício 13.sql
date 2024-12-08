-- 1 - Recupere o CPF e o nome dos mecânicos que trabalham nos setores maiores que 100 e menores que 200.
EXPLAIN ANALYSE SELECT cpf, m.nome FROM mecanico m, setor s WHERE m.cods = s.cods AND s.cods BETWEEN 100 AND 200;
"Planning Time: 0.156 ms"
"Execution Time: 1.246 ms"
EXPLAIN ANALYSE SELECT cpf, m.nome FROM mecanico m WHERE cods BETWEEN 100 AND 200;
"Planning Time: 0.072 ms"
"Execution Time: 0.873 ms"

-- 2 - Recupere o CPF e nome dos mecânicos que atenderam no dia 13/06/2018.
EXPLAIN ANALYSE SELECT m.cpf, m.nome FROM mecanico m JOIN conserto c USING(codm) WHERE c.data = '2018-08-13';
"Planning Time: 0.399 ms"
"Execution Time: 1.579 ms"

CREATE INDEX idx_con_data ON conserto USING btree(data);

EXPLAIN ANALYSE SELECT m.cpf, m.nome FROM mecanico m JOIN conserto c USING(codm) WHERE c.data = '2018-08-13';
"Planning Time: 0.421 ms"
"Execution Time: 0.282 ms"

-- 3 - Recupere o nome do mecânico, o nome do cliente e a hora do conserto para os consertos realizados de 12/06/2018 à 25/09/2018.
EXPLAIN ANALYSE SELECT m.nome, c.nome, con.data, con.hora FROM mecanico m JOIN conserto con USING(codm) JOIN veiculo v USING(codv) JOIN cliente c USING(codc) 
WHERE con.data BETWEEN '12/06/2018' AND '25/09/2018';
"Planning Time: 0.415 ms"
"Execution Time: 7.138 ms"

CREATE INDEX idx_con_data ON conserto USING btree(data);

EXPLAIN ANALYSE SELECT m.nome, c.nome, con.data, con.hora FROM mecanico m JOIN conserto con USING(codm) JOIN veiculo v USING(codv) JOIN cliente c USING(codc) 
WHERE con.data BETWEEN '12/06/2018' AND '25/09/2018';
"Planning Time: 0.388 ms"
"Execution Time: 7.270 ms"

-- 4 - Recupere o nome e a função de todos os mecânicos, e o número e o nome dos setores para os mecânicos que tenham essa informação.
EXPLAIN ANALYSE SELECT m.nome, m.funcao, s.cods, s.nome FROM mecanico m LEFT JOIN setor s USING(cods);
"Planning Time: 0.119 ms"
"Execution Time: 3.749 ms"

CREATE INDEX idx_mec_cods ON mecanico USING hash(cods);

EXPLAIN ANALYSE SELECT m.nome, m.funcao, s.cods, s.nome FROM mecanico m LEFT JOIN setor s USING(cods);
"Planning Time: 0.122 ms"
"Execution Time: 3.691 ms"

-- 5 - Recupere o nome de todos os mecânicos, e as datas dos consertos para os mecânicos que têm consertos feitos (deve aparecer apenas 
-- um registro de nome de mecânico para cada data de conserto).
EXPLAIN ANALYSE SELECT m.nome, c.data FROM mecanico m, conserto c WHERE m.codm = c.codm;
"Planning Time: 0.257 ms"
"Execution Time: 7.089 ms"

-- 6 - Recupere a média da quilometragem de todos os veículos de cada cliente, ordenando da maior KM para o menor.
EXPLAIN ANALYSE SELECT codc, count(*), avg(quilometragem) AS km FROM veiculo GROUP BY codc ORDER BY km DESC;
"Planning Time: 0.065 ms"
"Execution Time: 6.630 ms"

CREATE INDEX idx_vei_km ON veiculo USING btree(quilometragem);

EXPLAIN ANALYSE SELECT codc, count(*), avg(quilometragem) AS km FROM veiculo GROUP BY codc ORDER BY km DESC;
"Planning Time: 0.065 ms"
"Execution Time: 6.183 ms"

-- 7 - Recupere a soma da quilometragem dos veículos de cada cidade onde residem seus proprietários.
EXPLAIN ANALYSE SELECT c.cidade, sum(v.quilometragem) FROM cliente c, veiculo v WHERE c.codc = v.codc GROUP BY c.cidade;
"Planning Time: 0.318 ms"
"Execution Time: 14.075 ms"

CREATE INDEX idx_cli_cidade ON cliente USING btree(cidade);
CREATE INDEX idx_vei_km ON veiculo USING btree(quilometragem);

EXPLAIN ANALYSE SELECT c.cidade, SUM(v.quilometragem) FROM cliente c, veiculo v WHERE c.codc = v.codc GROUP BY c.cidade;
"Planning Time: 0.199 ms"
"Execution Time: 12.034 ms"

-- 8 - Recupere a quantidade de consertos feitos por cada mecânico durante o período de 12/06/2018 até 19/10/2018
EXPLAIN ANALYSE SELECT codm, COUNT(data) FROM conserto WHERE data BETWEEN '2018-06-12' AND '2018-10-19' GROUP BY codm;
"Planning Time: 0.170 ms"
"Execution Time: 5.563 ms"

DROP INDEX idx_con_data ON conserto USING btree(data);

EXPLAIN ANALYSE SELECT codm, COUNT(data) FROM conserto WHERE data BETWEEN '2018-06-12' AND '2018-10-19' GROUP BY codm;
"Planning Time: 0.149 ms"
"Execution Time: 2.618 ms"

-- 9 - Recupere a quantidade de consertos feitos agrupada pela marca do veículo.
EXPLAIN ANALYSE SELECT v.marca, COUNT(data) FROM veiculo v, conserto c WHERE v.codv = c.codv GROUP BY v.marca;
"Planning Time: 0.369 ms"
"Execution Time: 15.960 ms"

-- 10 - Recupere o modelo, a marca e o ano dos veículos que têm quilometragem maior que a média de quilometragem de todos os veículos.
EXPLAIN ANALYSE SELECT modelo, marca, ano FROM veiculo WHERE quilometragem > (SELECT AVG(quilometragem) FROM veiculo);
"Planning Time: 0.195 ms"
"Execution Time: 3.746 ms"

-- 11 - Recupere o nome dos mecânicos que têm mais de um conserto marcado para o mesmo dia.
EXPLAIN ANALYSE SELECT m.nome FROM mecanico m, conserto c WHERE m.codm = c.codm GROUP BY (m.codm, c.data) HAVING COUNT(c.data) > 1;
"Planning Time: 0.387 ms"
"Execution Time: 18.333 ms"
