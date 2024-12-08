#include <stdio.h>
#include <limits.h>
#include <math.h>

int main() {
    int n = 0;
    while (1) {
        double potencia_atual = pow(2, n);
        double proxima_potencia = pow(2, n + 1);
        double potencia_arredondada = potencia_atual + 1;

        if (potencia_arredondada == proxima_potencia) {
            printf("A primeira potência de dois cuja representação é idêntica ao seu sucessor é 2^%d\n", n);
            break;
        }

        n++;
        if (n == INT_MAX) {  // Verificar se atingiu o limite de int
            printf("Não foi encontrada uma solução dentro do limite de int.\n");
            break;
        }
    }

    return 0;
}
