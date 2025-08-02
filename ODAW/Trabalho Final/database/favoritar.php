<?php
session_start();
include("conexao.php");

$idUsuario = $_POST['idUsuario'];
$idJogo = $_POST['idJogo'];
$ordem = $_POST['ordem'];

if (!$idUsuario || !$idJogo || !$ordem) {
    die("Dados inválidos");
}

$query = "SELECT * FROM Favorito WHERE idUsuario = ? AND ordem = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("ii", $idUsuario, $ordem);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $query = "UPDATE Favorito SET idJogo = ? WHERE idUsuario = ? AND ordem = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("iii", $idJogo, $idUsuario, $ordem);
} else {
    $query = "INSERT INTO Favorito (idUsuario, idJogo, ordem) VALUES (?, ?, ?)";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("iii", $idUsuario, $idJogo, $ordem);
}

if ($stmt->execute()) {
    header("Location: ../telaPerfil.php");
    exit;
} else {
    echo "Erro ao salvar favorito.";
}
?>
