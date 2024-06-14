#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 20

int *busca( int *v, int n, int chave );
int *busca_v2( int *v, int n, int chave );

int main(int argc, char *argv[]) {
	int vet[N];
	int i;
	srand( time(0) );
	for( i = 0 ; i < N ; i++ )
		vet[i] = rand() % 1000 + 1;
	
	vet[3] = 112;
	vet[12] = 112;
	vet[19] = 112;
	
	for( i = 0 ; i < N ; i++ )
		printf("%d : %d\n", i, vet[i] );
		
	int chave;
	printf("Digite o valor a ser buscado: ");
	scanf("%d", &chave);
	
	int *indices = busca_v2( vet, N, chave );
	
	if( indices[0] == -1 )
		printf("Valor nao encontrado!\n");
	else{
		printf("Valor encontrado em: ");
		for( i = 0 ; indices[i] != -1 ; i++ )
			printf("%d ", indices[i] );
		printf("\n");
	}
	
	free( indices );
	return 0;
}

int *busca( int *v, int n, int chave ){
	int i, cont = 0;
	for( i = 0 ; i < n ; i++ )
		if( v[i] == chave )
			cont++;
	
	int *p = malloc( sizeof(int) * (cont+1) );
	
	int j = 0;
	for( i = 0 ; i < n ; i++ )
		if( v[i] == chave ){
			p[j] = i;
			j++;
		}
	
	p[j] = -1;
	return p;
}

int *busca_v2( int *v, int n, int chave ){
	int *p = malloc( sizeof(int) );
	int i, cont = 1;
	for( i = 0 ; i < n ; i++ )
		if( v[i] == chave ){
			p = realloc( p , sizeof( int ) * (cont+1) );
			p[cont-1] = i;
			cont++;
		}
	
	p[cont-1] = -1;
	return p;
}

