#include <stdio.h>
#include <stdlib.h>
#include <math.h>

double funcao(double x) {
    return x + tan(tan(fabs((1.0/4.0) * x - 2.0/4.0))); 
}

double metodoTrapezioComposto(double a, double b, int n) {
    double h = (b - a) / n;
    double soma = funcao(a) + funcao(b);
    
    for (int i = 1; i < n; i++) {
        soma += 2 * funcao(a + i * h);
    }
    return (h / 2) * soma;
}

double funcao2(double t) {
    return ((10.0 * 98.0) / 13.0) * (1 - exp(-(13.0 / 98.0) * t)); 
}

double metodoTrapezioComposto2(double a, double b, int n) {
    double h = (b - a) / n;
    double soma = funcao2(a) + funcao2(b);
    
    for (int i = 1; i < n; i++) {
        soma += 2 * funcao2(a + i * h);
    }
    return (h / 2) * soma;
}
int main() {

    double a = -2, b = 6;
    int n = 19;    
    double resultado = metodoTrapezioComposto(a, b, n);
    
    printf("A integral aproximada da questão 1 é: %lf\n", resultado);

    a = 0;
    b = 26;
    n = 25;    
    resultado = metodoTrapezioComposto2(a, b, n);
    
    printf("A integral aproximada da questão 2 é: %lf\n", resultado);
    
    return 0;
}
