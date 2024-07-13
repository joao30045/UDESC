#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char* items;
    int top;
    int capacity;
} Pilha;

Pilha* criarPilha(int capacidade) {
    Pilha* pilha = (Pilha*)malloc(sizeof(Pilha));
    pilha->capacity = capacidade;
    pilha->top = -1;
    pilha->items = (char*)malloc(capacidade * sizeof(char));
    return pilha;
}

int vazia(Pilha* pilha) {
    return pilha->top == -1;
}

int cheia(Pilha* pilha) {
    return pilha->top == pilha->capacity - 1;
}

void push(Pilha* pilha, char item) {
    if (!cheia(pilha)) {
        pilha->items[++pilha->top] = item;
    }
}

char pop(Pilha* pilha) {
    if (!vazia(pilha)) {
        return pilha->items[pilha->top--];
    }
    return '\0'; // Retorna caractere nulo se a pilha estiver vazia
}

char topo(Pilha* pilha) {
    if (!vazia(pilha)) {
        return pilha->items[pilha->top];
    }
    return '\0'; // Retorna caractere nulo se a pilha estiver vazia
}

void liberarPilha(Pilha* pilha) {
    free(pilha->items);
    free(pilha);
}

int estaBalanceada(const char* expressao) {
    Pilha* pilha = criarPilha(strlen(expressao));
    int i;

    for (i = 0; expressao[i] != '\0'; i++) {
        if (expressao[i] == '(' || expressao[i] == '[' || expressao[i] == '{') {
            push(pilha, expressao[i]);
        } else if (expressao[i] == ')' || expressao[i] == ']' || expressao[i] == '}') {
            if (vazia(pilha)) {
                liberarPilha(pilha);
                return 0; // A série está desbalanceada se houver um fechamento sem um correspondente abertura
            }
            char abertura = pop(pilha);
            if ((expressao[i] == ')' && abertura != '(') ||
                (expressao[i] == ']' && abertura != '[') ||
                (expressao[i] == '}' && abertura != '{')) {
                liberarPilha(pilha);
                return 0; // A série está desbalanceada se houver um fechamento sem um correspondente abertura
            }
        }
    }

    int balanceada = vazia(pilha);
    liberarPilha(pilha);
    return balanceada;
}

int main() {
    const char* serie1 = "([])[]({})";
    const char* serie2 = "([)]";
    const char* serie3 = "((()";

    printf("Série 1 balanceada? %s\n", estaBalanceada(serie1) ? "Sim" : "Não");
    printf("Série 2 balanceada? %s\n", estaBalanceada(serie2) ? "Sim" : "Não");

    return 0;
}
