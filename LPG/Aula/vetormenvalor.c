#include <stdio.h>

int main(){

int v[10] = {12,18,9,10,22,3,8,-1,13,6};
int i, n = 10;
int menor = v[0];

for(i=1;i<n;i++)
    if(v[i]<menor){
        menor = v[i];
    }
printf("Menor: %d\n", menor);

return 0;
}
