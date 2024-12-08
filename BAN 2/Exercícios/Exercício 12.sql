--1 - Crie um índice para cada uma das chaves estrangeiras presentes do esquema de dados.
CREATE INDEX idx_mec_cods ON mecanico USING hash(cods); 
CREATE INDEX idx_vei_cli ON veiculo USING hash(codc);
CREATE INDEX idx_con_vei ON conserto USING hash(codv);
CREATE INDEX idx_con_mec ON conserto USING hash(codm);

--2 - Crie um índice para acelerar a busca dos mecânicos pela função.
SELECT * FROM mecanico WHERE funcao ILIKE 'Motor';

CREATE INDEX idx_mec_funcao ON mecanico USING btree(funcao);

--3 - Crie um índice para acelerar a ordenação dos consertos pela data e hora.
CREATE INDEX idx_con_data_hora ON conserto USING btree(data, hora);

--4 - Crie um índice para acelerar a busca de clientes pelo cpf.
CREATE INDEX idx_cli_cpf ON cliente USING btree(cpf);

--5 - Crie um índice para acelerar a busca pelas primeiras 5 letras do nome dos clientes.
CREATE INDEX idx_nome_cli ON cliente USING btree(substr(nome, 1, 5));

--6 - Crie um índice para acelerar a busca dos clientes com CPF com final XXX.XXX.XXX-55.
CREATE INDEX idx_cli_cpf_55 ON cliente USING btree(cpf) WHERE cpf LIKE '%55';