#include <stdio.h>

void tipo_triangulo(float x, float y, float z);

int main(){

    float a, b, c; 
    scanf("%f%f%f", &a, &b, &c);
    tipo_triangulo(a,b,c);

return 0;
}

void tipo_triangulo(float x, float y, float z){
    
    if(x >= y +z){
    printf("Os lados não formam um triângulo\n");
    }
    if (x != y && y != z){
        printf("Triângulo escaleno\n");
    }
    if (x == y && y == z){
    printf("Triângulo equilátero\n");
    } else {
    if (x==y || x==z || y==z){
    printf("Triângulo isósceles\n");
    }
}
}