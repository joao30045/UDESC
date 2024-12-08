create table cliente (
	id_cliente int primary key,
	rua varchar(100),
	cidade varchar(50),
	numero int,
	nome varchar(50),
	sobrenome varchar(50),
	num_socio varchar(20)
)

create table telefone (
	id_telefone int primary key,
	telefone varchar(15),
	id_cliente int not null,
	foreign key (id_cliente) references cliente on delete cascade on update cascade
)

create table emprestimo (
	id_emprestimo int primary key,
	data_emprestimo date,
	data_devolucao date,
	id_cliente int not null,
	foreign key (id_cliente) references cliente on delete restrict on update cascade
)

create table item (
	codigo int primary key,
	locado boolean,
	tipo varchar(10)
)

create table fita (
	codigo int primary key,
	parte int,
	id_filme int,
	foreign key (codigo) references item on delete cascade on update cascade
)

create table vinil (
	codigo int primary key,
	num_musicas int,
	id_artista int,
	foreign key (codigo) references item on delete cascade on update cascade
)

create table empresta (
	id_empresta int primary key,
	id_emprestimo int not null,
	codigo int not null,
	foreign key (id_emprestimo) references emprestimo,
	foreign key (codigo) references emprestimo
)

create table artista (
	id_artista int primary key,
	nome varchar(70),
	nacionalidade varchar(50),
	codigo int not null,
	foreign key (codigo) references item on delete restrict on update cascade
)

create table estilo (
	id_estilo int primary key,
	nome varchar(70),
	id_artista int,
	foreign key (id_artista) references artista on delete restrict on update restrict
)

create table filme (
	id_filme int primary key,
	titulo varchar(70),
	codigo int not null,
	foreign key (codigo) references item on delete cascade on update cascade
)

create table categoria (
	id_categoria int primary key,
	nome varchar(50),
	id_filme int,
	foreign key (id_filme) references filme on delete restrict on update cascade
)

create table estrela (
	id_estrela int primary key,
	nome varchar(70),
	data_nascimento date,
	id_filme int not null,
	foreign key (id_filme) references filme on delete restrict on update cascade
)










