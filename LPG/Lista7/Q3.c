#include <stdio.h>
#include <stdlib.h>

int *aloca_vetor(int valor);

int main(){
    int n, v, i, j;
    printf("Digite quantos números serão trabalhados.\n");
    scanf("%d", &n);
    int **p = malloc(sizeof(int*)*n);
    for(i = 0; i < n; i++){
        scanf("%d", &v);
        p[i] = aloca_vetor(v);
    }
    printf("Modelo gerado:\n");
    for(i = 0; i < n; i++){
        for(j = 0; p[i][j] != 0; j++){
            printf("%d", p[i][j]);
        }
    printf("\n");
    }
    for(i = 0; i < n; i++){
        free(p[i]);
    }
    free(p);
}
int *aloca_vetor(int valor){
    int *p = malloc(sizeof(int) * 2);
    int cont = 2, i;
    p[0] = valor;
    for(i = 1; i <= valor; i++){
        if(valor % i == 0){
            cont++;
            p = realloc(p, sizeof(int) * cont);
            p[cont - 2] = i;
        }
    }
    p[cont - 1] = 0;
    return p;
}