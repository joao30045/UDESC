#include <stdio.h>

void soma_impares(int x,int y);

int main(){

    int a, b;
    scanf("%d%d", &a, &b);
    soma_impares(a, b);
    return 0;
}
void soma_impares(int x,int y){
    int i, soma = 0, temp;
    if(x > y){
        temp = x;
        x = y; 
        y = temp;
    }
    x++;
    for(i = x; i < y; i++){
        if(i % 2 != 0){
        soma += i;
        }
    }
    printf("%d\n", soma);
}