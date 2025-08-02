<?php
include("database/conexao.php");
include("estilo/navBar.php");

$idJogo = $_GET['id'] ?? null;

if (!$idJogo) {
    echo "<p>Jogo não especificado.</p>";
    exit;
}

$query = "SELECT * FROM Jogo WHERE idJogo = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("i", $idJogo);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows === 0) {
    echo "<p>Jogo não encontrado.</p>";
    exit;
}

$jogo = $result->fetch_assoc();

$queryMedia = "SELECT AVG(nota) AS mediaNota FROM JogoUsuario WHERE idJogo = ? AND nota IS NOT NULL";
$stmtMedia = $conn->prepare($queryMedia);
$stmtMedia->bind_param("i", $idJogo);
$stmtMedia->execute();
$resultMedia = $stmtMedia->get_result();
$mediaRow = $resultMedia->fetch_assoc();
$mediaNotaCalculada = $mediaRow['mediaNota'] ? number_format($mediaRow['mediaNota'], 1, ',', '') : 'Sem avaliações';

$reviewUsuario = null;
$usuario = null;

if (isset($_GET['usuario'])) {
    $idUsuario = $_GET['usuario'];

    $queryReview = "SELECT review FROM JogoUsuario WHERE idUsuario = ? AND idJogo = ?";
    $stmt = $conn->prepare($queryReview);
    $stmt->bind_param("ii", $idUsuario, $idJogo);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($row = $result->fetch_assoc()) {
        $reviewUsuario = $row['review'];
    }

    $queryUsuario = "SELECT nomeUsuario FROM Usuario WHERE idUsuario = ?";
    $stmtUsuario = $conn->prepare($queryUsuario);
    $stmtUsuario->bind_param("i", $idUsuario);
    $stmtUsuario->execute();
    $resultUsuario = $stmtUsuario->get_result();

    if ($resultUsuario->num_rows > 0) {
        $usuario = $resultUsuario->fetch_assoc();
    }
}

?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <link rel="stylesheet" href="estilo/jogo.css">
  <meta charset="UTF-8">
  <title><?php echo htmlspecialchars($jogo['titulo']); ?></title>
  <style>
  </style>
</head>
<body>

<div class="jogo-container">
  <div class="jogo-header">
    <img class="jogo-capa" src="<?php echo htmlspecialchars($jogo['capa']); ?>" alt="Capa do jogo">
    <div class="jogo-info">
      <div class="titulo-nota">
        <h1><?php echo htmlspecialchars($jogo['titulo']); ?></h1>
        <div class="nota-box"><?php echo $mediaNotaCalculada; ?></div>
      </div>
      <p><?php echo nl2br(htmlspecialchars($jogo['resumo'])); ?></p>
    </div>
  </div>

<div class="info-lista">
  <div class="info-item"><strong>Gênero:</strong> <?php echo htmlspecialchars($jogo['genero']); ?></div>
  <div class="info-item"><strong>Desenvolvedora:</strong> <?php echo htmlspecialchars($jogo['desenvolvedora']); ?></div>
  <div class="info-item"><strong>Plataforma:</strong> <?php echo htmlspecialchars($jogo['plataforma']); ?></div>
  <div class="info-item"><strong>Lançamento:</strong> <?php echo date('d/m/Y', strtotime($jogo['dataLancamento'])); ?></div>
</div>
</div>

<?php if ($reviewUsuario !== null): ?>
  <div style="margin-top: 30px; margin-left: 500px; background-color: #F0A04B; padding: 20px; max-width: 800px;">
    <h2>Review de <?php echo htmlspecialchars($usuario['nomeUsuario']); ?></h2>
    <p><?php echo htmlspecialchars($reviewUsuario); ?></p>
  </div>
<?php endif; ?>


</body>
</html>