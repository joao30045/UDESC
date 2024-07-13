#include <stdio.h>
#include <stdlib.h>

typedef struct no {
    struct no* pai;
    struct no* esquerda;
    struct no* direita;
    float valor; // Alterado para um tipo de valor específico (float)
} No;

typedef struct arvore {
    struct no* raiz;
    int tamanho;
} Arvore;

Arvore* cria() {
    Arvore* arvore = (Arvore*)malloc(sizeof(Arvore));
    arvore->raiz = NULL;
    arvore->tamanho = 0;
    return arvore;
}

No* adiciona(Arvore* arvore, No* pai, float valor) {
    No *no = (No*)malloc(sizeof(No));
    no->pai = pai;
    no->esquerda = NULL;
    no->direita = NULL;
    no->valor = valor;
    if (pai == NULL) { 
        arvore->raiz = no;
    }
    arvore->tamanho++;
    return no; 
}

void percorrer_pre_order(No* no, void (*callback)(float)) { 
    if (no != NULL) {
        callback(no->valor);
        percorrer_pre_order(no->esquerda, callback);
        percorrer_pre_order(no->direita, callback);
    }
}

void percorrer_in_order(No* no, void (*callback)(float)) { 
    if (no != NULL) {
        percorrer_in_order(no->esquerda, callback);
        callback(no->valor);
        percorrer_in_order(no->direita, callback);
    }
}

void percorrer_pos_order(No* no, void (*callback)(float)) { 
    if (no != NULL) {
        percorrer_pos_order(no->esquerda, callback);
        percorrer_pos_order(no->direita, callback);
        callback(no->valor);
    }
}

void exibe(float v) { 
    printf("%f ", v);
}

int main(int argc, char *argv[]) {
    Arvore* a = cria();
    No* raiz = adiciona(a, NULL, 4);
    No* esquerda = adiciona(a, raiz, 2);
    No* direita = adiciona(a, raiz, 8);
    adiciona(a, esquerda, 1);
    adiciona(a, esquerda, 3);
    adiciona(a, direita, 6);
    adiciona(a, direita, 9);
    adiciona(a, direita->esquerda, 5);
    adiciona(a, direita->esquerda, 7);

    printf("Pre-order: ");
    percorrer_pre_order(a->raiz, exibe);
    printf("\n");

    printf("In-order: ");
    percorrer_in_order(a->raiz, exibe);
    printf("\n");

    printf("Pos-order: ");
    percorrer_pos_order(a->raiz, exibe);
    printf("\n");

    return 0;
}
