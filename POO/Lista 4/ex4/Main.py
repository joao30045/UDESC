from EspacoGeometrico import EspacoGeometrico
from Ponto2D import Ponto2D
from Reta import Reta
from Exception import ObjetoSobreposto

espaco = EspacoGeometrico()
retas = [Reta(1, 2), Reta(1, 1), Reta(2, -2)]
pontos = [Ponto2D(2, 3), Ponto2D(3, 4), Ponto2D(4, 6), Ponto2D(3, 2)]

for reta in retas:
    try:
        espaco.adicionar_reta(reta)
    except ObjetoSobreposto:
        print('Objeto sobreposto!')

for ponto in pontos:
    try:
        espaco.adicionar_ponto(ponto)
    except ObjetoSobreposto:
        print("Objeto sobreposto!")

print("-----Pontos e Retas no Espaço------")

for reta in espaco.retas:
    print(f"Reta y = {reta.a}x + ({reta.b})")

for ponto in espaco.pontos:
    print(f"Ponto ({ponto.x}, {ponto.y})")