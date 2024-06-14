#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *aloca_string( char *msg );

int main(int argc, char *argv[]) {
	int n = 0;
	char **lista = NULL;
	
	char *str = aloca_string("Digite algo: ");
	
	while( strcmp( str, "sair") != 0 ){
		n++;
		lista = realloc( lista, sizeof(char*) * n );
		lista[n-1] = str;
		str = aloca_string("Digite algo: ");
	}
	
	int i;
	for( i = 0 ; i < n ; i++ )
		printf("%d : %s\n", i, lista[i]);
		
	for( i = 0 ; i < n ; i++ )
		free( lista[i] );
	free( lista );
	
	free( str );
	
	return 0;
}

char *aloca_string( char *msg ){
	printf("%s", msg);
	char buffer[101];
	scanf(" %[^\n]", buffer);
	char *p = malloc( strlen(buffer) + 1 );
	strcpy( p, buffer );
	return p;
}

