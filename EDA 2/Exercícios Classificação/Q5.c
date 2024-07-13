#include <stdio.h>
#include <stdlib.h>

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

struct NoArvore *construirArvore(struct NoArvore **particoes, int n) {
    if (n <= 0) {
        return NULL;
    }

    if (n == 1) {
        return particoes[0];
    }

    struct NoArvore *arvoreVencedores = NULL;
    for (int i = 0; i < n; i += 2) {
        struct NoArvore *esquerda = particoes[i];
        struct NoArvore *direita = (i + 1 < n) ? particoes[i + 1] : NULL;

        struct NoArvore *pai = criarNo(-1, -1);
        pai->esquerda = esquerda;
        pai->direita = direita;

        arvoreVencedores = criarNo(-1, -1);
        arvoreVencedores->esquerda = pai;
        arvoreVencedores->direita = NULL; // Único nó no nível superior

        esquerda->valor = esquerda->valor; // Realmente inicializamos o nó esquerdo da árvore de vencedores
        esquerda->indiceParticao = i; // Guarda o índice da partição
        direita->valor = direita->valor; // Realmente inicializamos o nó direito da árvore de vencedores
        direita->indiceParticao = i + 1; // Guarda o índice da partição
    }

    return arvoreVencedores;
}

void imprimirArvore(struct NoArvore *raiz) {
    if (raiz == NULL) {
        return;
    }

    printf("%d ", raiz->valor);
    imprimirArvore(raiz->esquerda);
    imprimirArvore(raiz->direita);
}

int main() {
    // Criar as partições dinâmicas
    struct NoArvore *particao1 = criarNo(5, 0);
    particao1->esquerda = criarNo(6, 1);
    particao1->direita = criarNo(14, 2);

    struct NoArvore *particao2 = criarNo(15, 3);
    particao2->esquerda = criarNo(18, 4);
    particao2->direita = criarNo(20, 5);

    struct NoArvore *particao3 = criarNo(26, 6);
    particao3->esquerda = criarNo(30, 7);
    particao3->direita = NULL;

    struct NoArvore *particao4 = criarNo(32, 8);
    particao4->esquerda = criarNo(41, 9);
    particao4->direita = criarNo(48, 10);

    // Criar a lista de partições
    struct NoArvore *particoes[] = {particao1, particao2, particao3, particao4};
    int numParticoes = sizeof(particoes) / sizeof(particoes[0]);

    // Construir a árvore binária de vencedores
    struct NoArvore *arvoreVencedores = construirArvore(particoes, numParticoes);

    // Imprimir a árvore de vencedores (para visualização)
    printf("Arvore de vencedores:\n");
    imprimirArvore(arvoreVencedores);
    printf("\n");

    return 0;
}
