#include <stdio.h>

int main(){

    int i, x, y, soma = 0, temp;
    scanf("%d%d", &x, &y);
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
    return 0;
}