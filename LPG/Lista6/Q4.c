 #include <stdio.h>
 #include <stdlib.h>
 #include <time.h>

void imprime_vetor(int *v, int n);

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
    int cont_pos = 0, cont_neg = 0;
    for(i = 0; i < tam; i++){
        if(v[i] > 0){
            cont_pos++;
        }
        else if (v[i] < 0){
            cont_neg++;
        }
    }
    int * v_pos = malloc(sizeof(int) * cont_pos);
    int * v_neg = malloc(sizeof(int) * cont_neg);
    int j = 0, k = 0;
    for(i = 0; i < tam; i++){
        if(v[i] > 0){
            v_pos[j] = v[i];
            j++;
        }
        else if (v[i] < 0){
            v_neg[k] = v[i];
            k++;
        }
    }
    printf("O vetor positivo é:\n");
    imprime_vetor(v_pos, cont_pos);
    printf("\nO vetor negativo é:\n");
    imprime_vetor(v_neg, cont_neg);
    printf("\n");
    free(v);
    free(v_pos);
    free(v_neg);

return 0;
 }
void imprime_vetor(int *v, int n){
    int i;
    for(i = 0; i < n; i++){
        printf("%d,", v[i]);
    }
}