<?php

$codigo = $_POST['codigo'];
$nome = $_POST['nome'];
$dataNascimento = $_POST['dataNascimento'];
$email = $_POST['email'];
$nomeUsuario = $_POST['usuario'];
$senha = $_POST['senha'];

$link = mysqli_connect("localhost", "root", "", "receitas");
//$link = mysqli_connect("localhost", "root", "udesc", "receitas");

$query = "UPDATE usuario SET nome='$nome', dataNascimento='$dataNascimento', email='$email',  
usuario='$nomeUsuario', senha='$senha' WHERE codigo='$codigo'";
echo "UPDATE: $query<br><hr>";
mysqli_query($link, $query);
mysqli_close($link);
			
?>