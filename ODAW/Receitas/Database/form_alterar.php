<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/5/w3.css">
<script> 
  function novaJanela(url){
    window.open(url,'teste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,menubar=no,width=550,height=350')
  }	
</script> 
<title>Banco de dados</title>
</head>
<body style="background-color:dodgerblue;" onload="saudacaoComData()">

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-green w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:170px;font-weight:bold;" id="mySidebar"><br>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
  <div class="w3-container">
    <a class="active" href="telaInicial.php">
    <svg height="50" width="100">
      <defs>
        <linearGradient id="grad1">
          <stop offset="0%" stop-color="yellow" />
          <stop offset="100%" stop-color="green" />
        </linearGradient>
        <linearGradient id="grad2">
          <stop offset="0%" stop-color="green" />
          <stop offset="100%" stop-color="yellow" />
        </linearGradient>
      </defs>
      <rect x="0" y="0" width="100" height="50" stroke="black" stroke-width="2" fill="url(#grad1)" />
      <text fill="url(#grad2)" stroke = "black" stroke-width = "1" font-size="15" font-family="Verdana"font-color = Black x="10" y="32">MAMUSKA</text>
      Sorry, your browser does not support inline SVG.
    </svg>
    </a>
  </div>
  <div class="w3-bar-block">
    <br />
    <a href="../telaCadastro.php" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Cadastro</a> 
    <a href="index.php" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Banco</a> 
  </div>
</nav>  

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:210px;margin-right:40px">

<section>
<?php

$codigo = $_GET['codigo'];
$nome = "";
$dataNascimento = "";
$email = "";
$nomeUsuario = "";
$senha = "";

$link = mysqli_connect("localhost", "root", "", "receitas");
//$link = mysqli_connect("localhost", "root", "udesc", "receitas");

$query = "SELECT * FROM usuario WHERE codigo='$codigo'";
$result = mysqli_query($link, $query);
if ($row = mysqli_fetch_row($result)) {
	$nome = $row[1];
	$dataNascimento = $row[2];
	$email = $row[3];
	$nomeUsuario = $row[4];
	$senha = $row[5];
}

mysqli_close($link);
?>

<form action='alterar.php' method='post'>
<b>Alterar elemento codigo <?php echo $codigo; ?>:</b><br><br>
<input type='hidden' name='codigo' value= <?php echo $codigo;?> >
Nome: <input type='text' name='nome' value= <?php echo $nome; ?> ><br>
Data de nascimento: <input type='date' name='dataNascimento' value= <?php echo $dataNascimento; ?> ><br>
Email: <input type='text' name='email' value= <?php echo $email; ?> ><br>
Nome de usuario: <input type='text' name='usuario' value= <?php echo $nomeUsuario; ?> ><br>
Senha: <input type='password' name='senha' value= <?php echo $senha; ?> ><br>
<input type='submit' name='editar' value='Editar'>
</form><hr>

</section>

<footer>
  <marquee><h1 style="color:yellow;">O MELHOR SITE DE RECEITAS</h1></marquee>
</footer>

</div>

</body>
</html>