#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int raizes( float a, float b, float c, float *p1, float *p2 );

int main(int argc, char *argv[]) {
	float a, b, c, x1, x2;
	printf("Digite o valor do coeficiente A: ");
	scanf("%f", &a);
	printf("Digite o valor do coeficiente B: ");
	scanf("%f", &b);
	printf("Digite o valor do coeficiente C: ");
	scanf("%f", &c);
	
	if( a == 0 )
		printf("Nao eh equacao de 2o grau!\n");
	else{		
		if( raizes( a, b, c, &x1, &x2 ) )
			printf("X1 = %.3f e X2 = %.3f\n", x1, x2);
		else
			printf("Nao ha raizes reais!\n");
	}
	
	return 0;
}

int raizes( float a, float b, float c, float *p1, float *p2 ){
	float delta = b*b - 4*a*c;
	if( delta < 0 )
		return 0;
	
	*p1 = ( -b + sqrt( delta ) ) / ( 2 * a );
	*p2 = ( -b - sqrt( delta ) ) / ( 2 * a );
	return 1;
}


