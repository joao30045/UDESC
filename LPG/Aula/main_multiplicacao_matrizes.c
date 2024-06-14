#include <stdio.h>
#include <stdlib.h>
#include "matrizes.h"

int main(int argc, char *argv[]) {
	int lin_a, col_a;
	printf("Quantas linhas (A)? ");
	scanf("%d", &lin_a);
	printf("Quantas colunas (A)? ");
	scanf("%d", &col_a);
	
	int **a = aloca_matriz( lin_a, col_a );
	gera_valores( a, lin_a, col_a );	
	mostra_matriz( a, lin_a, col_a );

	int lin_b, col_b;
	printf("Quantas linhas (B)? ");
	scanf("%d", &lin_b);
	printf("Quantas colunas (B)? ");
	scanf("%d", &col_b);
	
	int **b = aloca_matriz( lin_b, col_b );
	gera_valores( b, lin_b, col_b );	
	mostra_matriz( b, lin_b, col_b );

	int **c, lin_c, col_c;
	
	if( multiplica_matrizes( a, lin_a, col_a, b, lin_b, col_b, &c, &lin_c, &col_c ) ){
		mostra_matriz( c, lin_c, col_c );
		libera_matriz( c, lin_c );
	}
	else
		printf("Matrizes incompativeis!\n");
	
	
	libera_matriz( a, lin_a );
	libera_matriz( b, lin_b );
	
	return 0;
}

