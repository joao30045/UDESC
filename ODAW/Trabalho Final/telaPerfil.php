<?php
session_start();
include("database/conexao.php"); 

$idUsuarioLogado = $_SESSION['idUsuario'] ?? null;
$idUsuario = $_GET['idUsuario'] ?? $idUsuarioLogado;

if (!$idUsuario) {
    echo "Usuário não identificado.";
    exit;
}

$visualizandoProprioPerfil = ($idUsuarioLogado == $idUsuario);

$jaSegue = false;

if (!$visualizandoProprioPerfil && $idUsuarioLogado) {
    $query = "SELECT 1 FROM Amigo WHERE idUsuario1 = ? AND idUsuario2 = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("ii", $idUsuarioLogado, $idUsuario);
    $stmt->execute();
    $stmt->store_result();
    $jaSegue = $stmt->num_rows > 0;
}

if (!$idUsuario) {
    echo "Usuário não identificado.";
    exit;
}

$query = "SELECT nomeUsuario, imagemPerfil, dataCriacao, biografia FROM Usuario WHERE idUsuario = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("i", $idUsuario);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows === 0) {
    echo "Usuário não encontrado.";
    exit;
}

$usuario = $result->fetch_assoc();

$query = "SELECT J.capa, J.titulo 
          FROM Favorito F
          JOIN Jogo J ON F.idJogo = J.idJogo
          WHERE F.idUsuario = ?
          ORDER BY F.ordem ASC
          LIMIT 3";

$stmt = $conn->prepare($query);
$stmt->bind_param("i", $idUsuario);
$stmt->execute();
$result = $stmt->get_result();

$favoritos = [];
while ($row = $result->fetch_assoc()) {
    $favoritos[] = $row; 
}

$queryUltimoJogo = "
    SELECT J.idJogo, J.capa, J.titulo, JU.status, JU.dataInicio, JU.dataFim, JU.platina, JU.plataforma, JU.review, JU.nota
    FROM JogoUsuario JU
    JOIN Jogo J ON JU.idJogo = J.idJogo
    WHERE JU.idUsuario = ?
    ORDER BY JU.dataInicio DESC, JU.dataFim DESC
    LIMIT 1
";

$stmt = $conn->prepare($queryUltimoJogo);
$stmt->bind_param("i", $idUsuario);
$stmt->execute();
$ultimoJogoResult = $stmt->get_result();
$ultimoJogo = $ultimoJogoResult->fetch_assoc();

?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<link rel="stylesheet" href="estilo/perfil.css?=v2">
<meta charset="UTF-8">
<title>Perfil de <?php echo htmlspecialchars($usuario['nomeUsuario']); ?></title>
<style>
.perfil-e-favoritos {
    display: flex;
    align-items: flex-start;
    gap: 150px;             
    margin: 100px auto 0 auto;
    max-width: 2000px;
    padding: 0 20px;
    justify-content: center;
    flex-wrap: wrap; 
}

</style>
</head>
<body>

<?php 
include("estilo/navBar.php");
?>

<div class="perfil-e-favoritos">
    <div class="perfil-container">
        <h1><?php echo htmlspecialchars($usuario['nomeUsuario']); ?></h1>
        <img src="<?php echo htmlspecialchars($usuario['imagemPerfil']); ?>" alt="Foto de perfil">
        <p>Membro desde: <?php echo date('d/m/Y', strtotime($usuario['dataCriacao'])); ?></p>

        <div class="botoes-container">
            <button class="botao" onclick="window.location.href='telaLista.php?id=<?php echo $idUsuario; ?>'">Lista de Jogos</button>
            <?php if (!$visualizandoProprioPerfil): ?>
              <form method="post" action="database/seguir.php" style="display: inline;">
                  <input type="hidden" name="idUsuarioAlvo" value="<?php echo $idUsuario; ?>">
                  <button class="botao" type="submit">
                      <?php echo $jaSegue ? 'Remover' : 'Seguir'; ?>
                  </button>
              </form>
            <?php endif; ?>
        </div>
    </div>

<div class="favoritos-container">
  <h2>Favoritos</h2>
  <div class="capas-favoritas">
    <?php 
    for ($i = 0; $i < 3; $i++): 
        if (isset($favoritos[$i])): ?>
            <?php if ($visualizandoProprioPerfil): ?>
              <div class="slot-favorito" onclick="abrirModal(<?php echo $i + 1; ?>)">
                <img src="<?php echo htmlspecialchars($favoritos[$i]['capa']); ?>" 
                  alt="Capa de <?php echo htmlspecialchars($favoritos[$i]['titulo']); ?>" 
                  class="capa-jogo">
              </div>
            <?php else: ?>
              <div class="slot-favorito">
                <img src="<?php echo htmlspecialchars($favoritos[$i]['capa']); ?>" 
                  alt="Capa de <?php echo htmlspecialchars($favoritos[$i]['titulo']); ?>" 
                  class="capa-jogo">
              </div>
            <?php endif; ?>
        <?php else: ?>
            <?php if ($visualizandoProprioPerfil): ?>
              <div class="slot-favorito vazio" data-ordem="<?php echo $i + 1; ?>" title="Adicionar favorito"></div>
            <?php else: ?>
              <div class="slot-favorito vazio" title="Nenhum favorito"></div>
            <?php endif; ?>
        <?php endif; 
    endfor; ?>
  </div>
</div>


</div>

<?php if ($visualizandoProprioPerfil): ?>
<div class="editar-biografia-container">
    <h2>Sobre mim</h2>
    <form method="post" action="database/atualizarBiografia.php">
        <textarea name="biografia" class="caixa-biografia" placeholder="..."><?php echo htmlspecialchars($usuario['biografia']); ?></textarea>
        <button type="submit" class="botao">Salvar</button>
    </form>
</div>
<?php else: ?>
<div class="editar-biografia-container">
    <h2>Sobre mim</h2>
    <p><?php echo nl2br(htmlspecialchars($usuario['biografia'])); ?></p>
</div>
<?php endif; ?>


<?php if ($ultimoJogo): ?>
  <div class="lista-container">
    <h2>Última adição à lista</h2>
    <table>
      <thead>
        <tr>
          <th style="color: #B1C29E;">Capa</th>
          <th>Título</th>
          <th>Status</th>
          <th>Início</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td><img src="<?php echo htmlspecialchars($ultimoJogo['capa']); ?>" class="capa-img" alt="Capa"></td>
          <td><a class="titulo-link" href="telaJogo.php?id=<?php echo $ultimoJogo['idJogo']; ?>"><?php echo htmlspecialchars($ultimoJogo['titulo']); ?></a></td>
          <td><?php echo htmlspecialchars($ultimoJogo['status']); ?></td>
          <td><?php echo $ultimoJogo['dataInicio'] ? date('d/m/Y', strtotime($ultimoJogo['dataInicio'])) : '-'; ?></td>
        </tr>
      </tbody>
    </table>
  </div>
<?php endif; ?>

<?php if ($visualizandoProprioPerfil): ?>
<div id="modalFavorito" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <h3>Escolha um jogo para favoritar</h3>
    <form method="POST" action="database/favoritar.php">
      <input type="hidden" name="ordem" id="inputOrdem">
      <input type="hidden" name="idUsuario" value="<?php echo $idUsuario; ?>">
      <select name="idJogo" required>
        <option value="">Seus jogos</option>
        <?php
        $query = "SELECT J.idJogo, J.titulo FROM Jogo J 
                  JOIN JogoUsuario JU ON J.idJogo = JU.idJogo 
                  WHERE JU.idUsuario = ?";

        $stmt = $conn->prepare($query);
        $stmt->bind_param("i", $idUsuario);
        $stmt->execute();
        $result = $stmt->get_result();
        while ($jogo = $result->fetch_assoc()):
        ?>
          <option value="<?php echo $jogo['idJogo']; ?>">
            <?php echo htmlspecialchars($jogo['titulo']); ?>
          </option>
        <?php endwhile; ?>
      </select>
      <button type="submit">Favoritar</button>
    </form>
  </div>
</div>
<?php endif; ?>

<script>
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll('.slot-favorito.vazio').forEach((el) => {
    el.addEventListener('click', () => {
      const ordem = el.getAttribute('data-ordem');
      document.getElementById('inputOrdem').value = ordem;
      document.getElementById('modalFavorito').style.display = 'flex';
    });
  });

  document.querySelector('.close').onclick = () => {
    document.getElementById('modalFavorito').style.display = 'none';
  };

  window.onclick = (e) => {
    const modal = document.getElementById('modalFavorito');
    if (e.target === modal) {
      modal.style.display = 'none';
    }
  };
});
</script>
</body>
</html>