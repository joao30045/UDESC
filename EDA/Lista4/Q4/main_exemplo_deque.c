#include <stdio.h>
#include <stdlib.h>
#include "Deque.h"

#define N 5

int main(int argc, char *argv[]) {
	Deque f1;
	inicializa_deque( &f1, N );
	
	int i, x;
	for( i = 0 ; i < N ; i++ ){
		x = rand() % 100;
		printf("Inserindo %d...\n", x);
		if( inserir_fim( &f1, x ) == ERRO_FILA_CHEIA)
			printf("Fila cheia!\n");
	}
	
	mostra_deque( f1 );
	
	for( i = 0 ; i < N/2 ; i++ ){
		remover_inicio( &f1, &x );
		printf("Removido o valor %d...\n", x);
	}
	
	mostra_deque( f1 );
	
	for( i = 0 ; i < N/2 ; i++ ){
		x = rand() % 100;
		printf("Inserindo %d...\n", x);
		if( inserir_fim( &f1, x ) == ERRO_FILA_CHEIA)
			printf("Fila cheia!\n");
	}
	mostra_deque( f1 );
	
	desaloca_deque( &f1 );
	
	return 0;
}
