#include <time.h>
#include <stdio.h>
#define N 4

int main(){ srand(time(0));
    int m[N][N],i,j; 
    for(i=0;i<N;i++)
        for(j=0;j<N;j++)
            m[i][j] = rand()%1000;
    printf("Diagonal princiapl:");
    for(i=0;i<N;i++)
        for(j=0;j<N;j++)
            if(i==j)
            printf("%d", m[i][j]);
    printf("\n");
return 0;
}