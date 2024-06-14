#include <stdio.h>
#include <stdlib.h>
#include <time.h>

float * maiorvet(float *v, int tam, float x);
float media(float *v, int n);
void imprime_vetor(float *v, int n);

int main(){

    int tam, i;
    printf("Qual o tamanho do vetor a ser criado?\n");
    scanf("%d", &tam);
    float * v = malloc(sizeof(float) * tam);
    srand( time(0) );
	for(i = 0 ; i < tam ; i++){
		v[i] = rand() % 10 + 1;
    }
    for(i = 0; i < tam; i++){
		printf("%d : %.f\n", i, v[i]);
    }
    printf("O vetor está alocado na posição %p.\n", v);
    float med = media(v,tam);
    printf("A média dos valores é %.2f.\n", med);
    float * maiores = maiorvet(v, tam, med);
    printf("Vetor com valores maiores ou iguais a média:\n");
	imprime_vetor(maiores + 1, (int) maiores[0]);
    free(maiores);
    free(v);

return 0;
}
float media(float *v, int n){
    float soma = 0;
    int i;
    for(i = 0; i < n; i++){
        soma += v[i];
    }
return soma/n;
}
float * maiorvet(float *v, int tam, float x){
    int cont = 0, i, j = 0;
    for(i = 0; i < tam; i++){
        cont += (int) (v[i] >= x);
    }
    float * sec = malloc(sizeof(float) * (cont + 1));
    sec[0] = (float) cont;
    for(i = 0; i < tam && j < cont; i++){
        if(v[i] >= x){
            sec[j+1] = v[i];
            j++;
        }
    }
return sec;
}
void imprime_vetor(float *v, int n){
    int i;
    for(i = 0; i < n; i++){
        printf("%.2f,", v[i]);
    }
}