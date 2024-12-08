-------------------------------------------------------------------
--Criação do Esquema de Dados Sistema de controle de universidade
-------------------------------------------------------------------
--Tabela Professor
CREATE TABLE professor(
	id_professor integer NOT NULL PRIMARY KEY,
	nome varchar NOT NULL,
	idade integer NOT NULL,
	sala varchar NOT NULL,
	especialidade varchar NOT NULL
);
CREATE SEQUENCE id_professor;

-------------------------------------------------------------------
--Tabela Departamento
CREATE TABLE departamento(
	id_departamento integer NOT NULL PRIMARY KEY,
	nome varchar NOT NULL,
	escritorio_principal varchar NOT NULL,
	lider integer NOT NULL,
	FOREIGN KEY (lider) REFERENCES professor(id_professor) ON UPDATE CASCADE
);
CREATE SEQUENCE id_departamento;

-------------------------------------------------------------------
--Tabela Estudante
CREATE TABLE estudante(
	id_estudante integer NOT NULL PRIMARY KEY,
	nome varchar NOT NULL,
	idade integer NOT NULL,
	tipo_do_curso varchar NOT NULL,
	depart integer NOT NULL,
	supervisor integer,
	aconselhador integer NOT NULL,
	FOREIGN KEY (depart) REFERENCES departamento(id_departamento) ON UPDATE CASCADE,
	FOREIGN KEY (supervisor) REFERENCES professor(id_professor) ON UPDATE CASCADE,
	FOREIGN KEY (aconselhador) REFERENCES estudante(id_estudante) ON UPDATE CASCADE
);
CREATE SEQUENCE id_estudante;

-------------------------------------------------------------------
--Tabela Projeto
CREATE TABLE projeto(
	id_projeto integer NOT NULL PRIMARY KEY,
	orgao_financiador varchar NOT NULL,
	data_inicio varchar NOT NULL,
	data_fim varchar NOT NULL,
	orcamento float NOT NULL,
	pesquisador_principal integer NOT NULL,
	FOREIGN KEY (pesquisador_principal) REFERENCES professor(id_professor) ON UPDATE CASCADE
);
CREATE SEQUENCE id_projeto;

-------------------------------------------------------------------
-- Tabela Trabalha
CREATE TABLE trabalha(
	numero_departamento integer NOT NULL,
	professor_trabalha integer NOT NULL,
	tempo float NOT NULL,
	PRIMARY KEY(numero_departamento,professor_trabalha),
	FOREIGN KEY (numero_departamento) REFERENCES departamento(id_departamento) ON UPDATE CASCADE,
	FOREIGN KEY (professor_trabalha) REFERENCES professor(id_professor) ON UPDATE CASCADE
);

-------------------------------------------------------------------
-- Tabela Participação 
CREATE TABLE participacao(
	assistentes_investigacao integer NOT NULL,
	numero_projeto integer NOT NULL,
	PRIMARY KEY(assistentes_investigacao,numero_projeto),
	FOREIGN KEY (assistentes_investigacao) REFERENCES estudante(id_estudante) ON UPDATE CASCADE,
	FOREIGN KEY (numero_projeto) REFERENCES projeto(id_projeto) ON UPDATE CASCADE
);

-------------------------------------------------------------------
--Dados
-------------------------------------------------------------------

INSERT INTO professor VALUES
	(1, 'Fabiano', 30, 'Sala F307', 'Banco de dados'),
	(2, 'Cristiano', 50, 'Sala F101', 'Compiladores');

-------------------------------------------------------------------

INSERT INTO departamento VALUES
	(1, 'Departamento 1', 'Sala 1', 1);

-------------------------------------------------------------------

INSERT INTO estudante VALUES 
	(1, 'João', 24, 'Bacharelado', 1, 1, 2),
	(2, 'Matheus', 21, 'Bacharelado', 1, NULL, 1);

-------------------------------------------------------------------

INSERT INTO projeto VALUES
	(1, 'Estado SC', '01-09-2024', '01-09-2025', 95000, 1);

-------------------------------------------------------------------

INSERT INTO trabalha VALUES 
	(1, 1, 30);

-------------------------------------------------------------------

INSERT INTO participacao VALUES 
	(1, 1);