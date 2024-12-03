#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

int compara(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int* geraVetor(int n) {
    int* v = malloc(sizeof(int) * (n + 1));
    int i;

    srand(time(0));
     
    for (i = 0; i < n; i++) {
        v[i] = rand() % n;
    }
    
    qsort(v, n, sizeof(int), compara);
    return v;
}

int pesquisaSequencial(int chave, int v[], int n) {
    int contador = 1;
    int i;
    
    for (i = 0; i < n; i++) {
        contador++;
        if (v[i] == chave) {
            return contador; //sucesso
        }
        contador++;
    }

    return contador; //insucesso
}

int pesquisaBinaria(int chave, int v[], int n) {
    int inicio = 0, fim = n - 1, contador = 1;
    
    while (inicio <= fim) {
        int meio = (inicio + fim) / 2;
        contador++;
        if (chave == v[meio]) {
            return contador; //sucesso
        } else if (chave < v[meio]) {
            fim = meio - 1;
        } else {
            inicio = meio + 1;
        }
        contador += 2;
    }

    return contador; //insucesso
}

int main() {

    FILE *arquivo = fopen("arquivo.csv", "w");
    int repeticoes = 10;

    fprintf(arquivo, "N,Pesquisa sequencial,Pesquisa binaria\n");

    for(int n = 1; n <= 500; n++){
        unsigned long sequencial = 0;
        unsigned long binaria = 0;
        int* v = geraVetor(n);
        for (int i = 0; i < repeticoes; i++) {
            int chave = v[rand() % n];
            sequencial += pesquisaSequencial(chave, v, n);
            binaria += pesquisaBinaria(chave, v, n);
        }
        
        fprintf(arquivo, "%d,", n);
        fprintf(arquivo, "%ld,", sequencial / repeticoes);
        fprintf(arquivo, "%ld\n", binaria / repeticoes);
    }
    fclose(arquivo);
}