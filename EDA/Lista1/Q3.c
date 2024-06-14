#include <stdio.h>
#include <stdlib.h>

void cacula_hora(int totalMinutos, int *ph, int *pm);

int main(){

    int totalMinutos, ph, pm;
    printf("Quantos minutos se passaram da meia noite?\n");
    scanf("%d", &totalMinutos);
    cacula_hora(totalMinutos, &ph, &pm);
    printf("São %d:%d.\n", ph, pm);

return 0;
}
void cacula_hora(int totalMinutos, int *ph, int *pm){
    (*ph) = totalMinutos / 60;
    (*pm) = totalMinutos % 60;
}