#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

int contem( char s[], char c );

int main(int argc, char *argv[]) {
	setlocale( LC_ALL, "Portuguese" );
	
	char str[50], car;
	printf("Digite uma cadeia de caracteres: ");
	gets( str );
	printf("Digite um caractere: ");
	scanf(" %c", &car );
	
	if( contem( str, car ) )
		printf("'%s' contém %c\n", str, car);
	else
		printf("'%s' não contém %c\n", str, car);
	
	return 0;
}

int contem( char s[], char c ){
	int i;
	for( i = 0 ; s[i] != 0 ; i++ )
		if( s[i] == c )
			return 1;
	
	return 0;
}

