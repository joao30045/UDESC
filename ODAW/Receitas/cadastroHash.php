<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/5/w3.css">
<title>Cadastro</title>
</head>
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
        <a href="cadastroHash.php" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Cadastro Hash</a> 
        <a href="javascript:novaJanela('loginHash.php')" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Login Hash</a> 
    </div>
</nav>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:210px;margin-right:40px">

<h2>Cadastro de novo usuário Hash</h2>

<?php
$usuarioErr = $senhaErr = "";
$usuario = $senha = $mensagem = "";

function test_input($data) {
  return htmlspecialchars(stripslashes(trim($data)));
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $validado = true;

  if (empty($_POST["usuario"])) {
    $usuarioErr = "Usuário é obrigatório";
    $validado = false;
  } else {
    $usuario = test_input($_POST["usuario"]);
  }

  if (empty($_POST["senha"])) {
    $senhaErr = "Senha é obrigatória";
    $validado = false;
  } else {
    $senha = test_input($_POST["senha"]);
    if (strlen($senha) < 6) {
        $senhaErr = "A senha deve ter pelo menos 6 caracteres";
        $validado = false;
    } elseif (!preg_match("/[A-Za-z]/", $senha) || !preg_match("/[0-9]/", $senha)) {
        $senhaErr = "A senha deve conter letras e números";
        $validado = false;
    }
  }

  if ($validado) {
    $senhaHash = password_hash($senha, PASSWORD_DEFAULT);
    $linha = $usuario . ":" . $senhaHash . "\n";

    file_put_contents("autenticacao2.txt", $linha, FILE_APPEND);
    $mensagem = "<p>Cadastro realizado com sucesso!</p>";
    $usuario = $senha = "";
  }
}
?>


<section>
<p><span class="error">* campo obrigatório</span></p>
<form action="http://localhost/Receitas/cadastroHash.php" method="post">
    Nome de usuário:
    <br>
    <input type="text" name="usuario" value="<?php echo $usuario;?>">
    <span class="error">* <?php echo $usuarioErr;?></span>
    <br><br>
    Senha:
    <br>
    <input type="password" name="senha" value="<?php echo $senha; ?>">
    <span class="error">* <?php echo $senhaErr; ?></span>
    <br><br>
    <input type="submit" value="Cadastrar">
</form>
<br>
<br>
</section>
    
<footer>
    <marquee><h1 style="color:yellow;">O MELHOR SITE DE RECEITAS</h1></marquee>
</footer>

</div>

</body>
</html>