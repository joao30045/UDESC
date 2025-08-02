<?php
$conn = mysqli_connect("localhost", "root", "", "GameLog");
//$conn = mysqli_connect("localhost", "root", "udesc", "GameLog");

if ($conn->connect_error) {
    die("Falha na conexão: " . $conn->connect_error);
}
?>
