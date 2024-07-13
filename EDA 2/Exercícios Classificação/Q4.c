#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define CAPACIDADE_MEMORIA 7

struct NoArvore {
    int valor;
    int indiceParticao;
    struct NoArvore *esquerda, *direita;
};

struct NoArvore *criarNo(int valor, int indiceParticao) {
    struct NoArvore *novoNo = (struct NoArvore *)malloc(sizeof(struct NoArvore));
    novoNo->valor = valor;
    novoNo->indiceParticao = indiceParticao;
    novoNo->esquerda = novoNo->direita = NULL;
    return novoNo;
}

struct NoArvore *construirArvore(int arr[], int indicesParticoes[], int inicio, int fim) {
    if (inicio > fim) {
        return NULL;
    }

    int meio = (inicio + fim) / 2;
    struct NoArvore *raiz = criarNo(arr[meio], indicesParticoes[meio]);

    raiz->esquerda = construirArvore(arr, indicesParticoes, inicio, meio - 1);
    raiz->direita = construirArvore(arr, indicesParticoes, meio + 1, fim);

    return raiz;
}

void intercalarBalanceado(struct NoArvore *arvore, int arr[], int indicesParticoes[], int *indiceAtual) {
    if (arvore == NULL) {
        return;
    }

    arr[*indiceAtual] = arvore->valor;
    indicesParticoes[*indiceAtual] = arvore->indiceParticao;
    (*indiceAtual)++;

    intercalarBalanceado(arvore->esquerda, arr, indicesParticoes, indiceAtual);
    intercalarBalanceado(arvore->direita, arr, indicesParticoes, indiceAtual);
}

void imprimirArray(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {
    int particao1[] = {5, 6, 14, 15};
    int particao2[] = {18, 20, 26, 30};
    int particao3[] = {32, 41, 48, 75};
    int particao4[] = {4, 21, 22, 65};
    int tamanhoParticao = CAPACIDADE_MEMORIA;

    int arr[4 * tamanhoParticao];
    int indicesParticoes[4 * tamanhoParticao];
    int indiceAtual = 0;

    struct NoArvore *arvore = construirArvore(particao1, (int[]){0, 1, 2, 3}, 0, tamanhoParticao - 1);
    intercalarBalanceado(arvore, arr, indicesParticoes, &indiceAtual);

    arvore = construirArvore(particao2, (int[]){4, 5, 6, 7}, 0, tamanhoParticao - 1);
    intercalarBalanceado(arvore, arr, indicesParticoes, &indiceAtual);

    arvore = construirArvore(particao3, (int[]){8, 9, 10, 11}, 0, tamanhoParticao - 1);
    intercalarBalanceado(arvore, arr, indicesParticoes, &indiceAtual);

    arvore = construirArvore(particao4, (int[]){12, 13, 14, 15}, 0, tamanhoParticao - 1);
    intercalarBalanceado(arvore, arr, indicesParticoes, &indiceAtual);

    printf("Array intercalado:\n");
    imprimirArray(arr, 4 * tamanhoParticao);

    printf("Indices das particoes intercaladas:\n");
    imprimirArray(indicesParticoes, 4 * tamanhoParticao);

    return 0;
}
