#include <stdio.h>

int compara(float a[], float b[], int n);

int main(){
    
    int n, i;
    float a[n], b[n];
    printf("Entre com o tamanho do vetor:\n");
    scanf("%d", &n);
    printf("Entre com os valores dos vetores a:\n");
    for(i = 0; i < n; i++){
        scanf("%f", &a[i]);
    }
    printf("Entre com os valores dos vetores b:\n");
    for(i = 0; i < n; i++){
        scanf("%f", &b[i]);
    }
    if (compara(a, b, n) == 1)
        printf("Os vetores possuem os mesmos valores.");
    else 
        printf("Os vetores possuem valores diferentes.");
return 0;
}
int compara(float a[], float b[], int n){
    int i;
    for(i = 0; i < n; i++){
        if(a[i] != b[i])
            return 0;
    }
    return 1;
}