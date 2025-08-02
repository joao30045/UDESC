<?php


$nome = $_POST['nome'];
$dataNascimento = $_POST['dataNascimento'];
$email = $_POST['email'];
$nomeUsuario = $_POST['usuario'];
$senha = $_POST['senha'];

$link = mysqli_connect("localhost", "root", "", "receitas");
//$link = mysqli_connect("localhost", "root", "udesc", "receitas");

$query = "INSERT INTO usuario (nome, dataNascimento, email, nomeUsuario, senha) 
VALUES ('$nome', '$dataNascimento', '$email', '$nomeUsuario', '$senha')";
echo "INSERT: $query<br><hr>";
mysqli_query($link, $query);
mysqli_close($link);
			
?>
