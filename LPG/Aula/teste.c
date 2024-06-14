#include <stdio.h>
#include <stdlib.h>

long long int fatorial( int x );

int main(int argc, char *argv[]) {
	int n;
	printf("Digite um numero: ");
	scanf("%d", &n);
		
	printf("%d! = %lld\n", n, fatorial( n ) );
	
	return 0;
}

long long int fatorial( int x ){
	if( x == 0 ){
		printf("[Caso base: retorna 1]\n");
		return 1;
	}
	else{
		printf("[Caso recursivo: x=%d --> %d*%d!...]\n",x, x, x-1);
		long long int y = fatorial( x-1 ); // chamada recursiva
		printf("[Retorno da recursao: %d! = %lld]\n", x-1, y);
		return x * y;
	}
}

