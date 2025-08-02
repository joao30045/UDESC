<!DOCTYPE html>
<html lang="pt-br">
<head>
<link rel="stylesheet" href="estilo/container.css">
<meta charset="UTF-8">
<title>Login</title>
<style>
</style>
</head>
<body>

<?php
session_start();

$usuarioErr = $senhaErr = $loginErr = "";
$nomeUsuario = $senha = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (empty($_POST["nomeUsuario"])) {
        $usuarioErr = "Nome de Usuário é obrigatório";
    } else {
        $nomeUsuario = trim($_POST["nomeUsuario"]);
    }

    if (empty($_POST["senha"])) {
        $senhaErr = "Senha é obrigatória";
    } else {
        $senha = trim($_POST["senha"]);
    }

    if (empty($usuarioErr) && empty($senhaErr)) {
        include("database/conexao.php");

        $stmt = $conn->prepare("SELECT idUsuario, nomeUsuario, senha FROM Usuario WHERE nomeUsuario = ?");
        $stmt->bind_param("s", $nomeUsuario);
        $stmt->execute();
        $stmt->store_result();

        if ($stmt->num_rows === 1) {
            $stmt->bind_result($idUsuario, $nomeUsuario, $senhaHash);
            $stmt->fetch();

            if (password_verify($senha, $senhaHash)) {
                $_SESSION['idUsuario'] = $idUsuario;
                $_SESSION['nomeUsuario'] = $nomeUsuario;
                header("Location: telaPerfil.php");
                exit;
            } else {
                $loginErr = "Senha incorreta";
            }
        } else {
            $loginErr = "Usuário não encontrado";
        }

        $stmt->close();
        $conn->close();
    }
}

include("estilo/navBar.php");
?>


<div class="cadastro-container">
  <h2>Faça seu login</h2>
  <?php if (!empty($loginErr)) : ?>
    <p style="color: red; text-align: center; font-weight: bold;"><?php echo $loginErr; ?></p>
  <?php endif; ?>
  <form action="telaLogin.php" method="POST">
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
    <button type="submit" class="btn-cadastrar">Login</button>
  </form>
</div>

</body>
</html>