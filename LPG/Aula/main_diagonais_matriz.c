#include <stdio.h>
#include <stdlib.h>

#define N 6

int main(int argc, char *argv[]) {
	int m[N][N];
	
	int i, j;
	for( i = 0 ; i < N ; i++ ){
		for( j = 0 ; j < N ; j++ ){
			m[i][j] = rand()%1000;
			printf("%3d ", m[i][j]);
		}
		printf("\n");
	}
	printf("\n");	
	
	printf("Diagonal Principal: ");
	/*
	for( i = 0 ; i < N ; i++ )
		for( j = 0 ; j < N ; j++ )
			if( i == j )
				printf("%d ", m[i][j]);
	printf("\n");
	*/
	for( i = 0 ; i < N ; i++ )
		printf("%d ", m[i][i]);
	printf("\n");
	
	printf("Diagonal Secundaria: ");
	/*
	for( i = 0 ; i < N ; i++ )
		for( j = 0 ; j < N ; j++ )
			if( i + j == N-1 )
				printf("%d ", m[i][j]);
	printf("\n");
	*/
	for( i = 0 ; i < N ; i++ )
		printf("%d ", m[i][N-1-i]);
	printf("\n");
		
	return 0;
}
