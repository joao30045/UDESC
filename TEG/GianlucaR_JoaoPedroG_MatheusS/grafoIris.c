#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define vertices 150
#define limiar 0.05

typedef struct{
    double sepallength, sepalwidth, petallength, petalwidth;
}Iris;

double distanciaManhattan(Iris iris1, Iris iris2){
    double distancia = fabs(iris1.sepallength - iris2.sepallength) + fabs(iris1.sepalwidth -
    iris2.sepalwidth) + fabs(iris1.petallength - iris2.petallength) + fabs(iris1.petalwidth -
    iris2.petalwidth);

    return distancia;
}

double normalizarDistanciaManhattan(double distancia, double valor_max, double valor_min){
    double distanciaNormal = (distancia - valor_min) / (valor_max - valor_min);

    return distanciaNormal; 
}

void matrizDistanciaManhattanNormalizada(double matrizAdjacencias[vertices][vertices], int num_iris, 
    double maiorDistancia, double menorDistancia){
    for(int i = 0; i < num_iris; i++){
        for(int j = 0; j < num_iris; j++){
            if(i == j){
                continue;
            }else{
                double distManNormalizada = normalizarDistanciaManhattan(matrizAdjacencias[i][j], 
                maiorDistancia, menorDistancia);
                if(distManNormalizada <= limiar){
                    matrizAdjacencias[i][j] = 1;
                }else{
                    matrizAdjacencias[i][j] = 0;
                }
            }
        }
    }
}

void salvarMatrizAdjacencias(double matrizAdjacencias[vertices][vertices], int num_iris){
    FILE *matriz = fopen("matrizAdjacenciaIris.csv", "w");
    if (matriz == NULL) {
        printf("Erro ao abrir matrizAdjacenciaIris.csv");  
    }
    for (int i = 0; i < num_iris; i++) {
        for (int j = 0; j < num_iris; j++) {
            fprintf(matriz, "%.f ", matrizAdjacencias[i][j]);
        }
        fprintf(matriz, "\n");
    }
    fclose(matriz);
}

void dfs(double matrizAdjacencias[vertices][vertices], int vetorConexos[vertices], int vertice, int grupo) {
    vetorConexos[vertice] = grupo;
    for (int j = 0; j < vertices; j++) {
        if (vetorConexos[j] == -1 && matrizAdjacencias[vertice][j] == 1) {
            dfs(matrizAdjacencias, vetorConexos, j, grupo);
        }
    }     
}

void agruparConexo(double matrizAdjacencias[vertices][vertices], int vetorConexos[vertices]){
    int grupo = 0;
    
    for (int i = 0; i < vertices; i++) {
        // verifica se o vértice corrente já faz parte de um grupo
        // se sim, prossiga para o próximo vértice
        if (vetorConexos[i] != -1)
            continue;
        grupo++;
        dfs(matrizAdjacencias, vetorConexos, i, grupo);
    }
}

// verifica se o vértice está conectado com algum outro
char verticeConectado(double matrizAdjacencias[vertices][vertices], int vertice){
    char conectado = 0;
    for (int j = 0; j < vertices; j++) {
        if (matrizAdjacencias[vertice][j] == 1) {
            return 1;
        }
    }
    return 0;
}

// verifica se os grupos encontrados estão consistentes (vértices conectados precisam estar no mesmo grupo)
void validaGrupos(double matrizAdjacencias[vertices][vertices], int vetorConexos[vertices]) {
    int gruposValidos[vertices];
    for (int i = 0; i < vertices; i++)
        gruposValidos[i] = -1;
    for (int i = 0; i < vertices; i++) {
        for (int j = i + 1; j < vertices; j++) {
            if (matrizAdjacencias[i][j] == 1) {
                if (gruposValidos[j] != -1 && gruposValidos[j] != vetorConexos[j]) {
                    printf("ERRO: Vértice encontrado anteriormente em outro grupo: %d (grupo anterior: %d), (grupo novo: %d)\n", j, gruposValidos[j], vetorConexos[j]);
                } else if (gruposValidos[j] != -1) {
                    gruposValidos[j] = vetorConexos[j];
                }
                if (vetorConexos[i] != vetorConexos[j]) {
                    printf("ERRO: Vértices conectados em grupos diferentes: %d (grupo %d), %d (grupo %d)\n", i, vetorConexos[i], j, vetorConexos[j]);
                }
            }
        }
    }
}

void imprimeContagemGrupos(int vetorConexos[vertices]) {
    int contadorGrupos[vertices] = {0};
    for (int i = 0; i < vertices; i++) {
        contadorGrupos[vetorConexos[i]]++;
    }
    for (int i = 0; i < vertices; i++) {
        if (contadorGrupos[i] != 0)
            printf("grupo: %d, quantidade: %d\n", i, contadorGrupos[i]);
    }
}

void transformarMatrizParaArestas(double matrizAdjacencias[vertices][vertices], Iris iris[vertices]){
    FILE *arestas = fopen("arestasGrafo.csv", "w");
    if (arestas == NULL) {
        printf("Erro ao abrir arestasGrafo.csv");  
    }

    int vetorConexos[vertices];
    for (int i = 0; i < vertices; i++) {
        vetorConexos[i] = -1;
    }
    agruparConexo(matrizAdjacencias, vetorConexos);
    validaGrupos(matrizAdjacencias, vetorConexos);
    imprimeContagemGrupos(vetorConexos);

    for (int i = 0; i < vertices; i++) {
        // inclui vértices que estão sozinhos em um grupo
        if (verticeConectado(matrizAdjacencias, i) == 0){
            fprintf(arestas, "%f %f %f %f %d %d %d\n", iris[i].petallength+iris[i].petalwidth, iris[i].sepallength+iris[i].sepalwidth, iris[i].petallength+iris[i].petalwidth, iris[i].sepallength+iris[i].sepalwidth, i, i, vetorConexos[i]);
            continue;
        }
        for (int j = i + 1; j < vertices; j++) {
            if (matrizAdjacencias[i][j] == 1) {
                fprintf(arestas, "%f %f %f %f %d %d %d\n", iris[i].petallength+iris[i].petalwidth, iris[i].sepallength+iris[i].sepalwidth, iris[j].petallength+iris[j].petalwidth, iris[j].sepallength+iris[j].sepalwidth, i, j, vetorConexos[i]); 
            }
        }
    }
    fclose(arestas);
}

int main(){
    FILE *arquivo = fopen("Iris.csv", "r");
    if(arquivo == NULL){
        printf("Erro ao abrir Iris.csv");
        return 0;
    }

    Iris iris[vertices];
    int num_iris = 0;

    char buffer[256];
    fgets(buffer, sizeof(buffer), arquivo);

    while(fscanf(arquivo, "%*[^,],%lf,%lf,%lf,%lf", &iris[num_iris].sepallength,
    &iris[num_iris].sepalwidth, &iris[num_iris].petallength, 
    &iris[num_iris].petalwidth) == 4){
        num_iris++;
    }
    fclose(arquivo);

    double matrizAdjacencias[vertices][vertices] = {0};
    double maiorDistancia = distanciaManhattan(iris[0], iris[1]);
    double menorDistancia = distanciaManhattan(iris[0], iris[1]);

    for(int i = 0; i < num_iris; i++){
        for(int j = 0; j < num_iris; j++){
            double distMan = distanciaManhattan(iris[i], iris[j]);
            if(i == j){
                continue;
            }else{
                matrizAdjacencias[i][j] = distMan;
                if(distMan > maiorDistancia){
                    maiorDistancia = distMan;
                }
                if(distMan < menorDistancia){
                    menorDistancia = distMan;
                }
            } 
        }  
    }
//Para conferir a maior e menor distancia e os valores da distancia manhattan
/*
    printf("Maior distancia %.4f e menor distancia %.4f\n", maiorDistancia, menorDistancia);
    FILE *tabela = fopen("tabelaDistanciaManhattan.csv", "w");
    if (tabela == NULL) {
        printf("Erro ao abrir tabelaDistanciaManhattan.csv");  
    }
    for (int i = 0; i < num_iris; i++) {
        for (int j = 0; j < num_iris; j++) {
            fprintf(tabela, "%.1f ", matrizAdjacencias[i][j]);
        }
        fprintf(tabela, "\n");
    }
    fclose(tabela);
*/
    matrizDistanciaManhattanNormalizada(matrizAdjacencias, num_iris, maiorDistancia, menorDistancia);
    salvarMatrizAdjacencias(matrizAdjacencias, num_iris);
    transformarMatrizParaArestas(matrizAdjacencias, iris);
    
    return 0;
}