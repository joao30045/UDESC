#include <stdio.h>
#include <stdlib.h>

int **aloca_matriz( int l, int c );
void gera_valores( int **x, int l, int c );
void mostra_matriz( int **x, int l, int c );
void libera_matriz( int **x, int l );
int insere_linha( int ***x, int *pl, int c, int ln );

int main(int argc, char *argv[]) {
	int lin, col;
	printf("Quantas linhas? ");
	scanf("%d", &lin);
	printf("Quantas colunas? ");
	scanf("%d", &col);
	
	int **a = aloca_matriz( lin, col );
	gera_valores( a, lin, col );
	mostra_matriz( a, lin, col );
	
	int linha_nova;
	printf("Informe o indice da nova linha: ");
	scanf("%d", &linha_nova);
	
	if( ! insere_linha( &a, &lin, col, linha_nova ) )
		printf("A linha nova deve estar en 0 e %d!\n", lin);

	mostra_matriz( a, lin, col );
	
	// TO DO: fa�am a inser��o de uma nova coluna.
	
	libera_matriz( a, lin );
	
	return 0;
}

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
			x[i][j] = rand() % 1000 + 1;
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
	if( ln > *pl || ln < 0 ) // Nova linha n�o pode ser maior que o n�mero atual de linhas.
		return 0;
	
	(*pl)++; // Incrementa o n�mero de linhas;

	*x = realloc( *x, sizeof(int*) * *pl );
	
	int i;
	for( i = *pl - 1 ; i > ln ; i-- ) // Move os endere�os das linhas para baixo.
		(*x)[i] = (*x)[i-1];
	
	(*x)[ln] = malloc( sizeof(int) * c );
	
	for( i = 0 ; i < c ; i++ ){
		printf("[%d, %d]: ", ln, i);
		scanf("%d", & (*x)[ln][i] );
	}
	return 1;
}

