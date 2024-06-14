#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	char str[19];
	printf("Digite um numero: ");
	scanf("%s", str);
	
	int i, erro = 0; // Indica que há um erro na entrada.
	for( i = 0 ; str[i] != 0 && !erro ; i++ )
		if( str[i] < '0' || str[i] > '9' )
			erro = 1;
	
	if( erro )
		printf("Entrada invalida: há pelo menos um caractere que nao eh um digito!\n");
	else{
		long long int num = 0, pot_10 = 1;
		int j, digito;
		for( j = i-1 ; j >= 0 ; j-- ){
			digito = str[j] - '0'; // Pode ser também str[j] - 48
			num += digito*pot_10;
			pot_10 *= 10;
		}
		printf("Valor convertido + 1 : %lld", num + 1 );
	}
	
	return 0;
}
