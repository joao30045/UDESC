#include <stdio.h>

void soma(int n);
int somarec(int n);

int main(){

    int n;
    scanf("%d", &n);
    soma(n);
    printf("Resultado soma recursiva: %d\n", somarec(n));

return 0;
}
void soma(int n){
    int i, soma = 0;
    for (i = 1; i <= n; i++){
        soma += i;
    }
    printf("Resultado soma iterativa: %d\n", soma);
}
int somarec(int n){
    if (n != 0)
        return n + somarec(n - 1);
        else
        return n;
}