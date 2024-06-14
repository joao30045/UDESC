#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	char str[19];
	printf("Digite uma data: ");
	scanf("%s", str);
	
	int i, erro = 0;
	for( i = 0 ; str[i] != 0 && !erro ; i++ )
        if(str[2] != '/' || str[5] != '/')
            erro = 1;
        for( i = 0 ; str[i] != 0 && !erro && str[i] < 2; i++ )
		    if( str[i] < '0' || str[i] > '9')
			    erro = 1;
                for( i = 0 ; str[i] != 0 && !erro && str[i] < 2; i++ )
		            if( str[i] < '0' || str[i] > '9')
			            erro = 1;
                        for( i = 0 ; str[i] != 0 && !erro && str[i] < 4; i++ )
		                    if( str[i] < '0' || str[i] > '9')
			                    erro = 1;
	
	if(erro)
		printf("Entrada invalida: lembre-se da '/' e apenas dígitos.\n");
	else{
		long long int num = 0, pot_10 = 1;
		int j, digito;
		for( j = 9 ; j >= 6 ; j-- ){
			digito = str[j] - '0'; 
			num += digito*pot_10;
			pot_10 *= 10;
		}
		printf("Ano: %lld\n", num);
        num = 0; 
        pot_10 = 1;
        for( j = 4 ; j > 2 ; j-- ){
			digito = str[j] - '0'; 
			num += digito*pot_10;
			pot_10 *= 10;
		}
		printf("Mês: %lld\n", num);
        num = 0; 
        pot_10 = 1;
        for( j = 1 ; j >= 0 ; j-- ){
			digito = str[j] - '0'; 
			num += digito*pot_10;
			pot_10 *= 10;
		}
		printf("Dia: %lld\n", num);
	}
	return 0;
}
