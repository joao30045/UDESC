#include <stdio.h>

void trim( char str[] );

int main(){

    char str[50], car;
	printf("Digite uma cadeia de caracteres: ");
	gets( str );
    trim(str);
    printf("%s\n", str);

return 0;
}
void trim( char str[] ){
    int i = 0, cont = 0;
    while(str[cont] == ' ' || str[cont] == '\n' || str[cont] == '\t'){
        cont++;
    }
    if(cont != 0){
        while (str[i + cont] != '\0'){
            str[i] = str[i + cont];
            i++;
        }
    str[i] = '\0';
    }
}