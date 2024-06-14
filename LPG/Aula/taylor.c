#include <stdio.h>

int main(){

float x;
int n, i;
double e_x = 0, termo = 1;

for (i=0;i<=n;i++){
    e_x += termo;
    termo *= x/(i+1);
}
printf("%.15f/n", e_x);

return 0;
}