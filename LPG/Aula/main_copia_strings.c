#include <stdio.h>
#include <stdlib.h>

void copia_str( char dest[], char orig[] );

int main(int argc, char *argv[]) {
	char x[20] = "Alo mundo";
	char y[20] = "TEste 1234";
	
	printf("X: '%s' , Y: '%s'\n", x, y );
	
	copia_str( y, x );
	
	printf("X: '%s' , Y: '%s'\n", x, y );
	
	char z[100];
	
	copia_str( z, x );
	printf("X: '%s' , Z: '%s'\n", x, z );
	
	return 0;
}

void copia_str( char dest[], char orig[] ){
	int i;
	for( i = 0 ; orig[i] != 0 ; i++ )
		dest[i] = orig[i];
	dest[i] = 0;
}


