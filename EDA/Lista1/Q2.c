#include <stdio.h>

void calcula_circulo(float raio, float *pPerimetro, float *pArea);

int main(){

    float raio, pPerimetro, pArea;
    printf("Digite o valor do raio.\n");
    scanf("%f", &raio);
    calcula_circulo(raio, &pPerimetro, &pArea);
    printf("Para o círculo de raio %.2f: \n", raio);
    printf("Perimetro de %.2f.\n", pPerimetro);
    printf("Área de %.2f.\n", pArea);

return 0;
}
void calcula_circulo(float raio, float *pPerimetro, float *pArea){
    *pPerimetro = 2 * 3.14 * raio;
    *pArea = 3.14 * raio * raio;
}