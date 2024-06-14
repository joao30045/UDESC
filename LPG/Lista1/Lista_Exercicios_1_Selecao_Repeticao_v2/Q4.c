#include <stdio.h>

int main() {

    int n, x, i = 1, par = 0, impar = 0, positivo = 0, negativo = 0;

    scanf("%d", &n);

    while(i <= n){
        scanf("%d", &x);
        if (x > 0){
            positivo++;
        }
        else 
            negativo++;
        
        if(x % 2 == 0){
            par++;
        }
        else 
            impar++;
        i++;
    }
    printf("%d valor(es) pares\n", par);
    printf("%d valor(es) impares\n", impar);
    printf("%d valor(es) positivos\n", positivo);
    printf("%d valor(es) negativos\n", negativo);

return 0; 
}