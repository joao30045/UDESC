#include <stdio.h>

int main(){

    char str[50];
	printf("Digite uma cadeia de caracteres: \n");
	gets( str );
    int i;
    for(i = 0; str[i] != 0; i++){
        if(str[i] >= 'a' && str[i] <= 'z'){
            str[i] -= 32;
        }
    }
    printf("%s\n", str);

return 0;
}