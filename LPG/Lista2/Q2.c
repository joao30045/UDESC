#include <stdio.h>

void maior_valor(int a, int b, int c);

int main(){

    int x,y,z;
    scanf("%d%d%d", &x, &y, &z);
    maior_valor(x,y,z);
    
return 0;
}
void maior_valor(int a, int b, int c){
    if (a >= b) { 
        if (a >= c) 
            printf("O maior valor é %d.\n", a); 
        else
            printf("O maior valor é %d.\n", c); 
    } 
    else { 
        if (b >= c) 
            printf("O maior valor é %d.\n", b); 
        else
            printf("O maior valor é %d.\n", c); 
    }
} 