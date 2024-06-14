#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "FilaGenerica.h"
#include "Matriz.h"
#include "PilhaGenerica.h"

#define N m.lin

void mostra_int( void *x ){
	int *p = x;
	printf("%d\n", *p);
}

int main(int argc, char *argv[]) {
	Matriz m;
	FilaGenerica f;
	int a, b, c, i, x;
	int *vetorStatus, *vetorAntecessores; 
	vetorStatus = malloc(N * sizeof(int));
	for(i = 0; i < N; i++){
		vetorStatus[i] = 0;
	}
	vetorAntecessores = malloc(N * sizeof(int));
	for(i = 0; i < N; i++){
		vetorAntecessores[i] = 0;
	}
	inicializa_fila(&f, N, sizeof(int));
	carrega_arquivo("entrada.txt", &m);

	printf("Qual o vertice inicial A do percurso?\n");
	scanf("%d", &a);
	printf("Qual o vertice final B do percurso?\n");
	scanf("%d", &b);
	//Verifica se o vértice está dentro da matriz
	if(a < 0 || a > N || b < 0 || b > N){
		printf("Insira vértices válidos\n");
		return 0;
	}

	//Transforma o número do vértice para usar com a matriz
	a -= 1;
	b -= 1; 
	vetorStatus[a] = 1;
	inserir(&f, &a);
	bool achou = false;
	
	while(!fila_vazia(f) && !achou){
		remover(&f, &x);
		if(x == b){
			achou = true;
		}else{
			//Percorre os vértices adjacentes
			for(i = 0; i < N; i++){
				if(m.dados[x][i] == 1 && vetorStatus[i] == 0){
					vetorStatus[i] = 1;
					vetorAntecessores[i] = x;
					inserir(&f, &i);
				}
			}
		}
	}
	/*
	printf("Vetor de antecessores\n");
	for(i = 0; i < N; i++){
		printf("%d\n", vetorAntecessores[i]);
	}
	printf("Vetor de status\n");
	for(i = 0; i < N; i++){
		printf("%d\n", vetorStatus[i]);
	}
	*/
	if(achou){
		PilhaGenerica p;
		inicializa_pilha(&p, N, sizeof(int));
		while(x != a){
			empilha(&p, &x);
			x = vetorAntecessores[x];
		}
		empilha(&p, &a);
		printf("Caminho de A ate B\n");
		while(!pilha_vazia(p)){
			desempilha(&p, &c);
			//Transforma o número para o usuário
			printf("%d\n", c + 1);
		}
		desaloca_pilha(&p);
	}else{
		printf("B nao e alcancavel a partir de A!\n");
	}

	desaloca_fila(&f);
	desaloca_matriz(&m);
	free(vetorAntecessores);
	free(vetorStatus);

return 0;
}