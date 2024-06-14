#include <stdio.h>
#include <stdlib.h>

// sscanf() e sprintf()

int main(int argc, char *argv[]) {
		
	char str[20];
	
	// sscanf()
	printf("Digite um numero: ");
	scanf("%s", str);
	
	int num;
	int x = sscanf( str, "%d", &num );
	
	if( x == 0 )
		printf("Erro na entrada!\n");
	else
		printf("Valor convertido + 1: %d\n", num + 1);
	
	// sprintf()
	float y;
	printf("Digite um numero real: ");
	scanf("%f", &y);
	
	sprintf( str, "Y = %.4f", y);
	
	printf("'%s'\n", str);
	
	return 0;
}
