#include <stdio.h>
#include <math.h>

#define tamanho 3

void trocaLinha(float a[tamanho][tamanho], int linha1, int linha2, int n) {
    for (int i = 0; i < n; i++) {
        float aux = a[linha1][i];
        a[linha1][i] = a[linha2][i];
        a[linha2][i] = aux;
    }
}

void eliminacaoGaussianaCP(float a[tamanho][tamanho], float b[tamanho], int n) {
    for (int j = 0; j < n - 1; j++) {
        int linhaPivo = j;
        float pivo = fabs(a[j][j]);
        

        for (int i = j + 1; i < n; i++) {
            if (fabs(a[i][j]) > pivo) {
                pivo = fabs(a[i][j]);
                linhaPivo = i;
            }
        }
        

        if (linhaPivo != j) {
            trocaLinha(a, j, linhaPivo, n);
            float aux = b[j];
            b[j] = b[linhaPivo];
            b[linhaPivo] = aux;
        }
        
        for (int i = j + 1; i < n; i++) {
            float mij = a[i][j] / a[j][j];
            for (int k = 0; k < j; k++) {
                a[i][k] = 0;
            }
            for (int k = j + 1; k < n; k++) {
                a[i][k] -= mij * a[j][k];
            }
            b[i] -= mij * b[j];
        }
    }

    for (int k = n - 1; k >= 0; k--) {
        float soma = 0;
        for (int j = k + 1; j < n; j++) {
            soma += a[k][j] * b[j];
        }
        b[k] = (b[k] - soma) / a[k][k];
    }
}

int main() {
    float a[tamanho][tamanho];
    float b[tamanho];
    int n, i, j;
    
    printf("Digite o numero de variaveis(x1...x2...x3): ");
    scanf("%i", &n);
    
    printf("Digite os elementos da matriz:\n");
    for (i = 0; i < n; i++) {
        printf("linha %i:\n", i + 1);
        for (j = 0; j < n; j++) {
            printf("a[%i][%i]: ", i + 1, j + 1);
            scanf("%f", &a[i][j]);
        }
        printf("b[%i]: ", i + 1);
        scanf("%f", &b[i]);
    }

    eliminacaoGaussianaCP(a, b, n);

    printf("Solucao:\n");
    for (int i = 0; i < n; i++) {
        printf("x%i = %f\n", i + 1, b[i]);
    }

    return 0;
}