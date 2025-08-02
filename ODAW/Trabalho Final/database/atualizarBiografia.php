<?php
session_start();
include("conexao.php");

if (!isset($_SESSION['idUsuario'])) {
    header("Location: telaLogin.php");
    exit;
}

$idUsuario = $_SESSION['idUsuario'];
$biografia = trim($_POST['biografia'] ?? '');

$query = "UPDATE Usuario SET biografia = ? WHERE idUsuario = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("si", $biografia, $idUsuario);

if ($stmt->execute()) {
    header("Location: ../telaPerfil.php");
    exit;
} else {
    echo "Erro ao atualizar a biografia.";
}
