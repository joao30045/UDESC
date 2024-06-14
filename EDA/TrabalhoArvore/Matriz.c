#include "Matriz.h"
#include <stdlib.h>
#include <stdio.h>

void inicializa_matriz( Matriz *p, int l, int c ){
	p->lin = l;
	p->col = c;
	p->dados = malloc( sizeof(int*) * l );
	int i,j;
	for( i = 0 ; i < l ; i++ ){
		p->dados[i] = malloc( sizeof(int) * c );
		for( j = 0 ; j < c ; j++ )
			p->dados[i][j] = 0;
	}
}

void mostra_matriz( Matriz m ){
	printf("Dados da matriz %dx%d:\n", m.lin, m.col);
	int i, j;
	for( i = 0 ; i < m.lin ; i++ ){
		for( j = 0 ; j < m.col ; j++ ){
			printf("%4d ", m.dados[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

int carrega_arquivo( char *nome_arquivo, Matriz *p ){
	FILE *f = fopen( nome_arquivo, "rt" );
	if( f == NULL)
		return 0; // Retorna 0 caso n�o abra o arquivo
	
	int lin, col, i, j;
	fscanf( f, "%d%d", &lin, &col );
	inicializa_matriz( p, lin, col );
	for( i = 0 ; i < p->lin ; i++ )
		for( j = 0 ; j < p->col ; j++ )
			fscanf( f, "%d", &p->dados[i][j]);
	
	return 1;
}

int set_valor( Matriz *p, int i, int j, int valor ){
	if( i < 0 || i >= p->lin || j < 0 || j > p->col )
		return COORDENADA_INVALIDA;
	
	p->dados[i][j] = valor;
	return 1;
}

int get_valor( Matriz *p, int i, int j, int *p_valor ){
	if( i < 0 || i >= p->lin || j < 0 || j > p->col )
		return COORDENADA_INVALIDA;
	
	*p_valor = p->dados[i][j];
	return 1;
}

int matrizes_iguais( Matriz m1, Matriz m2 ){
	if( m1.lin != m2.lin || m1.col != m2.col )
		return 0;

	int i, j;
	for( i = 0 ; i < m1.lin ; i++ )
		for( j = 0 ; j < m1.col ; j++ )
			if( m1.dados[i][j] != m2.dados[i][j] )
				return 0;
	
	return 1;
}

void desaloca_matriz( Matriz *p ){
	int i;
	for( i = 0 ; i < p->lin ; i++ )
		free( p->dados[i] );
	
	free( p->dados );
}

