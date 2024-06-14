#include <stdio.h>

int contem( char s[], char c );

int main(){

char str[50], car;
	printf("Digite uma cadeia de caracteres: ");
	gets( str );
	printf("Digite um caractere: ");
	scanf(" %c", &car );
	contem( str, car );
	
return 0;
}
int contem( char s[], char c ){
	int i, cont = 0;
	for( i = 0 ; s[i] != 0 ; i++ )
		if( s[i] == c )
			cont++;
    printf("Contem: %d caracteres.\n", cont);
}