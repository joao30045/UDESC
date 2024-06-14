#include <stdio.h>
#include <stdlib.h>
#include "Pilha.h"
#include <string.h>

void inverte_string( char *s, int ini, int fim );

int main(int argc, char *argv[]) {
	char s[] = " Mais   Alguma  coisa teste";
	
	//inverte_string( s, 0, 5 );
	int i, ini;
	for( i = 0 ; s[i] != '\0' ; i++ ){
		while( s[i] == ' ' ){
			i++;
		}
		ini = i;
		while( s[i] != ' ' && s[i] != '\0' ){
			i++;
		}
		inverte_string( s, ini, i-1 );
	}	
	
	printf("'%s'\n", s);
	
	return 0;
}

void inverte_string( char *s, int ini, int fim ){
	Pilha p1;
	inicializa_pilha( &p1, fim-ini+1 );
	
	int i;
	for( i = ini ; i <= fim ; i++ )
		empilha( &p1, s[i] );
	
	for( i = ini ; i <= fim ; i++ ){
		int aux;
		desempilha( &p1, &aux );
		s[i] = aux;
	}
	desaloca_pilha( &p1 );
}

