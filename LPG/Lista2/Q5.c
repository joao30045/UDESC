#include <stdio.h>

void mostra_primo(int k, int n);

int main() {

    int a, b;
    scanf("%d", &a);
    scanf("%d", &b);
    mostra_primo(a, b);

    return 0;
}
void mostra_primo(int k, int n){
    int i, cont, primo = 1; 
    for (cont = 0 ; cont < n; k++) {
        primo = 1;
        for (i = 2; i < k; i++) {
            if (k % i == 0) {
                primo = 0;
                break;
            }
        }
        if (primo){
            cont++;
            printf("%d : %d\n", cont, k);
        }
    }
}
