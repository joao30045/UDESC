#include <stdio.h>

void min_matriz(float mat[3][4], float *pMin, int *pI, int *pJ);

int main(){

    float m[3][4], min;
    int i, j, min_i, min_j;
    printf("Digite os valores da matriz:\n");
    for(i = 0; i < 3; i++){
        for(j = 0; j < 4; j++){
          scanf("%f", &m[i][j]);
        }
    }
    min_matriz(m, &min, &min_i, &min_j);
    printf("O menor valor : %.2f na posição %d,%d", min, min_i, min_j);

return 0;
}
void min_matriz(float mat[3][4], float *pMin, int *pI, int *pJ){
    *pMin = mat[0][0];
    *pI = 0;
    *pJ = 0;
    int i, j;
    for(i = 1; i < 3; i++){
        for(j = 1; j < 4; j++){
            if(mat[i][j] < (*pMin)){
                *pMin = mat[i][j];
                *pI = i;
                *pJ = j;
            }
        }
    }
}