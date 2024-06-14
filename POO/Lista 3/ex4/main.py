from pessoa import Pessoa
from aluno import Aluno
from professor import Professor

alunos = []
professores = []

joaopedro = Aluno('Joao Pedro', [10, 10])
alunos.append(joaopedro)
breno = Aluno('Breno', [9, 10])
alunos.append(breno)
fabio = Aluno('Fabio', [1, 8])
alunos.append(fabio)
ana = Aluno('Ana', [7, 10])
alunos.append(ana)
luiza = Aluno('Luiza', [4, 6])
alunos.append(luiza)

fabiano = Professor('Fabiano', 15000)
professores.append(fabiano)
maisa = Professor('Maisa', 10000)
professores.append(maisa)

for aluno in alunos:
    print(aluno)
for professor in professores:
    print(professor)