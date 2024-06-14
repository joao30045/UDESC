#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char *argv[]) {
	int n, i;
	printf("Quantos numeros? ");
	scanf("%d", &n);
	
	FILE *f = fopen("numeros.txt", "wt");
	if( f == NULL ){
		printf("Erro na abertura do arquivo!\n");
		return -1;
	}
	
	srand( time(0) );
	for( i = 0 ; i < n ; i++ )
		fprintf( f, "%d\n", rand() );
	
	fclose( f );
	
	system("numeros.txt");
	
	return 0;
}
