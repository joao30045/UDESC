#include <stdio.h>

int main(){

    int i = 1, n;
    scanf("%d", &n);
    float n1, n2, n3, prova1, prova2, prova3, media;

    while(i <= n){
        scanf("%f%f%f", &n1,&n2,&n3);
        prova1 = n1 * 2;
        prova2 = n2 * 3;
        prova3 = n3 * 5;
        media = (prova1 + prova2 + prova3) / 10;
        printf("%.1f\n", media);
    i++;
    }

    return 0;
}