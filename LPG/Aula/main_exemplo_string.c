#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
	char nome[50], nome2[50];
	printf("Digite seu nome: ");
	//scanf("%s", nome );
	//gets( nome );
	scanf("%[^\n]%*c", nome);
	//fgets( nome, 50, stdin );
	
	printf("Digite seu outro nome: ");
	//scanf("%s", nome2 );
	gets( nome2 );
	
	printf("Seu nome: '%s'\n", nome);
	printf("Seu outro nome: '%s'\n", nome2);
	
	int i;
	for( i = 0 ; nome[i] != 0 ; i++ )
		printf("nome[%d]: %c (%d)\n", i, nome[i], nome[i] );
	printf("\n");
	
	
		
	return 0;
}
