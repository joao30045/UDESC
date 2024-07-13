#include <stdlib.h>
#include <stdio.h>

int comparar(const void *a, const void *b) {
    return *((int*)a) - *((int*)b);
}
int paresLuva(int *v, int tamanho){
    int pares = 0;
    int contador_tamanho = 0;
    if (tamanho <= 0)
        return 0;
    int atual = v[0];
    for(int i = 0; i < tamanho; i++){
        if (v[i] == atual) {
            contador_tamanho++;
        } else {
            pares += contador_tamanho/2;
            atual = v[i];
            contador_tamanho = 1;
        }
    }
    if (contador_tamanho >= 2) {
        pares += contador_tamanho/2;
    }
    return pares;
}

int main() {
    int tamanho = 8;
    int v[] = {6, 5, 2, 3, 5, 2, 2, 1};
    qsort(v, tamanho, sizeof(int), comparar);
    int pares = paresLuva(v, tamanho);
    printf("%d\n", pares);
    return 0;
}