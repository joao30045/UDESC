#include <stdio.h>

void max_vetor(float vet[], int tam, float *pMax, int *pIndice);

int main(){

    float v[10], max;
    int tam, indice, i;
    printf("Digite o tamnho do vetor de até 10 posições.\n");
    scanf("%d", &tam);
    printf("Digite os valores do vetor.\n");
    for(i = 0; i < tam; i++){
        scanf("%f", v + i);
    }
    max_vetor(v, tam, &max, &indice);
    printf("O valor máximo é %.2f, na posição %d.\n", max, indice);

return 0;
}
void max_vetor(float vet[], int tam, float *pMax, int *pIndice){
    int i;
    *pMax = vet[0];
    *pIndice = 0;
    for(i = 1; i < tam; i++){
        if(*pMax < vet[i]){
            *pMax = vet[i];
            *pIndice = i;
        }
    }
}