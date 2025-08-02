<?php
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}
$logado = isset($_SESSION['idUsuario']);
?>
<!DOCTYPE html>
<html>
<head>
<style>
body {
  margin: 0;
  background-color: #FADA7A; 
  font-family: "Courier";
}

ul {
  display: flex;
  justify-content: space-between;
  align-items: center;
  list-style-type: none;
  margin: 0;
  padding: 0 20px;
  height: 80px;
  background-color: #B1C29E;
  width: 100%;
  box-sizing: border-box;
}

ul .left {
  display: flex;
  gap: 20px;
}

ul .right {
  display: flex;
  gap: 20px;
  align-items: center;
}

li a {
  display: block;
  color: black;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #F0A04B;
}

.active {
  background-color: #B1C29E;
}

.button-link {
  background-color: black;
  color: white;
  padding: 14px 20px;
  border-radius: 12px;
  text-decoration: none;
  font-size: 16px;
  display: inline-block;
  text-align: center;
  cursor: pointer;
}

#searchOverlay {
  display: none; 
  position: fixed;
  top: 0; left: 0; right: 0; bottom: 0;
  background: rgba(0,0,0,0.5);
  z-index: 1000;

  display: flex;
  justify-content: center;
  align-items: center;
}

#searchBox {
  background: white;
  padding: 15px 25px;
  border-radius: 15px;
  box-shadow: 0 0 20px rgba(0,0,0,0.3);
  width: 400px;
  max-width: 90%;
}

#searchInput {
  width: 100%;
  padding: 10px 15px;
  font-size: 18px;
  border: 2px solid #B1C29E;
  border-radius: 10px;
  outline: none;
  font-family: "Courier";
  transition: border-color 0.3s ease;
  box-sizing: border-box;
}

#searchInput:focus {
  border-color: #F0A04B;
}

.search-icon {
  cursor: pointer;
}

.dropdown {
  position: relative;
}

.dropdown-menu {
  display: none;
  position: absolute;
  top: 50px;
  right: -40px;
  background: transparent; 
  list-style-type: none;
  margin: 0;
  width: 180px;
  z-index: 999;
  border-radius: 10px;
  overflow: hidden; 
}

.dropdown-menu li {
  width: 100%;
  background-color: #B1C29E; 
}

.dropdown-menu li a {
  display: block;
  padding: 12px 16px;
  color: black;
  text-decoration: none;
  font-family: "Courier";
}

.dropdown-menu li a:hover {
  background-color: #F0A04B;
}

</style>
</head>
<body>

<script>
  document.addEventListener("DOMContentLoaded", () => {
    document.getElementById('searchOverlay').style.display = 'none';
  });
</script>
  
<ul>
  <div class="left">
  <li>
    <a class="active" href="telaEntrada.php">
      <svg width="150" height="50" viewBox="0 0 300 100" xmlns="http://www.w3.org/2000/svg" fill="none" style="vertical-align: middle;">
        <rect width="250" height="100" fill="#FADA7A" rx="15"/>
        <g transform="translate(20, 25)">
          <rect x="0" y="0" width="60" height="35" rx="10" fill="#333"/>
          <circle cx="15" cy="17.5" r="5" fill="#FADA7A"/>
          <circle cx="45" cy="12" r="4" fill="red"/>
          <circle cx="50" cy="17.5" r="4" fill="blue"/>
          <circle cx="45" cy="23" r="4" fill="green"/>
        </g>
        <text x="95" y="60" font-family="Courier, monospace" font-size="32" fill="#000" font-weight="bold">
          GameLog
        </text>
      </svg>
    </a>
  </li>
  </div>

  <div class="right">
    <svg class="search-icon" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="30" height="30" viewBox="0 0 50 50" aria-label="Buscar" role="button" tabindex="0" >
      <path d="M 21 3 C 11.621094 3 4 10.621094 4 20 C 4 29.378906 11.621094 37 21 37 C 24.710938 37 28.140625 35.804688 30.9375 33.78125 L 44.09375 46.90625 L 46.90625 44.09375 L 33.90625 31.0625 C 36.460938 28.085938 38 24.222656 38 20 C 38 10.621094 30.378906 3 21 3 Z M 21 5 C 29.296875 5 36 11.703125 36 20 C 36 28.296875 29.296875 35 21 35 C 12.703125 35 6 28.296875 6 20 C 6 11.703125 12.703125 5 21 5 Z"></path>
    </svg>
    <li><a href="telaRanking.php">Ranking</a></li>
     <?php if ($logado): ?>
      <li><a href="telaAtividades.php">Atividades</a></li>
      <li class="dropdown">
        <a class="button-link active" id="dropdownToggle">Perfil</a>
        <ul class="dropdown-menu" id="dropdownMenu">
          <li><a href="telaPerfil.php">Ver Perfil</a></li>
          <li><a href="database/logout.php">Logout</a></li>
        </ul>
      </li>
    <?php else: ?>
      <li><a href="telaCadastro.php">Cadastrar</a></li>
      <li><a href="telaLogin.php" class="button-link active">Login</a></li>
    <?php endif; ?>
  </div>
</ul>

<div id="searchOverlay" role="dialog" aria-modal="true" aria-labelledby="searchLabel">
  <div id="searchBox">
    <form action="telaResultadoBusca.php" method="GET" style="width: 100%;">
      <input id="searchInput" type="text" name="q" placeholder="Digite sua busca..." aria-label="Campo de busca" required />
    </form>
  </div>
</div>

<script>
  const searchIcon = document.querySelector('.search-icon');
  const searchOverlay = document.getElementById('searchOverlay');
  const searchInput = document.getElementById('searchInput');

  searchIcon.addEventListener('click', () => {
    searchOverlay.style.display = 'flex';
    searchInput.focus();
  });

  searchOverlay.addEventListener('click', (e) => {
    if (e.target === searchOverlay) {
      searchOverlay.style.display = 'none';
      searchInput.value = '';
    }
  });

  document.addEventListener('keydown', (e) => {
    if (e.key === "Escape" && searchOverlay.style.display === 'flex') {
      searchOverlay.style.display = 'none';
      searchInput.value = '';
    }
  });

  
  const dropdownToggle = document.getElementById("dropdownToggle");
  const dropdownMenu = document.getElementById("dropdownMenu");

  dropdownToggle.addEventListener("click", (e) => {
    e.stopPropagation(); 
    dropdownMenu.style.display = dropdownMenu.style.display === "block" ? "none" : "block";
  });


  document.addEventListener("click", () => {
    dropdownMenu.style.display = "none";
  });

</script>

</body>
</html>