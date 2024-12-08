CREATE TYPE t_veterinario AS(
	crm int,
	nome varchar(50),
	convenio varchar(50)
);

CREATE TABLE veterinario OF t_veterinario(
	PRIMARY KEY(crm)	
);

CREATE TYPE t_animal AS(
	id_animal int,
	nome varchar(50),
	raca varchar(50),
	sexo varchar(50)
);

CREATE TABLE animal OF t_animal(
	PRIMARY KEY(id_animal)
);

CREATE TYPE t_tratamento AS(
	data date,
	descricao varchar(200)
);

CREATE TABLE consulta(
	crm int,
	id_animal int,
	data date,
	resultado varchar(200),
	consequencia t_tratamento[],
	PRIMARY KEY(crm, id_animal, data),
	FOREIGN KEY(crm) REFERENCES veterinario (crm),
	FOREIGN KEY(id_animal) REFERENCES animal (id_animal)
);

CREATE TABLE matriz(
	PRIMARY KEY(id_animal)
)INHERITS(animal);

CREATE TABLE reprodutor(
	PRIMARY KEY(id_animal)
)INHERITS(animal);

CREATE TABLE cobertura(
	id_matriz int,
	id_reprodutor int,
	PRIMARY KEY(id_matriz, id_reprodutor),
	FOREIGN KEY(id_matriz) REFERENCES matriz (id_animal),
	FOREIGN KEY(id_reprodutor) REFERENCES reprodutor (id_animal)
);

CREATE TABLE adquirido(
	nome_pai varchar(50),
	nome_mae varchar(50),
	valor float,
	PRIMARY KEY(id_animal)
)INHERITS(reprodutor);

CREATE TABLE cria(
	data_desmame date,
	PRIMARY KEY(id_animal)
)INHERITS(reprodutor);

CREATE TABLE sequencia(
	crm_ant int,
	id_animal_ant int,
	data_ant date,
	crm_dep int,
	id_animal_dep int,
	data_dep date,
	PRIMARY KEY(crm_ant, id_animal_ant, data_ant, crm_dep, id_animal_dep, data_dep),
	FOREIGN KEY(crm_ant, id_animal_ant, data_ant) REFERENCES consulta (crm, id_animal, data),
	FOREIGN KEY(crm_dep, id_animal_dep, data_dep) REFERENCES consulta (crm, id_animal, data)
);






