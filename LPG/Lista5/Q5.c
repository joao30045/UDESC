#include <stdio.h>
#include <stdlib.h>

void max_min(int vet[], int tam, int *pMin, int *pMax);

int main(){

    int v[10];
    int tam, min, max, i;
    printf("Digite o tamnho do vetor de até 10 posições.\n");
    scanf("%d", &tam);
    printf("Digite os valores do vetor.\n");
    for(i = 0; i < tam; i++){
        scanf("%d", v + i);
    }
    max_min(v, tam, &min, &max);
    printf("O valor maximo é: %d.\n", max);
    printf("O valor minimo é: %d.\n", min);

return 0;
}
void max_min(int vet[], int tam, int *pMin, int *pMax){
    int i;
    *pMax = vet[0];
    *pMin = vet[0];

    for(i = 1; i< tam; i++){
        if(*pMax < vet[i]){
            *pMax = vet[i];
        }
        if(*pMin > vet[i]){
            *pMin = vet[i];
        }
    }
}