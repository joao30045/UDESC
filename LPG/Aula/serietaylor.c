#include <stdio.h>

double e_x(double x, int n);
int main(){

    printf("%.15lf\n", e_x(1,20));

    return 0;
}
double e_x(double x, int n){
        double s = 0, termo = 1; 
        int i = 0;
        for(i = 0; i <= n; i++){
            s += termo;
            termo *= x/(i+1);
        }
        return s;
}