<?php
session_start();
include("conexao.php");

if (!isset($_POST['idUsuario'], $_POST['idJogo'], $_POST['status'])) {
    echo "Dados incompletos.";
    exit;
}

$idUsuario = $_POST['idUsuario'];
$idJogo = $_POST['idJogo'];
$status = $_POST['status'];
$nota = $_POST['nota'] !== '' ? $_POST['nota'] : null;
$dataInicio = $_POST['dataInicio'] !== '' ? $_POST['dataInicio'] : null;
$dataFim = $_POST['dataFim'] !== '' ? $_POST['dataFim'] : null;
$platina = isset($_POST['platina']) ? 1 : 0;
$plataforma = $_POST['plataforma'] ?? null;
$review = $_POST['review'] ?? null;

$query = "UPDATE JogoUsuario 
          SET status = ?, nota = ?, review = ?, dataInicio = ?, dataFim = ?, platina = ?, plataforma = ?
          WHERE idUsuario = ? AND idJogo = ?";

$stmt = $conn->prepare($query);
$stmt->bind_param(
    "sisssiisi",
    $status,
    $nota,
    $review,
    $dataInicio,
    $dataFim,
    $platina,
    $plataforma,
    $idUsuario,
    $idJogo
);

if ($stmt->execute()) {
    header("Location: ../telaLista.php?id=$idUsuario");
    exit;
} else {
    echo "Erro ao atualizar: " . $stmt->error;
}
?>
