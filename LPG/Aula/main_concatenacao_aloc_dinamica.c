#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *concat( char *s1, char *s2 );

int main(int argc, char *argv[]) {
	char a[50] = "Alo Teste ";
	char b[50] = "Mundo";
	
	printf("A = '%s', B = '%s'\n", a, b);
	char *c = concat( a, b );
	printf("A = '%s', B = '%s', C = '%s'\n", a, b, c);
	
	free( c );
	
	return 0;
}

char *concat( char *s1, char *s2 ){
	int n1 = strlen( s1 );
	int n2 = strlen( s2 );
	
	char *p = malloc( n1 + n2 + 1 );
	
	strcpy( p, s1 );
	strcpy( p + n1, s2 ); // Usando aritmética de ponteiros...
	
	return p;
}

