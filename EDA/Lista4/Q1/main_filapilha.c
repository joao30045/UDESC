#include <stdio.h>
#include <stdlib.h>
#include "Pilha.h"
#include "Fila.h"

#define N 5

int main(){
    Pilha p;
    Fila f;
    inicializa_pilha(&p, N);
    inicializa_fila(&f, N);
    int i, x, aux;
	for( i = 0 ; i < N ; i++ ){
		x = rand() % 100;
		printf("Inserindo %d...\n", x);
		if( inserir( &f, x ) == ERRO_FILA_CHEIA)
			printf("Fila cheia!\n");
	}
    mostra_fila(f);
    for(i = 0; i < N; i++){
        remover(&f, &aux);
        empilha(&p, aux);
    }
    mostra_pilha(p);
    while(!pilha_vazia(p)){
        desempilha(&p, &aux);
        inserir(&f, aux);
    }
    mostra_fila(f);


return 0;
}