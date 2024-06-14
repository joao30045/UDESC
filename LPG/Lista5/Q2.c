#include <stdio.h>

void troca_valor(float *x, float *y);

int main(){

    float x, y;
    printf("Digite os valores para trocar.\n");
    scanf("%f%f", &x, &y);
    printf("Valor 1: %.2f\n", x);
    printf("Valor 2: %.2f\n", y);
    troca_valor( &x, &y);
    printf("Valor trocado 1: %.2f\n", x);
    printf("Valor trocado 2: %.2f\n", y);

return 0;
}
void troca_valor(float *x, float *y){
    float aux;
    aux = (*x);
    (*x) = (*y);
    (*y) = aux;
}