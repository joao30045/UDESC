#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Pilha.h"

int main(){
    char s[] = "((2 + 4) * 6) / 3";
    Pilha p;
    inicializa_pilha(&p, strlen(s));
    int i, aux;
    for(i = 0; s[i] != '\0'; i++){
        if(s[i] == '('){
            empilha(&p, s[i]);
        }else if(s[i] == ')'){
            if(pilha_vazia(p)){
                printf("Fecha parênteses sem abre parênteses (posição %d)\n", i);
                return 0;
            }else{
                desempilha(&p, &aux);
            }
        }
    }
    if(!pilha_vazia(p)){
        printf("Há parênteses abertos que não foram fechados");
        return 0;
    }
    printf("OK\n");

return 0;
}