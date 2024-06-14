class Carro:
    def __init__(self):
        self.fabricante = ""
        self.modelo = ""
        self.cor = ""
    
    def __str__(self) -> str:
        return "Fabricante: {} - Modelo: {} - Cor: {}".format(self.fabricante, self.modelo, self.cor)