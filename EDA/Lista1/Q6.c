#include <stdio.h>
#include <stdlib.h>
#include <time.h>

float *clone(float *v, int n);

int main(){

    int tam, i;
    printf("Qual o tamanho do vetor a ser criado?\n");
    scanf("%d", &tam);
    float * v = malloc(sizeof(float) * tam);
    srand( time(0) );
	for(i = 0 ; i < tam ; i++){
		v[i] = rand() % 1000 + 1;
    }
    for(i = 0; i < tam; i++){
		printf("%d : %.f\n", i, v[i]);
    }
    printf("O vetor está alocado na posição %p.\n", v);
    float * p = clone(v,tam);
    for(i = 0; i < tam; i++){
		printf("%d : %.f\n", i, p[i]);
    }
    printf("A cópia do vetor está alocado na posição %p.", p);
    free(v);
    free(p);

return 0;
}
float *clone( float *v, int tam){
    float * p = malloc(sizeof(float) * tam);
    int i;
    for(i = 0; i < tam; i++){
        p[i] = v[i];
    }
return p;
}