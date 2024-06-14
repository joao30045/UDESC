#include <stdio.h>
#include <stdlib.h>

int main() {

	int k;
    float soma = 0, i;

    scanf("%d", &k);
    for(i = 1; i <= k; i++){
        if(i<k){
            printf("1/%.f + ", i);
            soma += 1/i;
        }
        if(i == k){
            printf("1/%.f", i);
            soma += 1/i;
        }
    }
    printf("\nA soma da série harmônica para %d termos é = %f\n", k, soma);
	
	return 0;
}