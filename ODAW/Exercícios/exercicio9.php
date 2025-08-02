<?php
  date_default_timezone_set('America/Sao_Paulo'); 
  $data = date('d/m/Y');
  $hora = date('H:i');
  echo "Hoje é $data e agora são $hora h";
  ?>
  <br>
  <?php
  function mostrarNomesFormatados($nomes) {
      foreach ($nomes as $nome) {
          echo strtoupper($nome) . "<br>";
      }
  }
  $listaDeNomes = ["Ana", "Bruno", "Carlos", "Diana"];
  mostrarNomesFormatados($listaDeNomes);
  ?>
  <?php
  $arquivo = "contador.txt";
  if (!file_exists($arquivo)) {
      file_put_contents($arquivo, 0);
  }
  $visitas = (int) file_get_contents($arquivo);
  $visitas++;
  file_put_contents($arquivo, $visitas);
  echo "Esta página foi visitada $visitas vezes.";
  ?>
  <br>
  <?php
  session_start();
  if (!isset($_SESSION['usuario'])) {
      $_SESSION['usuario'] = "João"; 
      echo "Seja bem-vindo, " . $_SESSION['usuario'] . "!";
  } else {
      echo "Bem-vindo novamente, " . $_SESSION['usuario'] . "!";
  }
  ?>
