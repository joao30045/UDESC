class Pessoa: 
    def __init__(self, nome) -> None:
        self.nome = nome
    def __repr__(self) -> str:
        return 'Nome: {}'.format(self.nome)