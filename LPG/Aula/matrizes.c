#include <stdio.h>
#include <stdlib.h>
#include "matrizes.h"

int **aloca_matriz( int l, int c ){
	int i;
	int **p = malloc( sizeof(int*) * l );
	for( i = 0 ; i < l ; i++ )
		p[i] = malloc( sizeof(int) * c );
	
	return p;
}

void gera_valores( int **x, int l, int c ){
	int i, j;
	for( i = 0 ; i < l ; i++ )
		for( j = 0 ; j < c ; j++ )
			x[i][j] = rand() % 10 + 1;
}

void mostra_matriz( int **x, int l, int c ){
	int i, j;
	printf("Matriz %dx%d:\n", l, c);
	for( i = 0 ; i < l ; i++ ){
		for( j = 0 ; j < c ; j++ )
			printf("%4d ", x[i][j]);
		printf("\n");
	}
	printf("\n");
}

void libera_matriz( int **x, int l ){
	int i;
	for( i = 0 ; i < l ; i++ )
		free( x[i] );
	free( x );
}

int insere_linha( int ***x, int *pl, int c, int ln ){
	if( ln > *pl || ln < 0 ) // Nova linha não pode ser maior que o número atual de linhas.
		return 0;
	
	(*pl)++; // Incrementa o número de linhas;

	*x = realloc( *x, sizeof(int*) * *pl );
	
	int i;
	for( i = *pl - 1 ; i > ln ; i-- ) // Move os endereços das linhas para baixo.
		(*x)[i] = (*x)[i-1];
	
	(*x)[ln] = malloc( sizeof(int) * c );
	
	for( i = 0 ; i < c ; i++ ){
		printf("[%d, %d]: ", ln, i);
		scanf("%d", & (*x)[ln][i] );
	}
	return 1;
}

int multiplica_matrizes( int **m1, int l1, int c1, int **m2, int l2, int c2, int ***m3, int *l3, int *c3 ){
	if( c1 != l2 )
		return 0; // Falso, matrizes incompativeis! :-(
	
	*l3 = l1;
	*c3 = c2;
	*m3 = aloca_matriz( *l3, *c3 );
	// Algoritmo da multiplicação:
	int i, j, k;
	for( i = 0 ; i < *l3 ; i++ )
		for( j = 0 ; j < *c3 ; j++ ){
			(*m3)[i][j] = 0;
			for( k = 0 ; k < c1 ; k++ )
				(*m3)[i][j] += m1[i][k]*m2[k][j];
			//(*m3)[i][j] = soma; // Código legado... :-)
		}

	return 1; // Sucesso.	
}


