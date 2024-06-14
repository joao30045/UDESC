#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	char str[19];
	printf("Digite um numero: ");
	scanf("%s", str);
	
	int i, erro = 0;
	for( i = 0 ; str[i] != 0 && !erro ; i++ )
		if( str[i] < '0' || str[i] > '9' )
			erro = 1;
	
	if( erro )
		printf("Entrada invalida: há pelo menos um caractere que nao eh um digito!\n");
	else{
		long long int num = 0, pot_10 = 1;
		int j, digito;
		for( j = i-1 ; j >= 0 ; j-- ){
			digito = str[j] - '0'; // Pode ser tamb�m str[j] - 48
			num += digito*pot_10;
			pot_10 *= 10;
		}
		printf("Valor convertido: %lld", num);
	}
	
	return 0;
}
