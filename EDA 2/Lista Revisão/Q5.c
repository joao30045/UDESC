/*
Considere uma escadaria com n degraus e você pode subir 1 ou 2 degraus por vez. 
Dado n, retorne o número de maneiras únicas de subir a escada.
○ Exemplo, dado n = 4, existem 5 maneiras exclusivas
[1,1,1,1], [2,1,1], [1,2,1], [1,1,2], [2, 2]
*/
#include <stdio.h>

int subirEscada(int n) {
    if (n <= 0) {
        return 0; 
    } else if (n == 1) {
        return 1; 
    } else if (n == 2) {
        return 2; 
    } else {
        return subirEscada(n - 1) + subirEscada(n - 2);
    }
}

int main() {
    int n = 4;
    int maneiras = subirEscada(n);
    printf("Existem %d maneiras únicas de subir a escada.\n", maneiras);

    return 0;
}
