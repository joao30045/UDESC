#include <stdio.h>
#include <stdlib.h>
#include "Pilha.h"
#include <string.h>

int eh_palindrome( char palavra[] );

int main(int argc, char *argv[]) {
	char s[] = "123456765432";
	
	printf("%d\n", eh_palindrome( s ) );
	
	return 0;
}

int eh_palindrome( char palavra[] ){
	Pilha p1;
	int n = strlen( palavra );
	inicializa_pilha( &p1, n );
	char aux[n+1];
	int i;
	for( i = 0 ; palavra[i] != '\0' ; i++ ){
		empilha( &p1, palavra[i] );
	}
	i = 0;
	while( !pilha_vazia( p1 ) ){
		int temp;
		desempilha( &p1, &temp );
		aux[i] = temp;
		i++;
	}
	aux[i] = '\0';
	desaloca_pilha( &p1 );
	return strcmp( palavra, aux ) == 0;
}
