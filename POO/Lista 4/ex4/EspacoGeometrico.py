from Ponto2D import Ponto2D
from Reta import Reta
from Exception import ObjetoSobreposto

class EspacoGeometrico:

    pontos = [] 
    retas = []

    def adicionar_reta(self, reta : Reta) -> None:
        for r in self.retas:
            if r.intercepta(reta):
                raise ObjetoSobreposto
        self.retas.append(reta)

    def adicionar_ponto(self, ponto : Ponto2D) -> None:
        for r in self.retas:
            if r.esta_na_reta(ponto):
                raise ObjetoSobreposto
        self.pontos.append(ponto)
