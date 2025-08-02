<?php
include("database/conexao.php");
include("estilo/navBar.php");

$idUsuario = $_GET['id'] ?? null;

$idUsuarioLogado = $_SESSION['idUsuario'] ?? null;
$visualizandoPropriaLista = ($idUsuarioLogado == $idUsuario);

if (!$idUsuario) {
    echo "Usuário não identificado.";
    exit;
}

$queryUsuario = "SELECT nomeUsuario FROM Usuario WHERE idUsuario = ?";
$stmtUsuario = $conn->prepare($queryUsuario);
$stmtUsuario->bind_param("i", $idUsuario);
$stmtUsuario->execute();
$resultUsuario = $stmtUsuario->get_result();

if ($resultUsuario->num_rows === 0) {
    echo "Usuário não encontrado.";
    exit;
}

$usuario = $resultUsuario->fetch_assoc();

$ordenarPor = $_GET['ordenarPor'] ?? 'titulo';
$colunaOrdenacao = ($ordenarPor === 'status') ? 'JU.status' : 'J.titulo';

$query = "SELECT J.titulo, J.capa, JU.status, JU.nota, JU.review, JU.dataInicio, JU.dataFim, JU.platina, JU.plataforma, J.idJogo
          FROM JogoUsuario JU
          JOIN Jogo J ON JU.idJogo = J.idJogo
          WHERE JU.idUsuario = ?
          ORDER BY $colunaOrdenacao ASC";

$stmt = $conn->prepare($query);
$stmt->bind_param("i", $idUsuario);
$stmt->execute();
$result = $stmt->get_result();
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<link rel="stylesheet" href="estilo/lista.css">
<meta charset="UTF-8">
<title>Lista de <?php echo htmlspecialchars($usuario['nomeUsuario']); ?></title>
<style>
</style>
</head>
<body>

<div class="lista-container">
  <h1>Lista de <?php echo htmlspecialchars($usuario['nomeUsuario']); ?></h1>
  <form method="GET" style="margin-bottom: 20px;">
    <input type="hidden" name="id" value="<?php echo $idUsuario; ?>">
    <label for="ordenarPor">Ordenar por:</label>
    <select name="ordenarPor" id="ordenarPor" onchange="this.form.submit()">
      <?php $ordenarSelecionado = $_GET['ordenarPor'] ?? 'titulo'; ?>
      <option value="titulo" <?php if ($ordenarSelecionado === 'titulo') echo 'selected'; ?>>Título</option>
      <option value="status" <?php if ($ordenarSelecionado === 'status') echo 'selected'; ?>>Status</option>
    </select>
  </form>

  <table>
    <thead>
    <tr>
      <th style="color: #B1C29E;">Capa</th>
      <th>Título</th>
      <th>Status</th>
      <th>Início</th>
      <th>Fim</th>
      <th>Platina</th>
      <th>Plataforma</th>
      <th>Review</th>
      <th>Nota</th>
      <th>
        <?php if ($visualizandoPropriaLista): ?>
          <button id="btnAddJogo" class="botao">Adicionar</button>
        <?php endif; ?>
      </th>
    </tr>
   </thead>
   <tbody>
    <?php while ($jogo = $result->fetch_assoc()): ?>
      <tr>
        <td><img src="<?php echo htmlspecialchars($jogo['capa']); ?>" class="capa-img" alt="Capa"></td>
        <td><a class="titulo-link" href="telaJogo.php?id=<?php echo $jogo['idJogo']; ?>"><?php echo htmlspecialchars($jogo['titulo']); ?></a></td>
        <td><?php echo htmlspecialchars($jogo['status']); ?></td>
        <td><?php echo $jogo['dataInicio'] ? date('d/m/Y', strtotime($jogo['dataInicio'])) : '-'; ?></td>
        <td><?php echo $jogo['dataFim'] ? date('d/m/Y', strtotime($jogo['dataFim'])) : '-'; ?></td>
        <td><?php echo $jogo['platina'] ? 'Sim' : 'Não'; ?></td>
        <td><?php echo htmlspecialchars($jogo['plataforma'] ?? '-'); ?></td>
        <td>
          <?php if (!empty($jogo['review'])): ?>
            <a class="titulo-link" href="telaJogo.php?id=<?php echo $jogo['idJogo']; ?>&usuario=<?php echo $idUsuario; ?>">
              Ver Review
            </a>
          <?php endif; ?>
        </td>
        <td><div><?php echo htmlspecialchars($jogo['nota'] ?? '-'); ?></div></td>
        <td>
          <?php if ($visualizandoPropriaLista): ?>
            <button class="botao editar-btn"
              data-idjogo="<?php echo $jogo['idJogo']; ?>"
              data-status="<?php echo $jogo['status']; ?>"
              data-nota="<?php echo $jogo['nota']; ?>"
              data-datainicio="<?php echo $jogo['dataInicio']; ?>"
              data-datafim="<?php echo $jogo['dataFim']; ?>"
              data-platina="<?php echo $jogo['platina']; ?>"
              data-plataforma="<?php echo htmlspecialchars($jogo['plataforma']); ?>"
              data-review="<?php echo htmlspecialchars($jogo['review']); ?>">
              Editar
            </button>
          <?php endif; ?>
        </td>
      </tr>
    <?php endwhile; ?>
   </tbody>
  </table>

<?php if ($visualizandoPropriaLista): ?>
<div id="modalAddJogo" class="modal" style="display:none; position: fixed; z-index: 10000; left:0;top:0;width:100%;height:100%; background:rgba(0,0,0,0.5); justify-content:center; align-items:center;">
  <div style="background:#fff; padding:20px; border-radius:10px; max-width:400px; width:90%;">
    <span id="closeModal" style="float:right; cursor:pointer; font-size:24px;">&times;</span>
    <form id="formAddJogo" method="POST" action="database/adicionarJogo.php">
      <input type="hidden" name="idUsuario" value="<?php echo $idUsuario; ?>">

      <label for="idJogo">Jogo:</label><br>
      <select name="idJogo" id="idJogo" required>
        <option value="">Selecione um jogo</option>
        <?php
        $queryJogos = "SELECT idJogo, titulo FROM Jogo WHERE idJogo NOT IN (
                        SELECT idJogo FROM JogoUsuario WHERE idUsuario = ?
                      ) ORDER BY titulo ASC";
        $stmtJogos = $conn->prepare($queryJogos);
        $stmtJogos->bind_param("i", $idUsuario);
        $stmtJogos->execute();
        $resultJogos = $stmtJogos->get_result();
        while ($j = $resultJogos->fetch_assoc()):
        ?>
          <option value="<?php echo $j['idJogo']; ?>"><?php echo htmlspecialchars($j['titulo']); ?></option>
        <?php endwhile; ?>
      </select><br><br>

      <label for="status">Status:</label><br>
      <select name="status" id="status" required>
        <option value="Jogando">Jogando</option>
        <option value="Terminado">Terminado</option>
        <option value="Pretendo Jogar">Pretendo Jogar</option>
      </select><br><br>

      <label for="nota">Nota:</label><br>
      <select name="nota" id="nota">
        <option value="">Sem nota</option>
        <?php
          for ($i = 0; $i <= 10; $i++) {
            echo "<option value=\"$i\">$i</option>";
          }
        ?>
      </select><br><br>

      <label for="dataInicio">Data de Início:</label><br>
      <input type="date" name="dataInicio" id="dataInicio"><br><br>

      <label for="dataFim">Data de Fim:</label><br>
      <input type="date" name="dataFim" id="dataFim"><br><br>

      <label for="platina">Platinou?</label>
      <input type="checkbox" name="platina" id="platina" value="1"><br><br>

      <label for="plataforma">Plataforma:</label><br>
      <input type="text" name="plataforma" id="plataforma"><br><br>

      <label for="review">Review:</label><br>
      <textarea name="review" id="review" rows="4" style="width: 100%;"></textarea><br><br>

      <button type="submit" class="botao">Salvar</button>
    </form>
  </div>
</div>
<?php endif; ?>

<?php if ($visualizandoPropriaLista): ?>
<div id="modalEditarJogo" class="modal" style="display:none; position: fixed; z-index: 10000; left:0;top:0;width:100%;height:100%; background:rgba(0,0,0,0.5); justify-content:center; align-items:center;">
  <div style="background:#fff; padding:20px; border-radius:10px; max-width:400px; width:90%;">
    <span id="closeModalEditar" style="float:right; cursor:pointer; font-size:24px;">&times;</span>
    <form id="formEditarJogo" method="POST" action="database/editarJogo.php">
      <input type="hidden" name="idUsuario" value="<?php echo $idUsuario; ?>">
      <input type="hidden" name="idJogo" id="editarIdJogo">

      <label for="editarStatus">Status:</label><br>
      <select name="status" id="editarStatus" required>
        <option value="Jogando">Jogando</option>
        <option value="Terminado">Terminado</option>
        <option value="Pretendo Jogar">Pretendo Jogar</option>
      </select><br><br>

      <label for="editarNota">Nota:</label><br>
      <select name="nota" id="editarNota">
        <option value="">Sem nota</option>
        <?php for ($i = 0; $i <= 10; $i++): ?>
          <option value="<?php echo $i; ?>"><?php echo $i; ?></option>
        <?php endfor; ?>
      </select><br><br>

      <label for="editarDataInicio">Data de Início:</label><br>
      <input type="date" name="dataInicio" id="editarDataInicio"><br><br>

      <label for="editarDataFim">Data de Fim:</label><br>
      <input type="date" name="dataFim" id="editarDataFim"><br><br>

      <label for="editarPlatina">Platinou?</label>
      <input type="checkbox" name="platina" id="editarPlatina" value="1"><br><br>

      <label for="editarPlataforma">Plataforma:</label><br>
      <input type="text" name="plataforma" id="editarPlataforma"><br><br>

      <label for="editarReview">Review:</label><br>
      <textarea name="review" id="editarReview" rows="4" style="width: 100%;"></textarea><br><br>

      <button type="submit" class="botao">Salvar Alterações</button>
    </form>
  </div>
</div>
<?php endif; ?>
</div>


<script>
  const btnAddJogo = document.getElementById('btnAddJogo');
  const modalAddJogo = document.getElementById('modalAddJogo');
  const closeModal = document.getElementById('closeModal');

  btnAddJogo.addEventListener('click', () => {
    modalAddJogo.style.display = 'flex';
  });

  closeModal.addEventListener('click', () => {
    modalAddJogo.style.display = 'none';
  });

  window.addEventListener('click', (event) => {
    if (event.target === modalAddJogo) {
      modalAddJogo.style.display = 'none';
    }
  });
  const modalEditar = document.getElementById('modalEditarJogo');
const closeModalEditar = document.getElementById('closeModalEditar');

document.querySelectorAll('.editar-btn').forEach(btn => {
  btn.addEventListener('click', () => {
    document.getElementById('editarIdJogo').value = btn.dataset.idjogo;
    document.getElementById('editarStatus').value = btn.dataset.status;
    document.getElementById('editarNota').value = btn.dataset.nota || '';
    document.getElementById('editarDataInicio').value = btn.dataset.datainicio || '';
    document.getElementById('editarDataFim').value = btn.dataset.datafim || '';
    document.getElementById('editarPlatina').checked = btn.dataset.platina === "1";
    document.getElementById('editarPlataforma').value = btn.dataset.plataforma || '';
    document.getElementById('editarReview').value = btn.dataset.review || '';

    modalEditar.style.display = 'flex';
  });
});

closeModalEditar.addEventListener('click', () => {
  modalEditar.style.display = 'none';
});

window.addEventListener('click', (e) => {
  if (e.target === modalEditar) {
    modalEditar.style.display = 'none';
  }
});

</script>

</body>
</html>