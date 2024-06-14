#include <stdio.h>
#include <stdlib.h>

void hanoi( int n, char orig, char dest, char aux );

int main(int argc, char *argv[]) {
	int n;
	printf("Digite o numero de discos: ");
	scanf("%d", &n);
	hanoi( n, 'A', 'C', 'B' );
	
	return 0;
}

void hanoi( int n, char orig, char dest, char aux ){
	if( n == 1 )
		printf("Mova disco 1 de %c para %c\n", orig, dest );
	else{
		hanoi( n-1, orig, aux, dest );
		printf("Mova disco %d de %c para %c\n", n, orig, dest);
		hanoi( n-1, aux, dest, orig );
	}	
}
