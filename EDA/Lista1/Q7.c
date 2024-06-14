#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *repetidor(char *s, int n);

int main(){

    char str[500];
    printf("Digite uma string para fazer cópias.\n");
    gets(str);
    int n;
    printf("Copiar quantas vezes?\n");
    scanf("%d", &n);
    char * str_repetida = repetidor(str,n);
    printf("A string repetida %d vezes é\n%s \n", n, str_repetida);
    free(str_repetida);

return 0;
}
char *repetidor(char *s, int n){
    int tam = strlen(s);
    int novo = n * tam;
    char * str = malloc(tam * n + 1);
    int i, j = 0;
    for(i = 0; i < novo;){
        for(j = 0; j < tam;){
            str[i] = s[j];
            i++;
            j++;
        }
    }
    str[i] = 0;
return str;
}