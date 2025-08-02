<!DOCTYPE html>
<html lang="pt-br">
<head>
<link rel="stylesheet" href="estilo/entrada.css?=v3">
<meta charset="UTF-8">
<title>Entrada</title>
<style>
</style>
</head>
<body>
<?php
include("estilo/navBar.php"); 
include("database/conexao.php");

$query = "SELECT idJogo, titulo, capa FROM Jogo ORDER BY dataLancamento DESC LIMIT 6";
$result = $conn->query($query);

$ultimosJogos = [];
while ($row = $result->fetch_assoc()) {
    $ultimosJogos[] = $row;
}
?>

<div class="container">
  <form class="search-box" action="telaResultadoBusca.php" method="GET">
    <input type="text" name="q" placeholder="Digite sua busca..." required>
  </form>
</div>

<div class="ultimos-lancamentos">
  <h2>Últimos lançamentos</h2>
  <div class="jogos-capas">
    <?php foreach ($ultimosJogos as $jogo): ?>
      <a href="telaJogo.php?id=<?php echo $jogo['idJogo']; ?>">
        <img src="<?php echo htmlspecialchars($jogo['capa']); ?>" alt="Capa de <?php echo htmlspecialchars($jogo['titulo']); ?>">
      </a>
    <?php endforeach; ?>
  </div>
</div>

<div class="sobre-site">
  <h2>O que é o GameLog?</h2>
  <p>
    O GameLog é um espaço para você gerenciar sua coleção de jogos. 
    Atualize sua lista, avalie os jogos que jogou, e marque aqueles que pretende jogar. 
    Compartilhe sua jornada gamer com amigos e acompanhe suas últimas atividades.
  </p>

  <div class="funcionalidades">

    <div class="funcionalidade">
      <img src="perfil.png" alt="Perfil" />
      <h3>Gerencie sua coleção pessoal</h3>
      <p>
        Monte seu perfil, mostre seus 3 jogos favoritos e diga mais sobre quem é você.
      </p>
    </div>

    <div class="funcionalidade">
      <img src="jogo.png" alt="Jogos" />
      <h3>Expresse suas opiniões</h3>
      <p>
        Veja as notas e avaliações da comunidade. Depois, escreva sua própria review para compartilhar seu ponto de vista.
      </p>
    </div>

    <div class="funcionalidade">
      <img src="atividades.png" alt="Atividades" />
      <h3>Acompanhe seus amigos</h3>
      <p>
        Siga seus amigos para ver suas últimas conquistas, reviews e jogos adicionados.
      </p>
    </div>

    <div class="funcionalidade">
      <img src="lista.png" alt="Listas de Jogos" />
      <h3>Crie e organize a sua lista</h3>
      <p>
        Monte sua lista e compartilhe publicamente para todos verem sua jornada.
      </p>
    </div>

  </div>
</div>

</body>
</html>