#include <stdio.h>

void fibonacci(int v[], int n);

int main(){

    int n;
    printf("Quantos termos terá a sequência de Fibonacci?\n");
    scanf("%d", &n);
    int v[n];
    fibonacci(v, n);
    
return 0;
}
void fibonacci(int v[], int n){
    int i;
    v[0] = 1;
    v[1] = 1;
    for( i = 2 ; i < n ; i++ ){
		v[i] = v[i - 1] + v[i - 2];
	}
	for(i = 0; i < n ; i++){
	    printf("%d ", v[i]);
	}
}