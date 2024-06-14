#include <stdio.h>
#include <stdlib.h>
#include "matrizes.h"

#define L1 2
#define C1 2
#define L2 4
#define C2 3

int main(int argc, char *argv[]) {
	int m1[L1][C1], m2[L2][C2];
	
	gera_valores( L1, C1, m1 );
	mostra_matriz( L1, C1, m1 );	
	//mostra_vetor( m1, L1*C1 );
	
	int i, j, soma = 0, cont = 0;
	for( i = 0 ; i < L1 ; i++ )
		for( j = 0 ; j < C1 ; j++ )
			if( m1[i][j] % 2 == 0 ){
				soma += m1[i][j];
				cont++;
			}
	if( cont == 0 )
		printf("Nao ha numeros pares!\n");
	else
		printf("A media dos %d numeros eh %.3f\n", cont, (float)soma/cont);	
	
	//gera_valores( L2, C2, m2 );
	//mostra_matriz( L2, C2, m2 );
	//mostra_vetor( m2, L2*C2 );
	
	return 0;
}


