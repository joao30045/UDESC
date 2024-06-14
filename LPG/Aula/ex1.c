#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#define LIN 6
#define COL 5

void mostra_matriz(int x[][COL], int l, int c){
        int i, j;
        printf("Dados da Matriz %d x %d =\n", l,c);
        for(i=0;i<l;i++){
            for(j=0;j<c;j++)
                printf("%3d ", x[i][j]);
            printf("\n");
        }
    }
void gera_valores(int x[][COL], int l, int c){
        srand(time(0));
        int i, j;
        for(i=0;i<l;i++)
            for(j=0;j<c;j++)
                x[i][j] = rand()%1000;
    } 
void mostra_vetor(int v[], int n){
    int i;
    for(i=0;i<n;i++)
        printf("%d : %d\n", i, v[i]);
}

int main(){

    int m[LIN][COL];
    gera_valores(m,LIN,COL);
    mostra_matriz(m,LIN,COL);
    int i;
    for(i=0;i<LIN;i++){
        printf("Linha %d:\n", i);
        mostra_vetor(m[i],COL);
    }
    printf("\n");
    mostra_vetor(&m, LIN*COL);

return 0; 
}