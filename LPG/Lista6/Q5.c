#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int *uniao(int *v1, int n1, int *v2, int n2, int *p3);
int pertence(int x, int *v, int);

int main(){

    int tam, i;
    printf("Qual o tamanho do vetor a ser criado?\n");
    scanf("%d", &tam);
    int * v = malloc(sizeof(int) * tam);
    srand( time(0) );
	for(i = 0 ; i < tam ; i++){
		v[i] = rand() % (4 * tam);
        v[i] -= 2 * tam;

    }
    for(i = 0; i < tam; i++){
		printf("%d : %.d\n", i, v[i]);
    }
    int tam2;
    printf("Qual o tamanho do segundo vetor a ser criado?\n");
    scanf("%d", &tam2);
    int * v2 = malloc(sizeof(int) * tam2);
    srand( time(0) );
	for(i = 0 ; i < tam2 ; i++){
		v2[i] = rand() % (4 * tam2);
        v2[i] -= 2 * tam2;

    }
    for(i = 0; i < tam2; i++){
		printf("%d : %.d\n", i, v2[i]);
    }
    int tam_uniao;
    int * v_uniao;
    v_uniao = uniao(v, tam, v2, tam2, &tam_uniao);
    printf("A união entres os dois vetores é:\n");
    for(i = 0; i < tam_uniao; i++){
		printf("%d : %d\n", i, v_uniao[i]);
    }
    free(v_uniao);

return 0;
}
int *uniao(int *v1, int n1, int *v2, int n2, int *p3){
    int * uniao = malloc(sizeof(int) * (n2));
    *p3 = 0;
    int j =0, i;
    for(i = 0; i < n2; i++){
        if(!pertence(v2[i], v1, n1)){
            uniao[*p3] = v2[i];
            (*p3)++;
        }
    }
    uniao = realloc(uniao,sizeof(int) * ((*p3) + n1));
    for(i = 0; i < n1; i++){
        uniao[i + (*p3)] = v1[i];
    }
    *p3 += n1;
return uniao;
}
int pertence(int x, int *v, int n){
    if(n <= 0){
        return 0;
    }
    else if (x == v[0]){
        return 1;
    }
return pertence(x, v+1, n-1);
}