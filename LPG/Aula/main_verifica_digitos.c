#include <stdio.h>
#include <stdlib.h>

int eh_numero( char s[] );

int main(int argc, char *argv[]) {
	char str[50] = "1235676757567567567567098";
	
	if( eh_numero( str ) )
		printf("'%s' eh um numero.\n", str);
	else
		printf("'%s' nao eh um numero.\n", str);
	
	return 0;
}

int eh_numero( char s[] ){
	int i;
	for( i = 0 ; s[i] != 0 ; i++ ){
		if( s[i] < '0' || s[i] > '9' )
			return 0;
	}
	return 1;	
}
