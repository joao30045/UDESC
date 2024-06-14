#include <stdio.h>
#include <stdlib.h>
#include "Pilha.h"

void concatena( Pilha *p1, Pilha *p2 );

int main(){
    Pilha p1, p2;
    inicializa_pilha(&p1, 10);
    empilha(&p1, 2);
    inicializa_pilha(&p2, 10);
    empilha(&p2, 3);
    concatena(&p1, &p2);
    mostra_pilha(p1);

return 0;
}
void concatena( Pilha *p1, Pilha *p2 ){
    int aux;
    if(!pilha_vazia(*p2)){
    desempilha(p2, &aux);
    empilha(p1, aux);
    }
}