/*
Feito pelos alunos João Pedro Garcia Guedes e João Pedro Patricio do Nascimento
*/
#include <stdio.h>
#include <stdlib.h>
#include "arvore_n_aria.h"
#include "FilaGenerica.h"
#include "Matriz.h"

int main(){

    Matriz m;
    FilaGenerica f;
    Arvore a;
    int i, v, x;
    int *vetorStatus; 


    printf("Digite o vertice inicial do percurso:\n");
    scanf("%d", &v);
    v--;
    inicializa_Arvore(&a, v);
    inicializa_fila(&f, m.col, sizeof(int));
    carrega_arquivo("entrada.txt", &m);

    vetorStatus = malloc(m.col * sizeof(int));
	for(i = 0; i < m.col; i++){
		vetorStatus[i] = 0;
	}

    vetorStatus[v] = 1;
    inserir(&f, &v);

    while(!fila_vazia(f)){
		remover(&f, &x);
		for(i = 0; i < m.col; i++){
			if(m.dados[x][i] == 1 && vetorStatus[i] == 0){
				vetorStatus[i] = 1;
                insere_filho(a, x, i);
				inserir(&f, &i);
			}
		}
	}
    gera_xml(a, 1);
    desaloca_arvore(&a);
    desaloca_fila(&f);
    desaloca_matriz(&m);
    free(vetorStatus);

return 0;
}