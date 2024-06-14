#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	double x;
	int n, i , j;
	printf("Digite o valor de x: ");
	scanf("%lf", &x);
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
	
	printf("e elevado a %lf = %.15lf (%d termos)\n", x, e_x, n);
	
	return 0;
}
