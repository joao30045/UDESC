#include <stdio.h>
#include <stdlib.h>
#include "Lista.h"

Lista gera_lista( int n );
void mostra_int( void *x );

int main(int argc, char *argv[]) {
	Lista a = gera_lista( 2 );	
	Lista b = gera_lista( 3 );
	
	printf("Antes:\n\n");
	mostra_lista( a, mostra_int );
	mostra_lista( b, mostra_int );
	
	concatena( &a, &b );
	
	printf("Depois:\n\n");
	mostra_lista( a, mostra_int );
	mostra_lista( b, mostra_int );
	
	limpa_lista( &a );
	limpa_lista( &b );
	return 0;
}

Lista gera_lista( int n ){
	Lista l;
	inicializa_lista( &l, sizeof(int) );
	int i, valor;
	for( i = 0 ; i < n ; i++ ){
		valor = rand() % 100 + 1;
		insere_fim( &l, &valor );
	}
	return l;
}

void mostra_int( void *x ){
	printf("%d\n", *(int*)x);
}

