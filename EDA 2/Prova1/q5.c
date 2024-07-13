#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int min(int a, int b, int c){
    int min = b;
    if (a < min)
        min = a;
    if (c < min)
        min = c;
    return min;
}

int main(){

    char* A = "exercício";    
    char* B = "exército";

    int m = strlen(A);
    int n = strlen(B);
    int M[m][n];

    for(int i = 0; i < m; i++){
        M[i][0] = i;
    }
    for(int j = 0; j < n; j++){
        M[0][j] = j;
    }
    for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
            int custo;
            if(A[i] == B[j]){
                custo = 0;
            }else{
                custo = 1;
            }
        M[i][j] = min(M[i - 1][j] + 1, 
                    M[i - 1][j - 1] + custo, 
                    M[i][j -1] + 1);    
        } 
    }
    printf("Matriz de custos:\n");
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            printf("%d ", M[i][j]);
        }
        printf("\n");
    }
    printf("Distância de edição: %d\n", M[m-1][n-1]);
return 0;
}