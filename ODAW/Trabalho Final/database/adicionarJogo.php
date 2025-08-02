<?php
session_start();
include("conexao.php");

$idUsuario = $_POST['idUsuario'] ?? null;
$idJogo = $_POST['idJogo'] ?? null;
$status = $_POST['status'] ?? null;
$nota = $_POST['nota'] ?? null;
$review = $_POST['review'] ?? null;
$dataInicio = !empty($_POST['dataInicio']) ? $_POST['dataInicio'] : null;
$dataFim = !empty($_POST['dataFim']) ? $_POST['dataFim'] : null;
$platina = isset($_POST['platina']) ? 1 : 0;  
$plataforma = $_POST['plataforma'] ?? null;

if (!$idUsuario || !$idJogo || !$status) {
    echo "Dados incompletos.";
    exit;
}

if ($nota !== null && $nota !== '' && !is_numeric($nota)) {
    echo "Nota inválida.";
    exit;
}

$query = "INSERT INTO JogoUsuario 
          (idUsuario, idJogo, status, nota, review, dataInicio, dataFim, platina, plataforma) 
          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
$stmt = $conn->prepare($query);
$stmt->bind_param("iisisssis", 
    $idUsuario, 
    $idJogo, 
    $status, 
    $nota, 
    $review, 
    $dataInicio, 
    $dataFim, 
    $platina, 
    $plataforma
);

if ($stmt->execute()) {
    header("Location: ../telaLista.php?id=$idUsuario");
    exit;
} else {
    echo "Erro ao adicionar jogo: " . $stmt->error;
}
?>
