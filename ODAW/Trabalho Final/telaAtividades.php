<?php
session_start();
include("database/conexao.php");
include("estilo/navBar.php");
if (!isset($_SESSION['idUsuario'])) {
    header("Location: telaLogin.php");
    exit;
}

$idUsuario = $_SESSION['idUsuario'] ?? null;

if (!$idUsuario) {
    echo "<p>Usuário não identificado.</p>";
    exit;
}

$sql = "
SELECT 
    U.idUsuario,
    U.nomeUsuario,
    U.imagemPerfil,
    JU.nota,
    JU.review,
    JU.dataFim,
    JU.dataInicio,
    J.titulo,
    J.idJogo,
    J.capa
FROM Amigo A
JOIN Usuario U ON U.idUsuario = A.idUsuario2
JOIN JogoUsuario JU ON JU.idUsuario = U.idUsuario
JOIN Jogo J ON J.idJogo = JU.idJogo
WHERE A.idUsuario1 = ?
  AND (JU.nota IS NOT NULL OR JU.review IS NOT NULL OR JU.dataFim IS NOT NULL OR JU.dataInicio IS NOT NULL)
ORDER BY COALESCE(JU.dataFim, JU.dataInicio) DESC
";

$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $idUsuario);
$stmt->execute();
$result = $stmt->get_result();
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Atividades</title>
<link rel="stylesheet" href="estilo/atividades.css?=v3">
<style>
</style>
</head>
<body>
<div class="container">
    <h1 style="margin-top: 100px">Atividades recentes</h1>

    <?php while ($row = $result->fetch_assoc()): 
        $data = $row['dataFim'] ?? $row['dataInicio'];
        $dataFormatada = $data ? date('d/m/Y', strtotime($data)) : '';
    ?>
    <div class="atividade">
    <img src="<?= htmlspecialchars($row['imagemPerfil']) ?>" class="perfil" alt="Perfil">
    <div class="info">
        <div class="usuario">
            <a class="titulo-link" href="perfil.php?id=<?= $row['idUsuario'] ?>">
                <?= htmlspecialchars($row['nomeUsuario']) ?>
            </a> adicionou 
            <a class="titulo-link" href="telaJogo.php?id=<?= $row['idJogo'] ?>">
                <?= htmlspecialchars($row['titulo']) ?>
            </a> à lista
        </div>

        <?php if ($row['nota'] !== null): ?>
            <div class="nota">Nota: <?= $row['nota'] ?>/10</div>
        <?php endif; ?>

        <?php if (!empty($row['review'])): ?>
            <div class="review">
                <a class="titulo-link" href="telaJogo.php?id=<?= $row['idJogo']; ?>&usuario=<?= $row['idUsuario']; ?>">
                    Ver Review
                </a>
            </div>
        <?php endif; ?>

        <?php if (!empty($row['dataFim']) || !empty($row['dataInicio'])): ?>
            <?php
                $data = $row['dataFim'] ?? $row['dataInicio'];
                $dataFormatada = date('d/m/Y', strtotime($data));
            ?>
            <div class="data">Em: <?= $dataFormatada ?></div>
                <?php endif; ?>
            </div>
    </div>
    <?php endwhile; ?>
</div>
</body>
</html>

