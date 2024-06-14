#include <stdio.h>
#include <string.h>

int main(){

    char str[50];
    printf("Digite a string:\n");
    gets(str);
    int i , j, palindromo;
    j = strlen(str) - 1;
    for(i = 0; i < j; i++){
        if(str[i] != str[j - i]){
        palindromo = 1;
        break;
        }
    }
    if(palindromo == 1){
        printf("%s não é palíndromo.\n", str);
    }else{
    printf("%s é palíndromo.\n", str);
    }
return 0;
}