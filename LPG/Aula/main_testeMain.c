#include <stdio.h>
#include <stdlib.h>

float pot( float b, int e );

int main(int argc, char *argv[]) {
	if( argc != 3 ){
		printf("Erro: formato deve ser:\nTesteMain <base> <expo>\n");
		return 1;
	}
	
	float base;
	int expo;
	
	if( sscanf( argv[1], "%f", &base) != 1 ){
		printf("Erro: '%s' deve ser um numero real!\n", argv[1]);
		return 1;
	}
	
	if( sscanf( argv[2], "%d", &expo) != 1 ){
		printf("Erro: '%s' deve ser um numero inteiro!\n", argv[2]);
		return 1;
	}
	
	printf("Base: %f e Expo: %d\n", base, expo);
	
	printf("Resultado: %f\n", pot( base, expo ) );
	
	/*
	int i;
	
	for( i = 0 ; i < argc ; i++ )
		printf("%d: %s\n", i, argv[i]);
	*/
	
	return 0;
}

float pot( float b, int e ){
	if( e == 0 ) return 1;

	if( e < 0 )	return pot( 1/b, -e );
	
	return b * pot( b , e-1 );
}

