<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/5/w3.css">
<title>Mousse de maracujá</title>
<style>
    ul.a {
      list-style-type: circle;
    }
    ol.c {
      list-style-type: decimal;
    }
</style>
<script> 
    function novaJanela(url){
      window.open(url,'teste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,menubar=no,width=550,height=350')
    }	
</script>
</head>
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

<h2>Mousse de maracujá</h2>

<iframe width="560" height="315" src="https://www.youtube.com/embed/YdL2Y4LQEmw?si=J90qPH8k5LC9qYdQ" 
    title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; 
    encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" 
    allowfullscreen></iframe>
<br />
<br />

<section>
<label for="multiplicador">Deseja aumentar a receita?</label>
<select id="multiplicador" onchange="multiplicarReceita()">
    <option value="1">Original</option>
    <option value="2">Dobrar</option>
    <option value="3">Triplicar</option>
</select>
<br />
<br />
<b>INGREDIENTES</b>
<br />

<ul class="a">
    <li><span data-base="1" class="ingrediente">1</span> lata de leite condensado</li>
    <li><span data-base="1" class="ingrediente">1</span> lata de creme de leite sem soro</li>
    <li><span data-base="1" class="ingrediente">1</span> lata de suco de maracujá (medida pela lata de leite condensado)</li>
</ul>
    
<script>
    function multiplicarReceita() {
      const fator = parseInt(document.getElementById("multiplicador").value);
      const ingredientes = document.querySelectorAll(".ingrediente");
    
      ingredientes.forEach(el => {
        const base = parseFloat(el.getAttribute("data-base"));
        el.textContent = base * fator;
      });
    }
</script>
    
<b>MODO DE PREPARO</b>
<br />

<ol class = "c">
    <li>Em um liquidificador, bata o creme de leite, o leite condensado e o 
        suco concentrado de maracujá.</li>
    <li>Em uma tigela, despeje a mistura e leve à geladeira por, no mínimo, 4 horas.</li>
</ol>
</section>

<h2>Qual a sua nota para a receita?</h2>

<form>
    <input type="radio" id="muitoBoa" name="avaliacao" value="Muito boa">
    <label for="muitoBoa">Muito boa</label>
    <input type="radio" id="ok" name="avaliacao" value="OK">
    <label for="ok">Ok</label>
    <input type="radio" id="fraca" name="avaliacao" value="Fraca">
    <label for="fraca">Fraca</label>
</form> 

<footer>
    <marquee><h1 style="color:yellow;">O MELHOR SITE DE RECEITAS</h1></marquee>
</footer>

</div>

</body>
</html>