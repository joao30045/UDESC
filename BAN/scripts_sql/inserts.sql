-- Inserção

-- Clientes 

-- Pessoas Fisícas
INSERT INTO clientes (tipo, nome, datanascimento, sexo, email, telefone, rua, bairro, cep, cpf)
VALUES (1, 'Andre', '2001-4-06', 'Masculino', 'andre@email.com', '47999123', 'Imperador', 'Itaum', 111111000, '11111111000'),
(1, 'Joao', '2003-10-20', 'Masculino', 'joao@email.com', '47999321', 'Barra Velha', 'Floresta', 111111001, '11111111001'),
(1, 'Julia', '2001-7-22', 'Feminino', 'julio@email.com', '47999567', 'Santa Rosa', 'Itaum', 111111010, '11111111010'),
(1, 'Pedro', '2005-2-28', 'Masculino', 'pedro@email.com', '47999123', 'Sao Paulo', 'Floresta', 111111011, '11111111011'),
(1, 'Edson', '1970-3-20', 'Masculino', 'edson@email.com', '47999765', 'Pinheiro Preto', 'Escolinha', 111111100, '11111111100');

-- Pessoa Juridícas
INSERT INTO clientes (tipo, nome, email, telefone, rua, bairro, cep, cnpj)
VALUES (2, 'RoboCore', 'robocore@email.com', '47992123', 'Avenida 9 de Julho', 'Bela Vista', 111110001, '1111111111100'),
(2, 'ADS Robotics', 'adsrobotics@email.com', '47992321', 'Avenida Cruzeiro do Sul', 'Pinheiros', 111110011, '1111111111101'),
(2, 'Robotima Automação e Robótica Industrial', 'robotima@email.com', '47992456', 'Rua Oscar Freire', 'Moema', 111110111, '1111111111111');

-- Produto 
INSERT INTO Produtos (nome, descricao, precoUnitVenda, precoUnitCompra, quantidade,datasheet) 
VALUES ('Kit Arduino Maker', 'O Kit Arduino Maker, possui diversos componentes voltados para o mundo Maker. Este Kit é ideal para quem quer conhecer sobre o Arduino e possibilita a criação de vários projetos!', 303.90, 210.30, 200, 'https://conteudo.eletrogate.com/apostila-arduino-maker'),
('Uno R3 + Cabo Usb para Arduino', 'Arduino é uma plataforma open-hardware e possui seu próprio ambiente de desenvolvimento baseado na linguagem C, simplificando a programação para iniciantes.', 94.90, 70.20, 500, 'https://blog.eletrogate.com/arduino-primeiros-passos/'),
('Jumpers - Macho/Macho - 65 Unidades', 'Conjunto de 65 Jumpers/Fios para Protoboard, também conhecido como "fio" ou "cabo". Excelente para montagem de projetos eletrônicos com rapidez, agilidade e limpeza!', 12.90, 7.90, 1000, NULL),
('Led Vermelho', 'Led 5mm difuso vermelho', 0.24, 0.10, 2000, NULL),
('Potenciometro', 'Potenciômetro Linear 10KΩ', 2.50, 1.0, 1500, NULL),
('Display de 7 segmentos', 'Display de 7 segmentos com 1 Dígito (Anodo Comum)', 2.90, 1.5, 1200, NULL)

-- Kits
INSERT INTO Kits (codProduto, codKitProduto, nome, quantidadeProduto)
VALUES (1, 	2, 'kit teste', 1),
(1, 3, 'kit iniciante', 1),
(1, 4, 'kit avancado', 5),
(1, 5, 'kit para colegio', 2),
(1, 6, 'kit academico', 1);

-- Fornecedor
INSERT INTO Fornecedor (nome, email, cnpj)
VALUES ('Fornecedor1', 'fornecedor1@email.com' , '1111111110011'),
('Fornecedor12', 'fornecedor2@email.com', '1111111111011'),
('Fornecedor3', 'fornecedor13@email.com', '1111111101111');

-- Tranportadora
INSERT INTO Transportadora (nome, email, cnpj, custoKM)
VALUES ('Transportadora1', 'transportadora1@email.com', '1001111111111', 0.15),
('Transportadora2', 'transportadora2@email.com', '1011111111111', 0.18),
('Transportadora3', 'transportadora3@email.com', '1101111111111', 0.12);

-- Venda
INSERT INTO Venda (codcliente, codproduto, codtransportadora, quantidade, formapagamento, data)
VALUES (1, 1, 2, 2, 'pix', '2023-11-16'),
(1, 3, 2, 4, 'boleto', '2023-11-18'),
(3, 5, 1, 3, 'pix', '2023-11-20'),
(6, 1, 3, 50, 'boleto', '2023-11-21'),
(6, 4, 3, 100, 'boleto', '2023-11-28'),
(7, 6, 2, 30, 'pix', '2023-11-22'),
(6, 1, 3, 35, 'boleto, '2023-08-03'),
 (6, 1, 3, 50, 'boleto', '2023-05-03');

-- Compra
INSERT INTO Compra (codFornecedor, codProduto, codTransportadora, quantidade, data)
VALUES (1, 1, 2, 52, '2023-12-06'),
(2, 4, 1, 50, '2023-12-04');




