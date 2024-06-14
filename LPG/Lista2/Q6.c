#include <stdio.h>

void sequencia_fibonacci(int n);

int main(){

    int a;
	scanf("%d", &a);
    sequencia_fibonacci(a);

return 0;
}
void sequencia_fibonacci(int n){
    int i;
	long long int a = 1, b = 1, atual;
	
	printf("1: 1\n2: 1\n");
	for( i = 3 ; i <= n ; i++ ){
		atual = a + b;
		printf("%d: %lld\n", i, atual );
		a = b;
		b = atual;
	}
}