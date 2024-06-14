#include <stdio.h>

int main(){

int v[10] = {12,18,9,10,22,3,8,-1,13,6};
int i, n = 10;
int menor = v[0];
int i_menor = 0;

for(i=1;i<n;i++)
    if(v[i]<menor){
        menor = v[i];
        i_menor = i;
    }
printf("Menor: %d (%d)\n", menor, i_menor);

return 0;
}
