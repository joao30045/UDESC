create database receitas;
use receitas;

create table usuario (
    codigo int auto_increment primary key, 
    nome char(50), 
    dataNascimento char(10), 
    email char(50), 
    nomeUsuario char(50), 
    senha char(50)
);