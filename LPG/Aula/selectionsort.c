#include <stdio.h>

int main(){

int v[10] = {12,18,9,10,22,3,8,-1,13,6};
int i, j, n = 10;

for( i = 0 ; i < n-1 ; i++ ){
 int i_menor = i;
 for(j = i+1 ; j < n ; j++ )
 if( v[j] < v[i_menor] )
 i_menor = j;

 int aux = v[i];
 v[i] = v[i_menor];
 v[i_menor] = aux;
}
for(i=0;i<n;i++){
    printf("%d: %d\n", i, v[i]);
}
return 0;
}
