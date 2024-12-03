from colonia import *
import time

def ler_matriz_arquivo(nome_arquivo):
    with open(nome_arquivo, 'r') as arquivo:
        linhas = arquivo.readlines()
    
    matriz = []
    inicio_matriz = False
    
    for linha in linhas:
        if "EDGE_WEIGHT_SECTION" in linha:
            inicio_matriz = True
            continue
        if "EOF" in linha:
            break
        if inicio_matriz:
            valores = list(map(lambda x: None if x == 'None' else int(x), linha.split()))
            matriz.append(valores)
    
    return matriz

arquivo = 'bays29.tsp'  
matriz_distancias = ler_matriz_arquivo(arquivo)

colonia = Colonia(matriz_distancias, 0.01, 0.1, 10)

start_time = time.time()
percursos, dists = colonia.rodar(50) 
end_time = time.time()

tempo_execucao = end_time - start_time
print(f"Tempo de execução: {tempo_execucao} segundos")

for aux, percurso in enumerate(percursos):
    print(percurso, dists[aux])