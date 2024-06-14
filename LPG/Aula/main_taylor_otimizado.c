#include <stdio.h>
#include <stdlib.h>

double e_x( double x, int n );
double e_x_rec( double x, int n, int i, double termo );


int main(int argc, char *argv[]) {
	
	printf("%.15lf\n", e_x( 1, 20 ) );
	printf("%.15lf\n", e_x_rec( 1, 20, 0, 1 ) );
	
	return 0;
}

double e_x( double x, int n ){
	double s = 0, termo = 1;
	int i = 0;
	for( i = 0 ; i <= n ; i++ ){
		s += termo;
		termo *= x/(i+1);
	}
	return s;
}

double e_x_rec( double x, int n, int i, double termo ){
	if( i <= n )
		return termo + e_x_rec( x, n, i+1, termo * x/(i+1) );
	else
		return 0;
}


