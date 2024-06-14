create table Filme(
    id int,
	titulo varchar(32),
    ano int,
    duracao int,
    genero varchar(32),
    descricao varchar(100),
    primary key (id)
);
create sequence id_filme;

create table Serie(
    id int,
	titulo varchar(32),
    ano int,
    temporada int,
    genero varchar(32),
    descricao varchar(100),
    primary key (id)
);
create sequence id_serie;

create table Episodio(
	id int,
	titulo varchar(32),
	numeroEpisodio int,
	numeroTemporada int,
	duracao int,
	descricao varchar(100),
	id_serie int,
	primary key (id),
	foreign key (id_serie) references serie
);
create sequence id_episodio;

create table Ator(
    id int,
    nome varchar(32),
    dataNascimento varchar(11),
    sexo varchar(32),
    primary key (id)
);
create sequence id_ator;

create table Usuario(
	id int,
	nome varchar(100),
	dataNascimento varchar(11),
	senha varchar(100),
	primary key (id)
);
create sequence id_usuario;