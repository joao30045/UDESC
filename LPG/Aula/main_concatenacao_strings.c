#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void concat( char *s1, char *s2 );

int main(int argc, char *argv[]) {
	char a[50] = "Alo";
	char b[50] = "Mundo";
	
	printf("A = '%s', B = '%s'\n", a, b);
	//strcat( a, b );
	concat( a, b );
	printf("A = '%s', B = '%s'\n", a, b);
	
	return 0;
}

void concat( char *s1, char *s2 ){
	int i, n = strlen( s1 );
	/*
	for( i = 0 ; s2[i] != 0 ; i++ )
		s1[n+i] = s2[i];
	s1[n+i] = 0;
	*/
	strcpy( s1 + n, s2 ); // Usando aritmética de ponteiros...
}

