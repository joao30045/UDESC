import imp
from pessoa import Pessoa

class Aluno(Pessoa):
    def __init__(self, nome, notas) -> None:
        super().__init__(nome)
        self.notas = notas
    def __repr__(self) -> str:
        return super().__repr__() + ' Media: {}'.format(self.calcularMedia()) + ' Aprovado: ' + self.aprovado()
    def calcularMedia(self) -> float:
        soma = 0
        for nota in self.notas:
            soma += nota
        return soma / len(self.notas)
    def aprovado(self) -> str:
        if self.calcularMedia() >= 7:
            return 'Sim'
        return 'Nao'