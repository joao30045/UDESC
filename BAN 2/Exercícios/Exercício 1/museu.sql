create table objetosdearte (
	num_id int primary key,
	ano int,
	titulo varchar(70),
	descricao varchar(150),
	origem varchar(50),
	periodo varchar(50),
	tipo varchar(20),
	estado varchar(20)
)

create table artista (
	id_artista int primary key,
	nome varchar(50),
	data_nascimento date,
	data_morte date,
	pais_origem varchar(50),
	periodo varchar(50),
	estilo_principal varchar(50),
	num_id int not null,
	foreign key(num_id) references objetosdearte on delete cascade on update cascade
)

create table pintura (
	num_id int primary key,
	tipo_tinta varchar(50),
	suporte varchar(50),
	estilo varchar(50),
	foreign key (num_id) references objetosdearte on delete cascade on update cascade
)

create table obra (
	num_id int primary key,
	estilo varchar(50),
	material varchar(50),
	altura int,
	peso int,
	tipo varchar(50),
	foreign key (num_id) references objetosdearte on delete cascade on update cascade
)

create table escultura (
	num_id int primary key,
	foreign key (num_id) references obra on delete cascade on update cascade
)

create table estatuaria (
	num_id int primary key,
	foreign key (num_id) references obra on delete cascade on update cascade
)

create table outros (
	num_id int primary key,
	tipo varchar(50),
	estilo varchar(50),
	foreign key (num_id) references objetosdearte on delete cascade on update cascade
)

create table colecaopermanente (
	num_id int primary key,
	data_aquisicao date,
	exposicao boolean,
	custo int,
	foreign key (num_id) references objetosdearte on delete cascade on update cascade
)

create table exposicoes (
	id_exposicao int primary key,
	nome varchar(50),
	data_inicio date,
	data_final date,
	num_id int not null,
	foreign key (num_id) references colecaopermanente on delete restrict on update cascade
)

create table emprestado (
	num_id int primary key,
	colecao varchar(50),
	data_emprestimo date,
	data_devolucao date,
	foreign key (num_id) references objetosdearte on delete cascade on update cascade
)

create table colecoes (
	nome varchar(50) primary key,
	tipo varchar(50),
	descricao varchar(150),
	rua varchar(50),
	numero int,
	cidade varchar(50),
	pessoa_contato varchar(50)
)

create table telefones (
	id_telefone int primary key,
	telefone varchar(50),
	nome varchar(50) not null,
	foreign key (nome) references colecoes on delete restrict on update cascade
)




