#include <stdio.h>
#include <stdlib.h>
#include "Pilha.h"
#include <string.h>

void inverte_string( char *s );

int main(int argc, char *argv[]) {
	char str[100] = "Alguma coisa diferente";
	//printf("sizeof do vetor: %d\n", sizeof( str) );
	
	inverte_string( str );
	
	printf("%s\n", str );
	
	return 0;
}

void inverte_string( char *s ){
	//printf("sizeof do ponteiro: %d\n", sizeof( s ) );
	Pilha p1;
	inicializa_pilha( &p1, strlen( s ) );
	
	int i;
	for( i = 0 ; s[i] != '\0' ; i++ )
		empilha( &p1, s[i] );
	
	//mostra_pilha( p1 );
	
	i = 0;
	while( !pilha_vazia( p1 ) ){
		int aux;
		desempilha( &p1, &aux );
		s[i] = aux;
		i++;
	}
	s[i] = '\0'; // Redundante aqui!
}

