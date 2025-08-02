<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/5/w3.css">
<title>Receitas</title>
</head>
<style>
    table, th, td {
      border:8px solid green;
    }
</style>
<script> 
  function novaJanela(url){
    window.open(url,'teste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,menubar=no,width=550,height=350')
  }	
</script>
<body style="background-color:dodgerblue;">

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
  </div>
</nav>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:210px;margin-right:40px">

<section>
  <br />
  <label for="filtro">Escolha o tipo de receita:</label>
  <select id="filtro" onchange="filtrarReceitas()">
    <option value="todas">Todas</option>
    <option value="salgada">Salgadas</option>
    <option value="doce">Doces</option>
  </select>
  <br /><br />

  <table style="width:20%">
    <tr>
      <td class="salgada">
        <a href="risotocamarao.php">
          <h1>Risoto de camarão</h1>
          <img src="risotocamarao.jpg" alt="Risoto de camarão" width="360" height="245" />
        </a>
      </td>
      <td class="doce">
        <a href="mousseMaracuja.php">
          <h1>Mousse de maracujá</h1>
          <img src="moussemaracuja.jpg" alt="Mousse de maracujá" width="360" height="245" />
        </a>
      </td>
    </tr>
  </table>
</section>

<script>
  function filtrarReceitas() {
    const filtro = document.getElementById("filtro").value;
    const receitasSalgadas = document.querySelectorAll(".salgada");
    const receitasDoces = document.querySelectorAll(".doce");
  
    if (filtro === "todas") {
      receitasSalgadas.forEach(el => el.style.display = "");
      receitasDoces.forEach(el => el.style.display = "");
    } else if (filtro === "salgada") {
      receitasSalgadas.forEach(el => el.style.display = "");
      receitasDoces.forEach(el => el.style.display = "none");
    } else if (filtro === "doce") {
      receitasSalgadas.forEach(el => el.style.display = "none");
      receitasDoces.forEach(el => el.style.display = "");
    }
  }
</script>
    

<footer>
  <marquee><h1 style="color:yellow;">O MELHOR SITE DE RECEITAS</h1></marquee>
</footer>

</div>

</body>
</html>