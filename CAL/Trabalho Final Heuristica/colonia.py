from caminho import *
import random
from itertools import combinations

class Colonia:
    def __init__(self, distancias, evap, ferInicial, attFer):
        self.evap = evap
        self.ferInicial = ferInicial
        self.attFer = attFer

        for i in range(len(distancias)):
            for j in range(len(distancias)):
                if i != j and distancias[i][j] is not None:
                    distancias[i][j] = Caminho(distancias[i][j], ferInicial)
                elif i != j:
                    distancias[i][j] = None

        self.caminhos = distancias

    def rodar(self, numIt):
        for _ in range(numIt):
            for z, linha in enumerate(self.caminhos):
                somatorioP = 0
                for h, caminho in enumerate(linha):
                    if z != h and caminho is not None:
                        caminho.tn = arredondar(caminho.t * caminho.n)
                        somatorioP += caminho.tn

                for c, caminho2 in enumerate(linha):
                    if z != c and caminho2 is not None:
                        caminho2.p = arredondar(arredondar(caminho2.tn / somatorioP) * 100 if somatorioP > 0 else 0)

            percursos = []
            distsPercorridas = []
            for j in range(len(self.caminhos)):
                percurso = [j]
                distPercorrida = 0
                while len(percurso) < len(self.caminhos):
                    ele = percurso[-1]
                    indices = []
                    probabilidades = []
                    for k, caminho in enumerate(self.caminhos[ele]):
                        if k not in percurso and caminho is not None:
                            indices.append(k)
                            probabilidades.append(caminho.p)

                    if len(probabilidades) == 0:
                        break

                    escolha = metodo_da_roleta(probabilidades)
                    percurso.append(indices[escolha])
                    distPercorrida += self.caminhos[ele][indices[escolha]].distancia if self.caminhos[ele][indices[escolha]] is not None else 0

                if len(percurso) == len(self.caminhos) and self.caminhos[percurso[-1]][j] is not None:
                    distPercorrida += self.caminhos[percurso[-1]][j].distancia
                    percurso.append(j)
                percursos.append(percurso)
                distsPercorridas.append(distPercorrida)

            conjuntos = list(combinations(range(len(self.caminhos)), 2))
            for conjunto in conjuntos:
                if self.caminhos[conjunto[0]][conjunto[1]] is not None:
                    somatorio = arredondar((1 - self.evap) * self.caminhos[conjunto[0]][conjunto[1]].n)
                    for x, percurso in enumerate(percursos):
                        for y in range(len(percurso) - 1):
                            if (percurso[y] in conjunto and percurso[y + 1] in conjunto):
                                somatorio += arredondar(self.attFer / distsPercorridas[x])
                    self.caminhos[conjunto[0]][conjunto[1]].n = arredondar(somatorio)
                    self.caminhos[conjunto[1]][conjunto[0]].n = arredondar(somatorio)

        return percursos, distsPercorridas


def metodo_da_roleta(probabilidades):
    total = sum(probabilidades)
    if total == 0:
        return random.randint(0, len(probabilidades) - 1)
    
    acumulado = [sum(probabilidades[:i+1]) for i in range(len(probabilidades))]
    r = random.uniform(0, total)
    for i, valor in enumerate(acumulado):
        if r <= valor:
            return i

        
def arredondar(valor):
    return round(valor, 3)