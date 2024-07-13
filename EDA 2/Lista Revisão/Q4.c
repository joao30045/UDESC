/*
Dada um vetor inteiros v, retorne a maior soma dos números não adjacentes.
Os números podem incluir 0 ou negativos no vetor.
○ Exemplo 1, dado v = [2,4,6,2,5], a saída esperada é 13, considerando 2 + 6 + 5
○ Exemplo 2, dado v = [5,1,1,5], a saída esperada é 10, considerando 5 + 5
*/
#include <stdio.h>
#include <stdlib.h>

int maior(int vetor[], int n){
   int inicio = vetor[0], excl = 0, novo, i;

   for (i = 1; i < n; i++) {
      novo = inicio > excl ? inicio : excl;
      
      inicio = excl + vetor[i];
      excl = novo;
   }

   return inicio > excl ? inicio : excl;
}

int main() {
   int v1[] = {2,4,6,2,5};
	
   printf("%d\n", maior(v1,5));

   return 0;
}