#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define LIN 3
#define COL 4

void gera_valores( int m[][COL], int lin, int col );
void mostra_matriz( int m[][COL], int lin, int col );

void mostra_vetor( int v[], int n );

int main(int argc, char *argv[]) {
	int mat[LIN][COL];
	int x[3][4];
	
	srand( time(0) );
	
	gera_valores( mat, LIN, COL );
	mostra_matriz( mat, LIN, COL );
	
	/*
	// Não é recomendável!
	gera_valores( x, 3, 4 );
	mostra_matriz( x, 3, 4 );
	*/
	
	/*
	int vet[5] = { 3, 67, 89, 234, -4 };
	mostra_vetor( vet, 5 );	
	*/
	
	/*
	// Mostra cada linha como um vetor separado.
	int i;
	for( i = 0 ; i < LIN ; i++ )
		mostra_vetor( mat[i], COL );
	*/
	
	// Mostra toda a matriz como um único vetor	.
	mostra_vetor( mat, LIN*COL );
		
	return 0;
}

void gera_valores( int m[][COL], int lin, int col ){
	//srand( time(0) );
	int i, j;
	for( i = 0; i < lin ; i++ )
		for( j = 0; j < col ; j++ )
			m[i][j] = rand() % 10000;
}

void mostra_matriz( int m[][COL], int lin, int col ){
	int i, j;
	printf("Dados da Matriz (%dx%d):\n", lin, col);
	for( i = 0; i < lin ; i++ ){
		for( j = 0; j < col ; j++ )
			printf("%4d ", m[i][j] );
		printf("\n");
	}
	printf("\n");
}

void mostra_vetor( int v[], int n ){
	int i;
	printf("Dados do vetor (cap. %d):\n", n);
	for( i = 0 ; i < n ; i++ )
		printf("v[%d]: %d\n", i, v[i]);
	printf("\n");
}


