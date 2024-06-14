#include <stdio.h>
#include <stdlib.h>

void serie_harmonica(int k);
float harmonicarec(int k);

int main() {

	int k;
    scanf("%d", &k);
	serie_harmonica(k);
    printf("A soma da série harmônica recursiva para %d termos é = %f\n", k, harmonicarec(k));

	return 0;
}
void serie_harmonica(int k){
    float soma = 0, i;
    for(i = 1; i <= k; i++){
        if(i<k){
            soma += 1/i;
        }
        if(i == k){
            soma += 1/i;
        }
    }
    printf("\nA soma da série harmônica iterativa para %d termos é = %f\n", k, soma);
}
float harmonicarec(int k){
    if( k != 1 )
		return 1.0/k + harmonicarec(k - 1);
	else
		return 1;
}