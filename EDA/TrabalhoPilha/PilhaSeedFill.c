#include <stdio.h>
#include <stdlib.h>
#include "Matriz.h"
#include "PilhaGenerica.h"

typedef struct{
	int x, y;
}Pixel; 

int main(){
	Matriz m;	
	PilhaGenerica p;
    Pixel semente, atual, vizinho;
	int a, b, cor, corOriginal, c, d, cor_aux1, e, f, cor_aux2, i, j;
    //Vetores para percorrer as quatro direções no for linha 57
    int vx[4] = {1, -1, 0, 0}, vy[4] = {0, 0, 1, -1};

	inicializa_pilha(&p, m.lin * m.col, sizeof(Pixel));
	carrega_arquivo("entrada.txt", &m);
	mostra_matriz(m);

	printf("Digite as coordenadas (x,y) do pixel semente:\n");
	scanf("%d%d", &a, &b);
    //Verifica se as coordenadas estão dentro da matriz
    if(a < 0 || a > m.lin - 1 || b < 0 || b > m.col - 1){
        printf("As coordenadas inseridas não pertencem a matriz.\n");
        return 0;
    }

	printf("Digite o número que representa a nova cor:\n");
	scanf("%d", &cor);
	get_valor(&m, a, b, &corOriginal);
    //Verifica se a nova cor é igual a original
	if(corOriginal == cor){
        printf("A nova cor é a mesma da original.\n");
		return 0;
	}
    //Empilha as coordenadas do pixel semente
    semente.x = a;
    semente.y = b;
    empilha(&p, &semente);

    while(!pilha_vazia(p)) {
        //Desempilha as coordenadas que serão utilizadas
        desempilha(&p, &atual);
        d = atual.y;
        c = atual.x;
        get_valor(&m, c, d, &cor_aux1);
        //Verifica se faz parte da matriz
        if(c >= 0 && c < m.lin && d >= 0 && d < m.col){
            //Se a cor for diferente o pixel recebe a nova cor
            if(cor_aux1 != cor){
                set_valor(&m, c, d, cor);
                //Percorre as quatro direções ao redor da coordenada: direita, esquerda, cima, baixo
                for(i = 0; i < 4; i++) {
                    e = c + vx[i];
                    f = d + vy[i];
                    get_valor(&m, e, f, &cor_aux2);
                    //Se o pixel possui a cor original, as coordenadas serão empilhadas
                    if(cor_aux2 == corOriginal) {
                        vizinho.x = e;
                        vizinho.y = f;
                        empilha(&p, &vizinho);
                    }
                }
            }
        }
    }
    FILE *saida = fopen("saida.txt", "w");
    if(saida == NULL){
        printf("Erro ao abrir o arquivo de saída.\n");
        return 1;
    }
    for(i = 0; i < m.lin; i++) {
        for (j = 0; j < m.col; j++) {
            fprintf(saida, "%d ", m.dados[i][j]);
        }
        fprintf(saida, "\n");
    }
    fclose(saida);
	mostra_matriz(m);
	desaloca_pilha(&p);
	desaloca_matriz(&m);

return 0;
}