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
        <a href="telaCadastro.php" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Cadastro</a> 
        <a href="javascript:novaJanela('telaLogin.php')" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Login</a> 
        <a href="Database/index.php" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-blue">Banco</a> 
    </div>
</nav>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:210px;margin-right:40px">

<h2>Cadastro de novo usuário</h2>

<?php
$nameErr = $dataNascimentoErr = $emailErr = $usuarioErr = $senhaErr = $confirmaSenhaErr = "";
$name = $dataNascimento = $email = $usuario = $senha = $confirmaSenha = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (empty($_POST["nome"])) {
    $nameErr = "Nome é obrigatório";
  } else {
    $name = test_input($_POST["nome"]);
    if (!preg_match("/^[a-zA-Z-' ]*$/",$name)) {
      $nameErr = "Apenas letras e espaços vazios";
    }
  }
  if (empty($_POST["dataNascimento"])) {
    $dataNascimentoErr = "Data de nascimento é obrigatória";
  } else {
    $dataNascimento = test_input($_POST["dataNascimento"]);
    $date_parts = explode("-", $dataNascimento);
    if (!checkdate($date_parts[1], $date_parts[2], $date_parts[0])) {
        $dataNascimentoErr = "Data inválida";
    } else {
        $hoje = new DateTime();
        $nascimento = new DateTime($dataNascimento);
        $idade = $hoje->diff($nascimento)->y;

        if ($idade < 13) {
            $dataNascimentoErr = "Você deve ter pelo menos 13 anos";
        }
    }
  }
  if (empty($_POST["email"])) {
    $emailErr = "E-mail é obrigatório";
  } else {
    $email = test_input($_POST["email"]);
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
      $emailErr = "Formato inválido de e-mail";
    }
  }

  if (empty($_POST["usuario"])) {
    $usuarioErr = "Usuário é obrigatório";
  } else {
    $usuario = test_input($_POST["usuario"]);
  }
  if (empty($_POST["senha"])) {
    $senhaErr = "Senha é obrigatória";
  } else {
    $senha = test_input($_POST["senha"]);
    if (strlen($senha) < 6) {
        $senhaErr = "A senha deve ter pelo menos 6 caracteres";
    } elseif (!preg_match("/[A-Za-z]/", $senha) || !preg_match("/[0-9]/", $senha)) {
        $senhaErr = "A senha deve conter letras e números";
    }
  }
  if (empty($_POST["confirmaSenha"])) {
    $confirmaSenhaErr = "Confirmação de senha é obrigatória";
  } else {
    $confirmaSenha = test_input($_POST["confirmaSenha"]);
    if ($senha !== $confirmaSenha) {
       $confirmaSenhaErr = "As senhas não coincidem";
    }
  }

}

function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>

<section>
<p><span class="error">* campo obrigatório</span></p>
<form action="Database/inserir.php" method="post">
    Nome:
    <br> 
    <input type="text" name="nome" value="<?php echo $name;?>">
    <span class="error">* <?php echo $nameErr;?></span>
    <br><br>
    Data de nascimento:
    <br>
    <input type="date" id="dataNascimento" name="dataNascimento" value="<?php echo $dataNascimento; ?>">
    <span class="error">* <?php echo $dataNascimentoErr;?></span>
    <br><br>
    Email:
    <br>
    <input type="email" name="email" value="<?php echo $email;?>">
    <span class="error">* <?php echo $emailErr;?></span>
    <br><br>
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
    Confirmar senha:
    <br />
    <input type="password" id="confirmaSenha" name="confirmaSenha">
    <span class="error">* <?php echo $confirmaSenhaErr; ?></span>
    <br><br>
    <input type="submit" value="Cadastrar">
</form>
</section>
    
<footer>
    <marquee><h1 style="color:yellow;">O MELHOR SITE DE RECEITAS</h1></marquee>
</footer>

</div>

</body>
</html>