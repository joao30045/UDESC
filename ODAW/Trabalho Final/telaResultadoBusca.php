<?php
include("estilo/navBar.php"); 
include("database/conexao.php");

$q = isset($_GET['q']) ? trim($_GET['q']) : '';

?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Busca</title>
<style>
  .section {
    margin-top: 100px;
    margin-left: 300px;
  }
  .capa-busca {
  width: 80px;
  height: auto;
  margin-top: 10px;
  margin-right: 10px;
  vertical-align: middle;
}
  .titulo-link {
    text-decoration: none;
    color: #000;
    font-weight: bold;
  }

  .titulo-link:hover {
    text-decoration: underline;
    color: #F0A04B;
  }
</style>
</head>
<body>

  <div class="section">
    <h2>Resultados para: <em><?= htmlspecialchars($q) ?></em></h2>

    <?php if ($q === ''): ?>
      <p>Nenhum termo foi informado.</p>
    <?php else: ?>

    <h3>Jogos encontrados</h3>
    <?php
    $stmt = $conn->prepare("SELECT idJogo, titulo, dataLancamento, capa FROM Jogo WHERE titulo LIKE ?");
    $like = "%$q%";
    $stmt->bind_param("s", $like);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0):
      while ($row = $result->fetch_assoc()):
    ?>
        <div class="result-item">
          <img src="<?= htmlspecialchars($row['capa']) ?>" class="capa-busca" alt="Capa">
          <a href="telaJogo.php?id=<?= $row['idJogo'] ?>" class="titulo-link">
            <?= htmlspecialchars($row['titulo']) ?>
          </a>
          <?php if (!empty($row['dataLancamento'])): ?>
            (<?= date('d/m/Y', strtotime($row['dataLancamento'])) ?>)
          <?php endif; ?>
        </div>
    <?php
      endwhile;
    else:
      echo "<p>Nenhum jogo encontrado.</p>";
    endif;
    $stmt->close();
    ?>
  </div>

  <div class="section">
    <h3>Usuários encontrados</h3>
    <?php
    $stmt = $conn->prepare("SELECT idUsuario, nomeUsuario, imagemPerfil FROM Usuario WHERE nomeUsuario LIKE ?");
    $stmt->bind_param("s", $like);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0):
      while ($row = $result->fetch_assoc()):
    ?>
        <div class="result-item">
          <img src="<?= htmlspecialchars($row['imagemPerfil']) ?>" class="capa-busca" alt="Capa">
            <a href="telaPerfil.php?idUsuario=<?= $row['idUsuario'] ?>" class="titulo-link">
              <?= htmlspecialchars($row['nomeUsuario']) ?>
            </a>
        </div>
    <?php
      endwhile;
    else:
      echo "<p>Nenhum usuário encontrado.</p>";
    endif;
    $stmt->close();
    ?>
  </div>

<?php endif; ?>

</body>
</html>