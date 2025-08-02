<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/5/w3.css">
<title>Login Hash</title>
</head>
<body style="background-color:dodgerblue;">

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:210px;margin-right:40px">

<h2>Login Hash</h2>
<?php
$usuarioErr = $senhaErr = $loginErr = "";
$usuario = $senha = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (empty($_POST["usuario"])) {
        $usuarioErr = "Usuário é obrigatório";
    } else {
        $usuario = trim($_POST["usuario"]);
    }
    if (empty($_POST["senha"])) {
        $senhaErr = "Senha é obrigatória";
    } else {
        $senha = trim($_POST["senha"]);
    }
    if (empty($usuarioErr) && empty($senhaErr)) {
        $validado = false;
        $linhas = file("autenticacao2.txt");
        foreach ($linhas as $linha) {
            list($userArq, $senhaArq) = explode(":", trim($linha));
            if ($usuario === $userArq) {
                if (password_verify($senha, $senhaArq)) {
                    $validado = true;
                    break;
                }
            }
        }
        if ($validado) {
            echo "<p>Login bem-sucedido!</p>";
        } else {
            $loginErr = "Usuário ou senha incorretos";
        }
    }
}
?>

<section>
<form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="post">
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
    <input type="submit" value="Entrar">
    <br><br>
    <span class="error"><?php echo $loginErr; ?></span>
</form>
</section>

</div>

</body>
</html>