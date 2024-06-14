create table Clientes(
    codCliente serial,
    email varchar(50) NOT NULL,
    telefone varchar(15) NOT NULL,
    nome varchar(50) NOT NULL,
    rua varchar(50) NOT NULL,
    cep int NOT NULL,
    bairro varchar(50) NOT NULL,
    tipo int NOT NULL,
    dataNascimento date,
    sexo varchar(20),
    cpf varchar(20),
    cnpj varchar(20),
    primary key (codCliente)
);

create table Produtos(
    codProduto serial, 
    precoUnitVenda DOUBLE PRECISION NOT NULL,
    precoUnitCompra DOUBLE PRECISION NOT NULL,
    descricao varchar(200) NOT NULL,
    quantidade int NOT NULL,
	nome varchar(50),
    datasheet varchar(1000),
    primary key (codProduto)
);

create table Kits(
    codKit serial,
    codKitProduto serial,
    codProduto serial,
	nome varchar(50),
    quantidadeProduto int NOT NULL,
    primary key (codKit),
    foreign key (codProduto) references Produtos,
    foreign key (codKitProduto) references Produtos
);

create table Fornecedor(
    codFornecedor serial,
    nome varchar(50) NOT NULL,
    cnpj varchar(20) NOT NULL,
    email varchar(50) NOT NULL,
    primary key (codFornecedor) 
);

create table Transportadora(
    codTransportadora serial,
    nome varchar(50) NOT NULL,
    cnpj varchar(20) NOT NULL,
    email varchar(50) NOT NULL,
    custoKM DOUBLE PRECISION NOT NULL,
    primary key (codTransportadora)
);

create table Venda(
    codVenda serial,
    codCliente serial, 
    codProduto serial,
    quantidade int NOT NULL,
    formaPagamento varchar(50) NOT NULL,
    codTransportadora serial,
    data date NOT NULL,
    primary key (codVenda),
    foreign key (codCliente) references Clientes,
    foreign key (codProduto) references Produtos,
    foreign key (codTransportadora) references Transportadora
);

create table Compra(
    codCompra serial,
    codFornecedor serial, 
    codProduto serial,
    quantidade int NOT NULL,
    codTransportadora serial,
    data date NOT NULL,
    primary key (codCompra),
    foreign key (codFornecedor) references Fornecedor,
    foreign key (codProduto) references Produtos,
    foreign key (codTransportadora) references Transportadora
);
