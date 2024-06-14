#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	FILE *f = fopen( "numeros.txt", "rt" );
	if( f == NULL ){
		printf("Erro na abertura do arquivo!\n");
		return -1;
	}
	
	int x;
	
	while( fscanf( f, "%d", &x ) == 1 ){
		printf("%d\n", x );
	}
	
	fclose( f );
	
	return 0;
}
