#include <stdio.h>

int soma_primos(int v[], int n);

int main(){

    int n, v[n], i;
    printf("Digite o tamanho do vetor:");
    scanf("%d", &n);
    printf("Digite os valores do vetor");
    for(i = 0; i < n; i++){
        scanf("%d", &v[i]);
    }
    soma_primos(v, n);
return 0;
}
int soma_primos(int v[], int n){
    int i, j, soma, primo;
    for(i = 0; i < n; i++){
        primo = 1;
        for(j = 2; j < v[i]; j++){
            if (v[i] % j == 0){
                primo = 0;
                break;
            }
        }
        if(primo){
        soma += v[i];
        printf("%d", soma);
        }
    }
}