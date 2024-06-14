#include <stdio.h>

int compara( char str1[], char str2[] );

int converte(char str[]);

int main(){

    char str1[50], str2[50];
    printf("Digite uma string:\n");
    gets(str1);
    printf("Digite outra string:\n");
    gets(str2);
    converte(str1);
    converte(str2);
    compara(str1, str2);

return 0;
}
int compara( char str1[], char str2[] ){
    int i, j, aux = 0;
    for(i = 0; str1[i] !=0 ; i++){
        for(j = 0; str2[j] != 0; j++){
            if(str1[i] > str2[j]){
                aux = 1;
            }
            if(str1[i] < str2[j]){
            aux = 0;
            }
            if(str1[i] == str2[j]){
                aux = 2;
            }
        }     
    }
    if(aux == 1){
        printf("A segunda string está na frente pela ordem alfabética.\n");
        printf("%s\n", str2);
        printf("%s\n", str1);
    }
    if(aux == 0){
        printf("A primeira string está na frente pela ordem alfabética.\n");
        printf("%s\n", str1);
        printf("%s\n", str2);
    }
    if(aux == 2){
        printf("As strings são iguais.\n");
        printf("%s\n", str1);
    }
}
int converte(char str[]){
    int i;
    for(i = 0; str[i] != 0; i++){
        if(str[i] >= 65 && str[i] <= 90){
            str[i] += 32;
        }
    }
}