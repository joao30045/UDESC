#include <stdio.h>
#include <stdlib.h>

int ** aloca_matriz_quadrada(int n);
int *diagonal_secundaria(int **matriz, int ordem);

int main(){

    int n;
    printf("Qual o tamanho da matriz a ser criada?\n");
    scanf("%d", &n);
    int ** matriz = aloca_matriz_quadrada(n);
    int i, j;
    for(i = 0; i < n; i++){
        for(j = 0; j < n; j++){
            matriz[i][j] = rand() % 1000;
        }
    }
    printf("A matriz %d x %d, gerada aleatoriamente\n", n, n);
    for(i = 0; i < n; i++){
        for(j = 0; j < n; j++){
            printf("%4d", matriz[i][j]);
        }
        printf("\n");
    }
    printf("no espaço da memória %p.\n", matriz);
    int * diag_sec = diagonal_secundaria(matriz, n);
    printf("A diagonal secundária é\n");
    for(i = 0; i < n; i++){
        printf("%4d", diag_sec[i]);
    }
    printf("\n");
    free(diag_sec);
    for (i = 0; i < n; i++){
        free(matriz[i]);
    }
    free(matriz);
    
return 0;
}
int ** aloca_matriz_quadrada(int n){
    int ** p = malloc(n * sizeof(int*));
    int i;
    for(i = 0; i < n; i++){
        p[i] = malloc(n * sizeof(int));
    }
    return p;
}
int *diagonal_secundaria(int **matriz, int n){
    int * diag = malloc(n * sizeof(int));
    int i;
    for(i = 0; i < n; i++){
        diag[i] = matriz[i][n - 1 - i];
    }
    return diag;
}