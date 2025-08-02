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
<title>Inicio</title>
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
    <a href="receitas.php" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Receitas</a> 
    <a href="telaCadastro.php" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Cadastro</a> 
    <a href="javascript:novaJanela('telaLogin.php')" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Login</a> 
    <a href="Database/index.php" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Banco</a> 
  </div>
</nav>  

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:210px;margin-right:40px">

<section>
  
  <h2 style="color:yellow;">Novas receitas</h2>
  <h3>Mousse de maracujá</h3>
  <a href = "moussemaracuja.php"><img 
    src = "moussemaracuja.jpg" alt = "Mousse de Maracujá" width = "360" height = "245" />
  </a>
  <p>Bora aprender como fazer mousse de maracujá? Esse é um doce difícil de 
    recusar e fácil de amar pra sempre! Vem que a gente te ensina uma receita 
    de mousse de maracujá rápida, fácil e extremamente viciante! Esta sobremesa 
    é uma verdadeira explosão de frescor e doçura, perfeita para encerrar uma 
    refeição com chave de ouro ou para refrescar os dias mais quentes. Com apenas 
    alguns ingredientes simples (suco de maracujá, creme de leite e leite condensado), 
    você poderá preparar um mousse cremoso e irresistível. A combinação equilibrada 
    entre a acidez característica do maracujá e a suavidade dos laticínios torna 
    esta sobremesa um verdadeiro deleite para o paladar. Siga o passo a passo desta 
    receita e descubra como é simples preparar essa sobremesa que vai conquistar 
    os apaixonados por sabores tropicais.</p>
</section>

<footer>
  <marquee><h1 style="color:yellow;">O MELHOR SITE DE RECEITAS</h1></marquee>
</footer>

</div>

</body>
</html>