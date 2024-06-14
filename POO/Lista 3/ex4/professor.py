from pessoa import Pessoa

class Professor(Pessoa):
    def __init__(self, nome, salario) -> None: 
        super().__init__(nome)
        self.salario = salario
    def __repr__(self) -> str:
        return super().__repr__() + ' Salario: {}'.format(self.salario)