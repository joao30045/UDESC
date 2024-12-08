--1 Recupere o CPF e o nome dos mecânicos que trabalham nos setores número 1 e 2 (faça a consulta utilizado a cláusula IN).
SELECT cpf, nome, cods FROM mecanico WHERE cods IN (1,2)

--2  Recupere o CPF e o nome dos mecânicos que trabalham nos setores 'Funilaria' e 'Pintura' (faça a consulta utilizando sub-consultas aninhadas).
SELECT nome, cpf, cods FROM mecanico WHERE cods IN (SELECT cods FROM setor WHERE nome IN ('Funilaria', 'Pintura'))
		  		   
--3 Recupere o CPF e nome dos mecânicos que atenderam no dia 13/06/2014 (faça a consulta usando INNER JOIN).
SELECT DISTINCT m.cpf, m.nome FROM mecanico m JOIN conserto c USING (codm) WHERE c.data = '13/06/2014'

--4 Recupere o nome do mecânico, o nome do cliente e a hora do conserto para os consertos realizados no dia 12/06/2014 (faça a consulta usando INNER JOIN).
SELECT m.nome, c.nome, con.hora FROM mecanico m JOIN conserto con USING (codm) JOIN veiculo v USING (codv) JOIN cliente c USING (codc) WHERE con.data = '12/06/2014'

--5 Recupere o nome e a função de todos os mecânicos, e o número e o nome dos setores para os mecânicos que tenham essa informação.
SELECT m.nome, m.funcao, s.nome, s.cods FROM mecanico m FULL JOIN setor s USING (cods)

--6 Recupere o nome de todos os mecânicos, e as datas dos consertos para os mecânicos que têm consertos feitos (deve aparecer apenas um registro de nome de mecânico
-- para cada data de conserto).
SELECT DISTINCT m.nome, c1.data FROM mecanico m INNER JOIN conserto c1 USING (codm)

--7 Recupere a média da quilometragem de todos os veículos dos clientes.
SELECT round(avg(quilometragem)::numeric, 2) FROM veiculo

--8 Recupere a soma da quilometragem dos veículos de cada cidade onde residem seus proprietários.
SELECT c.cidade, sum(v.quilometragem) FROM veiculo v JOIN cliente c USING (codc) GROUP BY c.cidade

--9 Recupere a quantidade de consertos feitos por cada mecânico durante o período de 12/06/2014 até 19/06/2014.
SELECT m.cpf, m.nome, count(*) AS consertos FROM mecanico m INNER JOIN conserto c USING (codm) WHERE c.data BETWEEN '12/06/2014' AND '19/06/2014' GROUP BY codm

--10 Recupere a quantidade de consertos feitos agrupada pela marca do veículo.
SELECT v.marca, count(*) FROM veiculo v JOIN conserto c USING (codv) GROUP BY v.marca

--11 Recupere o modelo, a marca e o ano dos veículos que têm quilometragem maior que a média de quilometragem de todos os veículos.
SELECT v.modelo, v.marca, v.ano, v.quilometragem FROM veiculo v WHERE v.quilometragem > (SELECT round(avg(quilometragem)::numeric, 2) FROM veiculo)

--12 Recupere o nome dos mecânicos que têm mais de um conserto marcado para o mesmo dia.
SELECT m.nome, c.data, count(*) FROM mecanico m JOIN conserto c USING (codm) GROUP BY m.nome, c.data HAVING count(*) > 1