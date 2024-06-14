#include <stdio.h>
#include <stdlib.h>
#include "Fila_Nova.h"

#define N 5

int main(int argc, char *argv[]) {
	Fila_Nova f1;
	inicializa_fila( &f1, N );
	
	int i, x;
	for( i = 0 ; i < N ; i++ ){
		x = rand() % 100;
		printf("Inserindo %d...\n", x);
		if( inserir( &f1, x ) == ERRO_FILA_CHEIA)
			printf("Fila cheia!\n");
	}
	
	mostra_fila( f1 );
	
	for( i = 0 ; i < N/2 ; i++ ){
		remover( &f1, &x );
		printf("Removido o valor %d...\n", x);
	}
	
	mostra_fila( f1 );
	
	for( i = 0 ; i < N/2 ; i++ ){
		x = rand() % 100;
		printf("Inserindo %d...\n", x);
		if( inserir( &f1, x ) == ERRO_FILA_CHEIA)
			printf("Fila cheia!\n");
	}
	mostra_fila( f1 );
	
	desaloca_fila( &f1 );
	
	return 0;
}
