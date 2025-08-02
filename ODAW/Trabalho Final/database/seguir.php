<?php
session_start();
include("conexao.php");

$idUsuarioLogado = $_SESSION['idUsuario'] ?? null;
$idUsuarioAlvo = $_POST['idUsuarioAlvo'] ?? null;

if (!$idUsuarioLogado || !$idUsuarioAlvo || $idUsuarioLogado == $idUsuarioAlvo) {
    header("Location: ../telaPerfil.php?idUsuario=$idUsuarioAlvo");
    exit;
}

$query = "SELECT 1 FROM Amigo WHERE idUsuario1 = ? AND idUsuario2 = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("ii", $idUsuarioLogado, $idUsuarioAlvo);
$stmt->execute();
$stmt->store_result();

if ($stmt->num_rows > 0) {
    $query = "DELETE FROM Amigo WHERE idUsuario1 = ? AND idUsuario2 = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("ii", $idUsuarioLogado, $idUsuarioAlvo);
    $stmt->execute();
} else {
    $query = "INSERT INTO Amigo (idUsuario1, idUsuario2) VALUES (?, ?)";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("ii", $idUsuarioLogado, $idUsuarioAlvo);
    $stmt->execute();
}

header("Location: ../telaPerfil.php?idUsuario=$idUsuarioAlvo");
exit;
