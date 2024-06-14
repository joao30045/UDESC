#include <stdio.h>

void inverte(char s[]);

int main(){

    char str[50];
	printf("Digite uma cadeia de caracteres: ");
	gets( str );
    inverte(str);
    printf("%s\n", str);

return 0;
}
void inverte(char s[]){

    int i = 0, j;
    for(j = 0; s[j] != 0; j++);
    j--;
    while(i < j){
        char aux = s[i];
        s[i] = s[j];
        s[j] = aux;
        i++;
        j--;
    }
}