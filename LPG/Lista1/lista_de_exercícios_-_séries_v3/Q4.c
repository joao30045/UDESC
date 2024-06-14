#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	double x = 1;
	int n, i , j;
	printf("Digite o valor de n: ");
	scanf("%d", &n);
	
	double e_x = 0;
	
	for( i = 0 ; i <= n ; i++ ){
		double termo = 1;
		for( j = 1 ; j <= i ; j ++ ){
			termo *= x / j;
		}
		e_x += termo;
	}
	
	printf("e = %.15lf (%d termos)\n", e_x, n);
	
	return 0;
}