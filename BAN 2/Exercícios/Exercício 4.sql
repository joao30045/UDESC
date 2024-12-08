--1 Recupere o nome e o endereço de cada cliente
SELECT nome, endereco FROM cliente

--2 Recupere o nome e a função dos mecânicos que trabalham no setor número 2 (cods 2)
SELECT nome, funcao FROM mecanico WHERE cods = 2

--3 Recupere o CPF e o nome de todos os mecânicos que são clientes da oficina (utilize operação de conjuntos). 
SELECT cpf, nome FROM mecanico INTERSECT SELECT cpf, nome from cliente

SELECT c.cpf, c.nome FROM cliente c JOIN mecanico m ON (c.cpf = m.cpf)

--4 Recupere as cidades das quais os mecânicos e clientes são oriundos.
SELECT cidade FROM mecanico UNION SELECT cidade FROM cliente

--5 Recupere as marcas distintas dos veículos dos clientes que moram em Joinville.
SELECT DISTINCT marca FROM veiculo v, cliente c WHERE cidade = 'Joinville' AND c.codc = v.codc

SELECT DISTINCT v.marca FROM veiculo v JOIN cliente c USING (codc)

--6 Recupere as funções distintas dos mecânicos da oficina
SELECT DISTINCT funcao FROM mecanico

--7 Recupere todas as informações dos clientes que têm idade maior que 25 anos.
SELECT * FROM cliente WHERE idade > 25

--8 Recupere o CPF e o nome dos mecânicos que trabalham no setor de mecânica.
SELECT cpf, nome FROM mecanico WHERE cods = 2

SELECT m.cpf, m.nome FROM mecanico m JOIN setor s USING (cods) WHERE s.nome = 'Mecânica'

--9 Recupere o CPF e nome dos mecânicos que trabalharam no dia 13/06/2014.
SELECT DISTINCT cpf, nome FROM mecanico m, conserto c WHERE data = '13/06/2014' AND m.codm = c.codm

SELECT DISTINCT m.cpf, m.nome FROM mecanico m JOIN conserto c USING (codm) WHERE c.data = '13/06/2014'

--10 Recupere o nome do cliente, o modelo do seu veículo, o nome do mecânico e sua função para todos os consertos realizados (utilize join para realizar a junção).
SELECT DISTINCT c.nome as cliente, v.modelo, m.nome as mecanico, m.funcao FROM cliente c JOIN veiculo v USING (codc) JOIN conserto co USING (codv) JOIN mecanico m USING (codm)

--11 Recupere o nome do mecânico, o nome do cliente e a hora do conserto para as serviços realizados no dia 19/06/2014 (utilize join para realizar a junção).
SELECT m.nome as mecanico, c.nome as cliente, co.hora FROM cliente c JOIN veiculo v USING (codc) JOIN conserto co USING (codv) JOIN mecanico m USING (codm) WHERE co.data = '19/06/2014' 

--12 Recupere o código e o nome dos setores que foram utilizados entre os dias 12/06/2014 e 14/06/2014 (utilize join para realizar a junção).
SELECT DISTINCT s.cods, s.nome FROM setor s JOIN mecanico m USING (cods) JOIN conserto c USING (codm) WHERE c.data BETWEEN '12/06/2014' AND '14/06/2014'