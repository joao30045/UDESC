#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define LARGURA 51  
#define ALTURA 25

typedef struct No {
    int x, y;        // Linha e coluna
    int g, h, f;     // Custos: g = custo até agora, h = heurística, f = g + h
    struct No* pai;  // Rastrear o caminho
    int noCaminho;  
} No;

No* criarNo(int x, int y, No* pai) {
    No* novoNo = (No*)malloc(sizeof(No));
    novoNo->x = x;
    novoNo->y = y;
    novoNo->g = 0;
    novoNo->h = 0;
    novoNo->f = 0;
    novoNo->pai = pai;
    return novoNo;
}

// Distância Manhattan
int heuristica(No* atual, No* objetivo) {
    return abs(atual->x - objetivo->x) + abs(atual->y - objetivo->y);
}

int mesmoNo(No* a, No* b) {
    return a->x == b->x && a->y == b->y;
}

int estaDentro(int x, int y) {
    return x >= 0 && x < LARGURA && y >= 0 && y < ALTURA;
}

int estaNaLista(No* no, No* lista[], int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        if (mesmoNo(no, lista[i])) {
            return 1;
        }
    }
    return 0;
}

void reconstruirCaminho(No* no, int mapa[ALTURA][LARGURA]) {
    while (no != NULL) {
        mapa[no->y][no->x] = 2; 
        no = no->pai;
    }
}

void imprimirMapa(int mapa[ALTURA][LARGURA]) {
    for (int y = 0; y < ALTURA; y++) {
        for (int x = 0; x < LARGURA; x++) {
            if (mapa[y][x] == 1) {
                printf("#"); 
            } else if (mapa[y][x] == 2) {
                printf("2"); 
            } else {
                printf(" "); 
            }
        }
        printf("\n");
    }
}

void aEstrela(int mapa[ALTURA][LARGURA], No* inicio, No* objetivo) {
    No* listaAberta[ALTURA * LARGURA];  // Lista de nós a serem explorados
    No* listaFechada[ALTURA * LARGURA]; // Lista de nós já explorados
    int tamanhoAberto = 0, tamanhoFechado = 0;

    listaAberta[tamanhoAberto++] = inicio;

    int direcoes[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Direita, baixo, esquerda, cima

    while (tamanhoAberto > 0) {
        // Encontrar nó com menor f na listaAberta
        int indiceAtual = 0;
        for (int i = 1; i < tamanhoAberto; i++) {
            if (listaAberta[i]->f < listaAberta[indiceAtual]->f) {
                indiceAtual = i;
            }
        }
        No* noAtual = listaAberta[indiceAtual];

        // Se chegou ao fim, reconstruir
        if (mesmoNo(noAtual, objetivo)) {
            printf("Caminho encontrado: \n");
            reconstruirCaminho(noAtual, mapa);
            return;
        }

        // Mover nó atual da listaAberta para a listaFechada
        for (int i = indiceAtual; i < tamanhoAberto - 1; i++) {
            listaAberta[i] = listaAberta[i + 1];
        }
        tamanhoAberto--;
        listaFechada[tamanhoFechado++] = noAtual;

        // Explorar os vizinhos
        for (int i = 0; i < 4; i++) {
            int novoX = noAtual->x + direcoes[i][0];
            int novoY = noAtual->y + direcoes[i][1];

            if (!estaDentro(novoX, novoY) || mapa[novoY][novoX] == 1) {
                continue; 
            }
            No* vizinho = criarNo(novoX, novoY, noAtual);

            if (estaNaLista(vizinho, listaFechada, tamanhoFechado)) {
                free(vizinho);
                continue;
            }
            // Cálculo dos custos
            vizinho->g = noAtual->g + 1;
            vizinho->h = heuristica(vizinho, objetivo);
            vizinho->f = vizinho->g + vizinho->h;
            // Se já estiver na listaAberta com um custo menor, ignorar
            if (estaNaLista(vizinho, listaAberta, tamanhoAberto)) {
                free(vizinho);
                continue;
            }
            // Adicionar vizinho à listaAberta
            listaAberta[tamanhoAberto++] = vizinho;
        }
    }
    printf("Nenhum caminho encontrado.\n");
}

int abreMapa(char* nomeArquivo, int mapa[ALTURA][LARGURA]) {
    FILE* arquivo = fopen(nomeArquivo, "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return 0;
    }

    int ch;
    int linha = 0, coluna = 0;

    while ((ch = fgetc(arquivo)) != EOF) {
        if (ch == '0' || ch == '1') {
            mapa[linha][coluna] = ch - '0'; 
            coluna++;
            if (coluna == LARGURA) {
                coluna = 0;
                linha++;
                if (linha == ALTURA) {
                    break;
                }
            }
        }
    }
    fclose(arquivo);
    return 1;
}

int main() {
    int mapa[ALTURA][LARGURA];

    if (!abreMapa("mapa01.txt", mapa)) {
        return 1; 
    }

    No* inicio = criarNo(1, 1, NULL);
    No* objetivo = criarNo(19, 6, NULL);

    aEstrela(mapa, inicio, objetivo);

    imprimirMapa(mapa); 

    free(inicio);
    free(objetivo);

    return 0;
}