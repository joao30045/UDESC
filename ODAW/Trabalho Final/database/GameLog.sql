CREATE DATABASE GameLog;
USE GameLog;

CREATE TABLE Jogo (
    idJogo INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    dataLancamento DATE,
    plataforma VARCHAR(100),
    desenvolvedora VARCHAR(100),
    genero VARCHAR(100),
    resumo TEXT,
    mediaNota FLOAT DEFAULT 0,
    capa VARCHAR(200)
);

CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,  
    email VARCHAR(100) UNIQUE NOT NULL,
    nomeUsuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,  
    dataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    biografia TEXT,
    imagemPerfil VARCHAR(200)
);

CREATE TABLE JogoUsuario (
    idUsuario INT,
    idJogo INT,
    status ENUM('Jogando', 'Terminado', 'Pretendo Jogar') NOT NULL,
    nota TINYINT,
    review TEXT,
    dataInicio DATE,
    dataFim DATE,
    platina BOOLEAN,
    plataforma VARCHAR(100),
    PRIMARY KEY (idUsuario, idJogo),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idJogo) REFERENCES Jogo(idJogo)
);

CREATE TABLE Favorito (
    idUsuario INT,
    idJogo INT,
    ordem TINYINT CHECK (ordem BETWEEN 1 AND 3),
    PRIMARY KEY (idUsuario, idJogo),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idJogo) REFERENCES Jogo(idJogo)
);

CREATE TABLE Amigo (
    idUsuario1 INT,
    idUsuario2 INT,
    PRIMARY KEY (idUsuario1, idUsuario2),
    FOREIGN KEY (idUsuario1) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idUsuario2) REFERENCES Usuario(idUsuario)
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'ELDEN RING NIGHTREIGN',
    '2025-05-29',
    'PC, Xbox Series e PS5',
    'FromSoftware',
    'Soulslike',
    'ELDEN RING NIGHTREIGN é uma aventura independente dentro do universo de ELDEN RING, feita para oferecer aos jogadores uma nova experiência de jogo reimaginando o design básico do jogo. Una forças com outros jogadores para enfrentar a noite expansiva e os perigos dentro dela com cooperativo para 3 jogadores. Assuma o controle de heróis com habilidades únicas, cada um com seu próprio estilo distinto. Embora sejam formidáveis individualmente, suas habilidades formam poderosas sinergias quando se unem em equipe. Supere uma ameaça incansável que invade uma terra que muda a cada sessão de jogo para derrotar o magnífico chefe daquela noite!',
    9.7,
    'capas/eldenRingNightreign.jpg'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'ANIMAL WELL',
    '2024-05-04',
    'PC, Nintendo Switch e PS5',
    'Billy Basso',
    'Metroidvania',
    'Explore um labirinto denso e interconectado e descubra seus muitos segredos. Colete itens para manipular o ambiente de maneiras surpreendentes e significativas. Encontre criaturas belas e inquietantes enquanto tenta sobreviver ao que espreita na escuridão. Há mais do que se pode ver.',
    9.7,
    'capas/animalWell.jpg'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'Star Wars Outlaws',
    '2024-11-21',
    'PC, Xbox Series e PS5',
    'Ubisoft',
    'Aventura',
    'Experimente o primeiro jogo de ação e aventura de Star Wars™ em mundo aberto e explore diferentes locais pela galáxia, tanto icônicos quanto novos. Arrisque tudo como a mercenária Kay Vess, em busca de liberdade e meios para começar uma nova vida. Lute, roube e supere os sindicatos do crime da galáxia enquanto se junta aos mais procurados da galáxia.',
    9.7,
    'capas/starWarsOutlaws.png'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'ELDEN RING',
    '2022-02-24',
    'PC, Xbox Series e PS5',
    'FromSoftware',
    'Soulslike',
    'Levante-se, Maculado, e seja guiado pela graça para portar o poder do Anel Prístino e se tornar um Lorde Prístino nas Terras Intermédias. Um mundo vasto onde campos abertos e uma variedade de situações e masmorras imensas, com complexos designs tridimensionais se conectam com fluidez. Conforme explora, sinta a alegria de descobrir poderosas e desconhecidas ameaças que aguardam por você, levando a um grande senso de conquista. Além de personalizar a aparência do seu personagem, você pode combinar livremente armas, armaduras e magias que equipar. Você pode desenvolver seu personagem de acordo com seu estilo de jogo, como aumentar a força muscular para se tornar um poderoso guerreiro, ou dominar a magia. Uma história cheia de camadas, contada em fragmentos. Um drama épico onde os vários pensamentos dos personagens se interligam nas Terras Intermédias. Além do multijogador, onde você pode se conectar diretamente com outros jogadores e viajarem juntos, o jogo suporta um elemento on-line assíncrono único que permite que você sinta a presença dos outros.',    
    9.7,
    'capas/eldenRing.jpg'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'Blasphemous',
    '2019-09-10',
    'PC, Xbox Series e PS5',
    'The Game Kitchen',
    'Metroidvania',
    'Uma horrível maldição, conhecida apenas como O Milagre, caiu sobre a terra de Cvstodia e a sua população. Jogue como o Penitente — o único sobrevivente do massacre do “Sofrimento Silencioso”. Preso em um ciclo sem fim de morte e renascimento, cabe a você libertar o mundo deste terrível destino e alcançar a origem de sua angústia. Explore esse mundo aterrorizante de religião distorcida e descubra seus diversos e profundos segredos. Realize combos devastadores e execuções brutais para abater as hordas de monstros grotescos e chefes titânicos, prontos para dilacerarem seus membros, um de cada vez. Encontre e equipe-se com relíquias, contas de rosário e orações que invocam os poderes dos céus para lhe auxiliarem em sua jornada para se livrar da condenação eterna.',
    9.7,
    'capas/blasphemous.jpg'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'Persona 5 Royal',
    '2022-10-21',
    'PC, Xbox Series e PS5',
    'ATLUS',
    'JRPG',
    'Vista a máscara, junte-se aos Phantom Thieves of Hearts, arme grandes assaltos, infiltre-se nas mentes dos corruptos e faça eles mudarem de atitude! Prepare-se para a aclamada experiência de RPG com esta edição definitiva de Persona 5 Royal, incluindo diversos conteúdos adicionais! Após ser obrigado a se transferir para um colégio em Tóquio, o protagonista tem um sonho estranho. ""Você é um prisioneiro do destino. Em breve, a ruína chegará para você."" Para se ""reabilitar"", ele deve usar a máscara de um Phantom Thief para salvar os outros dos desejos distorcidos.',
    9.7,
    'capas/persona5.jpg'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'Bioshock',
    '2007-08-21',
    'PC, Xbox 360 e PS3',
    '2K',
    'FPS',
    'BioShock é um jogo de tiro diferente de tudo que você já jogou, repleto de armas e táticas nunca vistas. Você terá um arsenal completo à sua disposição, de simples revólveres a lançadores de granadas e projéteis químicos, mas também será forçado a modificar geneticamente seu DNA para criar uma arma ainda mais mortal: você. Plasmídeos injetáveis ​​concedem poderes sobre-humanos: lance correntes elétricas na água para eletrocutar vários inimigos ou congele-os e oblitere-os com o movimento de uma chave inglesa. Nenhum encontro é igual ao outro, e dois jogadores nunca jogarão o jogo da mesma maneira. Modifique biologicamente seu corpo: envie fogo das pontas dos seus dedos e libere um enxame de vespas assassinas nascidas das veias dos seus braços. Hackeie dispositivos e sistemas, aprimore suas armas e crie novas variantes de munição. Transforme tudo em uma arma: o ambiente, seu corpo, fogo e água, e até mesmo seus piores inimigos. Explore um mundo art déco incrível e único, escondido nas profundezas do oceano.',
    9.7,
    'capas/bioshock.jpg'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'Sekiro: Shadows Die Twice',
    '2019-03-21',
    'PC, Xbox Series e PS5',
    'FromSoftware',
    'Soulslike',
    'Em Sekiro™: Shadows Die Twice, você é o lobo de um braço só, um guerreiro desfigurado e desgraçado, resgatado da beira da morte. Jurado para proteger um jovem lorde descendente de uma antiga linhagem de sangue, você vira alvo de muitos inimigos perigosos, incluindo o temido clã Ashina. Quando o jovem lorde é capturado, você parte em uma jornada de redenção onde nada impedirá você, nem mesmo a própria morte. Explore o Japão no final dos anos 1500, um período brutal de conflitos mortais constantes, enquanto enfrenta inimigos épicos em um mundo sombrio e distorcido. Use um arsenal de ferramentas prostéticas mortais e habilidades ninjas poderosas ao mesmo tempo em que alterna furtividade, travessia vertical e combate direto visceral em um confronto sangrento. Obtenha vingança. Retome sua honra. Mate astuciosamente.',
    9.7,
    'capas/sekiro.jpg'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'Grand Theft Auto: San Andreas',
    '2005-06-06',
    'PC e PS2',
    'Rockstar Games',
    'Mundo aberto',
    'Cinco anos atrás Carl Johnson escapou das pressões da vida em Los Santos, San Andreas... uma cidade que se divide em problemas como gangues, drogas e corrupção. Onde estrelas e milionários fazem seu melhor para esquivar dos negociantes e líderes de gangues. Agora, é o comeco dos anos 90. Carl decide voltar para casa. Sua mãe foi assassinada, sua família se despedaçou e seus amigos de infância estão todos seguindo rumo ao desastre. Em seu retorno para a vizinhança, uma dupla de policiais corruptos o acusam de homicídio. CJ se ve forçado a entrar em uma jornada que atravessa o estado de San Andreas, para salvar sua família e recuperar o controle das ruas.',
    9.7,
    'capas/gtaSA.jpg'
);

INSERT INTO Jogo (titulo,dataLancamento,plataforma,desenvolvedora,genero,resumo,mediaNota,capa) VALUES (
    'RoboCop: Rogue City',
    '2023-11-02',
    'PC',
    'Teyon',
    'FPS',
    'Transforme-se no lendário policial metade homem, metade máquina, e combata o crime nas perigosas ruas de Old Detroit. Use sua poderosa Auto-9 ou uma das outras 20 armas disponíveis para eliminar a criminalidade em uma explosiva jornada em primeira pessoa. Sua força robótica e habilidades cibernéticas, que podem ser melhoradas no decurso da aventura, transformam você no mais eficiente agente da lei.',
    9.7,
    'capas/robocop.jpg'
);