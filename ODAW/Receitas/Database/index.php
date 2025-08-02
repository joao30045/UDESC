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

$link = mysqli_connect("localhost", "root", "", "receitas");
//$link = mysqli_connect("localhost", "root", "udesc", "receitas");
	
$query = "SELECT * FROM usuario ORDER BY nome";
$result = mysqli_query($link, $query);

echo "SELECT: $query<br>";

?>

<table border=\"1\">
<tr><td><b>Código</b></td>
<td><b>Nome</b></td>
<td><b>Data de nascimento</b></td>
<td><b>E-mail</b></td>
<td><b>Usuario</b></td>
<td><b>Senha</b></td>
<td><b>&nbsp;</b></td>
<td><b>&nbsp;</b></td></tr>

<?php
while ($row = mysqli_fetch_row($result)) {
	echo "<tr><td>".$row[0]."</td>";
	echo "<td>".$row[1]."</td>";
	echo "<td>".$row[2]."</td>";
	echo "<td>".$row[3]."</td>";
  echo "<td>".$row[4]."</td>";
  echo "<td>".$row[5]."</td>";
	echo "<td><a href=\"deletar.php?codigo=".$row[0]."\">deletar</a>";
	echo "<td><a href=\"form_alterar.php?codigo=".$row[0]."\">alterar</a></tr>";
}
echo "</table><hr>";

mysqli_close($link);

?>

</section>

<footer>
  <marquee><h1 style="color:yellow;">O MELHOR SITE DE RECEITAS</h1></marquee>
</footer>

</div>

</body>
</html>