#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	float x;
	int n, i , j;
	printf("Digite o valor de x: ");
	scanf("%f", &x);
	printf("Digite o valor de n: ");
	scanf("%d", &n);
	
	double e_x = 0;
	
	for( i = 0 ; i <= n ; i++ ){
		int fat = 1;
		double pot = 1;
		for( j = 1 ; j <= i ; j ++ ){
			pot *= x; // pot = pot * x;
			fat *= j;
		}
		e_x += pot / fat;
	}
	
	printf("e elevado a %f = %.15lf (%d termos)\n", x, e_x, n);
	
	return 0;
}
