#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
	char s1[50], s2[50];
	printf("Digite algo: ");
	gets( s1 );
	printf("Digite algo de novo: ");
	gets( s2 );
	
	printf("S1: '%s' e S2: '%s'\n", s1, s2);
	
	int n = strcmp( s1, s2 );
	if( n == 0 )
		printf("S1 == S2\n");
	else
		if( n > 0 )
			printf("S1 > S2\n");
		else
			printf("S1 < S2\n");
			
	// Vamos testar a concatenação usando a função strcat();
	
	//strcat( s1, s1 ); // Concatenando s1 consigo mesma.
	//printf("S1: '%s' e S2: '%s'\n", s1, s2);
	
	strcat( s1, s2 );
	printf("Apos a concatenacao:\n");
	printf("S1: '%s' e S2: '%s'\n", s1, s2);
	
	strcat( s1, s2 );
	printf("Apos a concatenacao (de novo):\n");
	printf("S1: '%s' e S2: '%s'\n", s1, s2);
	
	return 0;
}
