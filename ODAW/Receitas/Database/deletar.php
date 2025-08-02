<?php

$codigo = $_GET['codigo'];

$link = mysqli_connect("localhost", "root", "", "receitas");
//$link = mysqli_connect("localhost", "root", "udesc", "receitas");

$query = "DELETE FROM usuario WHERE codigo='$codigo'";
echo "DELETAR: $query<br><hr>";
mysqli_query($link, $query);
mysqli_close($link);

?>
