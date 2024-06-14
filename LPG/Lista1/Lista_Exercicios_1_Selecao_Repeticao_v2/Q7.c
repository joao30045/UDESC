#include <stdio.h>

int main(){

    int j = 1, n, i, x, y, temp;
    scanf("%d", &n);

    while(j <= n){
    int soma = 0;
    scanf("%d", &x);
    scanf("%d", &y);
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
    j++;
    }
    
    return 0;
}