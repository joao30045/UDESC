<?php
include("database/conexao.php");
include("estilo/navBar.php"); 

$sql = "
    SELECT j.titulo, j.capa, AVG(ju.nota) AS mediaNota 
    FROM 
        Jogo j
    JOIN 
        JogoUsuario ju ON j.idJogo = ju.idJogo
    WHERE 
        ju.nota IS NOT NULL
    GROUP BY 
        j.idJogo
    ORDER BY 
        mediaNota DESC
    LIMIT 10
";

$result = mysqli_query($conn, $sql);
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Ranking</title>
<link rel="stylesheet" href="estilo/ranking.css">
<style>
</style>
</head>
<body>

<div class="ranking-container">
    <h1 style="margin-top: 100px">Top 10 Jogos</h1>

    <?php
    if (mysqli_num_rows($result) > 0):
        $posicao = 1;
        while ($row = mysqli_fetch_assoc($result)):
    ?>
        <div class="jogo">
            <img src="<?= $row['capa'] ? $row['capa'] : 'imagens/placeholder.png' ?>" alt="Capa do jogo">
            <div class="jogo-info">
                <div class="titulo"><?= $posicao . ". " . htmlspecialchars($row['titulo']) ?></div>
                <div class="nota">Nota: <?= number_format($row['mediaNota'], 1, ',', '') ?></div>
            </div>
        </div>
    <?php
        $posicao++;
        endwhile;
    else:
        echo "<p>Nenhum jogo encontrado no ranking.</p>";
    endif;

    mysqli_close($conn);
    ?>
</div>

</body>
</html>