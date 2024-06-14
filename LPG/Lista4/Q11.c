#include <stdio.h>

int remover( char str[], char s );

int main(){

    char str[50], s;
	printf("Digite uma cadeia de caracteres: ");
	gets( str );
	printf("Digite um caractere: ");
	scanf(" %c", &s);
    remover(str, s);
    printf("%s\n", str);

return 0;
}
int remover( char str[], char s ){
    int i,j;
    i = 0;
    for( i = 0 ; str[i] != 0 ; i++ ){
		if( str[i] == s ){
            for (j = i; j < str[i]; j++){
                str[j]=str[j+1];   
            }
        i--;
        } 
    }
}