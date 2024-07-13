#include <stdio.h>

#define tamanho 3

void eliminacaoGaussianaSP(double a[tamanho][tamanho], double b[tamanho], int n) {
    double x[tamanho];
    int i, j, k;
    
    for (j = 0; j < n - 1; j++) {
        for (i = j + 1; i < n; i++) {
            double mij = a[i][j] / a[j][j];
            for (k = 0; k < j; k++) {
                a[i][k] = 0;
            }
            for (k = j + 1; k < n; k++) {
                a[i][k] -= mij * a[j][k];
            }
            b[i] -= mij * b[j];
        }
    }
    
    x[n - 1] = b[n - 1] / a[n - 1][n - 1];
    for (k = n - 2; k >= 0; k--) {
        double soma = 0;
        for (j = k + 1; j < n; j++) {
            soma += a[k][j] * x[j];
        }
        x[k] = (b[k] - soma) / a[k][k];
    }

    printf("Solucao:\n");
    for (i = 0; i < n; i++) {
        printf("x[%i] = %lf\n", i + 1, x[i]);
    }
}

int main() {
    double a[tamanho][tamanho];
    double b[tamanho];
    int n, i, j;
    
    printf("Digite o numero de variaveis(x1...x2...x3): ");
    scanf("%i", &n);
    
    printf("Digite os elementos da matriz:\n");
    for (i = 0; i < n; i++) {
        printf("linha %i:\n", i + 1);
        for (j = 0; j < n; j++) {
            printf("a[%i][%i]: ", i + 1, j + 1);
            scanf("%lf", &a[i][j]);
        }
        printf("b[%i]: ", i + 1);
        scanf("%lf", &b[i]);
    }
    
    eliminacaoGaussianaSP(a, b, n);
    
    return 0;
}