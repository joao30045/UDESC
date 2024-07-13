#include <stdlib.h>
#include <stdio.h>
#include <time.h>

typedef struct no {
    struct no* pai;
    struct no* esquerda;
    struct no* direita;
    int valor;
    int altura;
} No;

typedef struct arvore {
    struct no* raiz;
} Arvore;

Arvore* criar();
int vazia(Arvore* arvore);
No* criarNo(No* pai, int valor, int* contador);
No* adicionarNo(No* no, int valor, int* contador);
No* adicionar(Arvore* arvore, int valor, int* contador);
void balanceamento(Arvore* arvore, No* no, int* contador);
int altura(No* no);
int fb(No* no);
No* rse(Arvore* arvore, No* no, int* contador);
No* rsd(Arvore* arvore, No* no, int* contador);
No* rde(Arvore* arvore, No* no, int* contador);
No* rdd(Arvore* arvore, No* no, int* contador);
No* minimo(No* no);
No* removerNo(Arvore* arvore, No* no, int valor, int* contador);
void remover(Arvore* arvore, int valor, int* contador);

Arvore* criar() {
    Arvore *arvore = malloc(sizeof(Arvore));
    arvore->raiz = NULL;
    return arvore;
}

int vazia(Arvore* arvore) {
    return arvore->raiz == NULL;
}

No* criarNo(No* pai, int valor, int* contador) {
    (*contador)++;
    No* no = malloc(sizeof(No));
    no->valor = valor;
    no->pai = pai;
    no->esquerda = NULL;
    no->direita = NULL;
    no->altura = 1;
    return no;
}

No* adicionarNo(No* no, int valor, int* contador) {
    (*contador)++;
    if (valor > no->valor) {
        if (no->direita == NULL) {
            No* novo = criarNo(no, valor, contador);
            no->direita = novo;
            return novo;
        } else {
            return adicionarNo(no->direita, valor, contador);
        }
    } else {
        if (no->esquerda == NULL) {
            No* novo = criarNo(no, valor, contador);
            no->esquerda = novo;
            return novo;
        } else {
            return adicionarNo(no->esquerda, valor, contador);
        }
    }
}

No* adicionar(Arvore* arvore, int valor, int* contador) {
    if (vazia(arvore)) {
        arvore->raiz = criarNo(NULL, valor, contador);
        return arvore->raiz;
    } else {
        No* no = adicionarNo(arvore->raiz, valor, contador);
        balanceamento(arvore, no->pai, contador);
        return no;
    }
}

void balanceamento(Arvore* arvore, No* no, int* contador) {
    while (no != NULL) {
        (*contador)++;
        int fator = fb(no);

        no->altura = (altura(no->esquerda) > altura(no->direita) ? altura(no->esquerda) : altura(no->direita)) + 1;

        if (fator > 1) { // árvore mais profunda para a esquerda
            if (fb(no->esquerda) >= 0) {
                rsd(arvore, no, contador);
            } else {
                rdd(arvore, no, contador);
            }
        } else if (fator < -1) { // árvore mais profunda à direita
            if (fb(no->direita) <= 0) {
                rse(arvore, no, contador);
            } else {
                rde(arvore, no, contador);
            }
        }

        no = no->pai;
    }
}

int altura(No* no) {
    if (no == NULL) {
        return 0;
    }
    return no->altura;
}

int fb(No* no) {
    return altura(no->esquerda) - altura(no->direita);
}

No* rse(Arvore* arvore, No* no, int* contador) {
    (*contador)++;
    No* pai = no->pai;
    No* direita = no->direita;

    if (direita->esquerda != NULL) {
        direita->esquerda->pai = no;
    }

    no->direita = direita->esquerda;
    no->pai = direita;

    direita->esquerda = no;
    direita->pai = pai;

    if (pai == NULL) {
        arvore->raiz = direita;
    } else {
        if (pai->esquerda == no) {
            pai->esquerda = direita;
        } else {
            pai->direita = direita;
        }
    }

    no->altura = (altura(no->esquerda) > altura(no->direita) ? altura(no->esquerda) : altura(no->direita)) + 1;
    direita->altura = (altura(direita->esquerda) > altura(direita->direita) ? altura(direita->esquerda) : altura(direita->direita)) + 1;

    return direita;
}

No* rsd(Arvore* arvore, No* no, int* contador) {
    (*contador)++;
    No* pai = no->pai;
    No* esquerda = no->esquerda;

    if (esquerda->direita != NULL) {
        esquerda->direita->pai = no;
    }

    no->esquerda = esquerda->direita;
    no->pai = esquerda;

    esquerda->direita = no;
    esquerda->pai = pai;

    if (pai == NULL) {
        arvore->raiz = esquerda;
    } else {
        if (pai->esquerda == no) {
            pai->esquerda = esquerda;
        } else {
            pai->direita = esquerda;
        }
    }

    no->altura = (altura(no->esquerda) > altura(no->direita) ? altura(no->esquerda) : altura(no->direita)) + 1;
    esquerda->altura = (altura(esquerda->esquerda) > altura(esquerda->direita) ? altura(esquerda->esquerda) : altura(esquerda->direita)) + 1;

    return esquerda;
}

No* rde(Arvore* arvore, No* no, int* contador) {
    no->direita = rsd(arvore, no->direita, contador);
    return rse(arvore, no, contador);
}

No* rdd(Arvore* arvore, No* no, int* contador) {
    no->esquerda = rse(arvore, no->esquerda, contador);
    return rsd(arvore, no, contador);
}

No* minimo(No* no) {
    while (no->esquerda != NULL) {
        no = no->esquerda;
    }
    return no;
}

No* removerNo(Arvore* arvore, No* no, int valor, int* contador) {
    (*contador)++;
    if (no == NULL) {
        return no;
    }

    if (valor < no->valor) {
        no->esquerda = removerNo(arvore, no->esquerda, valor, contador);
    } else if (valor > no->valor) {
        no->direita = removerNo(arvore, no->direita, valor, contador);
    } else {
        if (no->esquerda == NULL || no->direita == NULL) {
            No* temp = no->esquerda ? no->esquerda : no->direita;

            if (temp == NULL) {
                temp = no;
                no = NULL;
            } else {
                *no = *temp;
            }

            free(temp);
        } else {
            No* temp = minimo(no->direita);
            no->valor = temp->valor;
            no->direita = removerNo(arvore, no->direita, temp->valor, contador);
        }
    }

    if (no == NULL) {
        return no;
    }

    no->altura = 1 + ((altura(no->esquerda) > altura(no->direita)) ? altura(no->esquerda) : altura(no->direita));

    int fator = fb(no);
    if (fator > 1 && fb(no->esquerda) >= 0) {
        return rsd(arvore, no, contador);
    }
    if (fator > 1 && fb(no->esquerda) < 0) {
        no->esquerda = rse(arvore, no->esquerda, contador);
        return rsd(arvore, no, contador);
    }
    if (fator < -1 && fb(no->direita) <= 0) {
        return rse(arvore, no, contador);
    }
    if (fator < -1 && fb(no->direita) > 0) {
        no->direita = rsd(arvore, no->direita, contador);
        return rse(arvore, no, contador);
    }

    return no;
}


void remover(Arvore* arvore, int valor, int* contador) {
    arvore->raiz = removerNo(arvore, arvore->raiz, valor, contador);
}

int main() {
    srand(time(0)); 
    int n_conjuntos = 10;
    int tamanhos[] = {1, 50, 100, 500, 1000, 5000, 10000};
    int n_tamanhos = sizeof(tamanhos) / sizeof(tamanhos[0]);
    
    FILE *arquivo = fopen("resultadosAVL.txt", "w");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo!\n");
        return 1;
    }
    
    for (int i = 0; i < n_tamanhos; i++) {
        int tamanho = tamanhos[i];
        double soma_contador_adicao = 0;
        double soma_contador_remocao = 0;

        for (int j = 0; j < n_conjuntos; j++) {
            Arvore* a = criar();
            int* chaves = (int*)malloc(tamanho * sizeof(int));
            int contador_adicao = 0;
            int contador_remocao = 0;
            
            for (int k = 0; k < tamanho; k++) {
                chaves[k] = rand() % (tamanho); 
            }
            
            for (int k = 0; k < tamanho; k++) {
                adicionar(a, chaves[k], &contador_adicao);
            }
            soma_contador_adicao += contador_adicao;
            
            for (int k = 0; k < tamanho; k++) {
                remover(a, chaves[k], &contador_remocao);
            }
            soma_contador_remocao += contador_remocao;
            
            free(chaves);
            free(a);
        }

        fprintf(arquivo, "Tamanho: %d\n", tamanho);
        fprintf(arquivo, "Operações médias de adição: %f\n", soma_contador_adicao / n_conjuntos);
        fprintf(arquivo, "Operações médias de remoção: %f\n\n", soma_contador_remocao / n_conjuntos);
    }
    
    fclose(arquivo);
    
    return 0;
}
