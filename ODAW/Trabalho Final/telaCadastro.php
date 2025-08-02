<!DOCTYPE html>
<html lang="pt-br">
<head>
<link rel="stylesheet" href="estilo/container.css">
<meta charset="UTF-8">
<title>Cadastro</title>
<style>
</style>
</head>
<body>
<?php include("estilo/navBar.php"); ?>
<?php
$emailErr = $usuarioErr = $senhaErr = "";
$email = $nomeUsuario = $senha = $confirmarSenha = $mensagem = "";

function test_input($data) {
  return htmlspecialchars(stripslashes(trim($data)));
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $validado = true;

  if (empty($_POST["email"])) {
    $emailErr = "Email é obrigatório";
    $validado = false;
  } else {
    $email = test_input($_POST["email"]);
  }

  if (empty($_POST["nomeUsuario"])) {
    $usuarioErr = "Nome de Usuário é obrigatório";
    $validado = false;
  } else {
    $nomeUsuario = test_input($_POST["nomeUsuario"]);
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

  if (empty($_POST["confirmarSenha"])) {
    $senhaErr = "Confirme a senha";
    $validado = false;
  } else {
    $confirmarSenha = test_input($_POST["confirmarSenha"]);
    if ($senha !== $confirmarSenha) {
        $senhaErr = "As senhas não coincidem";
        $validado = false;
    }
  }

  if ($validado) {
    include("database/conexao.php");

    $checkEmailQuery = "SELECT email FROM Usuario WHERE email = '$email'";
    $resultEmail = mysqli_query($conn, $checkEmailQuery);
    $checkUsuarioQuery = "SELECT nomeUsuario FROM Usuario WHERE nomeUsuario = '$nomeUsuario'";
    $resultUsuario = mysqli_query($conn, $checkUsuarioQuery);

    if (mysqli_num_rows($resultEmail) > 0) {
        $emailErr = "Este email já está cadastrado.";
        $validado = false;
    } elseif  (mysqli_num_rows($resultUsuario) > 0) {
        $usuarioErr = "Este nome de usuário já está cadastrado.";
        $validado = false;
    } else {
      $senhaHash = password_hash($senha, PASSWORD_DEFAULT);
      $email = mysqli_real_escape_string($conn, $email);
      $nomeUsuario = mysqli_real_escape_string($conn, $nomeUsuario);
      $biografia = "";
      $imagemPerfil = "default.png";

      $query = "INSERT INTO Usuario (email, nomeUsuario, senha, biografia, imagemPerfil) 
      VALUES ('$email', '$nomeUsuario', '$senhaHash', '$biografia', '$imagemPerfil')";

      mysqli_query($conn, $query);

      mysqli_close($conn);

      $mensagem = "Cadastro realizado com sucesso!";
      $email = $nomeUsuario = $senha = $confirmarSenha = "";

    }
  }
}
?>

<div class="cadastro-container">
  <h2>Crie sua conta</h2>
  <?php if (!empty($mensagem)) : ?>
  <p style="text-align: center; font-weight: bold;"><?php echo $mensagem; ?></p>
<?php endif; ?>
  <form action="telaCadastro.php" method="POST">
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" id="email" name="email" value="<?php echo $email;?>" required>
      <span class="error"><?php echo $emailErr;?></span>
    </div>
    <div class="form-group">
      <label for="nomeUsuario">Nome de Usuário</label>
      <input type="text" id="nomeUsuario" name="nomeUsuario" value="<?php echo $nomeUsuario;?>" required>
      <span class="error"><?php echo $usuarioErr;?></span>
    </div>
    <div class="form-group">
      <label for="senha">Senha</label>
      <input type="password" id="senha" name="senha" value="<?php echo $senha;?>" required>
      <span class="error"><?php echo $senhaErr;?></span>
    </div>
    <div class="form-group">
      <label for="confirmarSenha">Confirmar Senha</label>
      <input type="password" id="confirmarSenha" name="confirmarSenha" value="<?php echo $confirmarSenha;?>" required>
      <span class="error"><?php echo $senhaErr;?></span>
    </div>
    <button type="submit" class="btn-cadastrar">Cadastrar</button>
  </form>
</div>

</body>
</html>