#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int ** prod_mat(int **A, int**B, int m, int n, int p);

int main(){

    int a, b, c;
    printf("Digite a quantidade de linhas da 1 matriz.\n");
    scanf("%d", &a);
    printf("Digite a quantidade de colunas da 1 matriz e linhas da 2 matriz.\n");
    scanf("%d", &b);
    printf("Digite a quantidade de colunas da 1 matriz.\n");
    scanf("%d", &c);
    int ** m = malloc(sizeof(int*) * a);
    int i, j;
    for(i = 0; i < a; i++){
        m[i] = malloc(sizeof(int*) * b);
        for(j = 0; j < b; j++){
            m[i][j] = rand() % 10;
        }
    }
    int ** m2 = malloc(sizeof(int*) * b);
    for(i = 0; i < b; i++){
        m2[i] = malloc(sizeof(int*) * c);
        for(j = 0; j < c; j++){
            m2[i][j] = rand() % 10;
        }
    }
    printf("As matrizes são:\n");
    printf("M1 = \n");
    for(i = 0; i < a; i++){
        for(j = 0; j < b; j++){
        printf("%4d", m[i][j]);
        }
        printf("\n");
    }
    printf("M2 = \n");
    for(i = 0; i < b; i++){
        for(j = 0; j < c; j++){
        printf("%4d", m2[i][j]);
        }
        printf("\n");
    }
    int ** mult = prod_mat(m,m2,a,b,c);
    printf("O produto entres as duas matrizes é:\n");
    for(i = 0; i < a; i++){
        for(j = 0; j < c; j++){
        printf("%4d", mult[i][j]);
        }
        printf("\n");
    }
    for(i = 0; i < a; i++){
        free(m[i]);
        free(mult[i]);
    }
    for(i = 0; i < b; i++){
        free(m2[i]);
    }
return 0;
}
int ** prod_mat(int **m, int**m2, int a, int b, int c){
    int ** prod = malloc(sizeof(int*) * a);
    int i, j, k;
    for(i = 0; i < a; i++){
        prod[i] = malloc(sizeof(int*) * c);
    }
    for(i = 0; i < a; i++){
        for(j = 0; j < c; j++){
            prod[i][j] = 0;
            for(k = 0; k < b; k++){
                prod[i][j] += m[i][k] * m2[k][j];
            }
        }
    }
return prod;
}