#include <stdio.h>
#include <stdlib.h>

void converte( int t, int *pH, int *pM, int *pS );

int main(int argc, char *argv[]) {
	int x, h, m, s;
	printf("Digite um tempo (em s): ");
	scanf("%d", &x);
	
	converte( x, &h, &m, &s );
	
	printf("%dh %dmin %ds\n", h, m, s);
	
	return 0;
}

void converte( int t, int *pH, int *pM, int *pS ){
	*pH = t / 3600;
	int resto = t % 3600;
	*pM = resto / 60;
	*pS = resto % 60;
}

