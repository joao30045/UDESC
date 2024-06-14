from Ponto2D import Ponto2D

class Reta:
    
    def __init__(self, a : int, b : int):
        self.a = a
        self.b = b
    
    def intercepta(self, reta) -> bool:
        if self.a == reta.a:
            return False
        return True

    def esta_na_reta(self, ponto : Ponto2D) -> bool:
        return (self.a * ponto.x) + self.b == ponto.y