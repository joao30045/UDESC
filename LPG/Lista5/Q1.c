#include <stdio.h>
#include <stdlib.h>

void inc_dec(int *a, int *b);

int main(){

    int a, b;
    printf("Digite os valores para incrementar e decrementar.\n");
    scanf("%d%d", &a, &b);
    inc_dec(&a,&b);
    printf("Novos valores: %d e %d\n", a,b);

return 0;
}
void inc_dec (int *a, int *b){
    (*a)++;
    (*b)--;
}