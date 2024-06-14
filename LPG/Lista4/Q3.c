#include <stdio.h>

void concatena( char str1[], char str2[] );

int main(){

    char str1[50], str2[50];
    printf("Digite a primeira string.\n");
    gets(str1);
    printf("Digite a segunda string.\n");
    gets(str2);
    concatena(str1, str2);

return 0;
}
void concatena( char str1[], char str2[] ){
    int i = 0, j = 0;
    while(str1[i] != '\0')
        i++;
    while(str2[j] != '\0'){
        str1[i] = str2[j];
        i++;
        j++;
    }
    str1[i] = '\0';
    printf("Concatenação das suas strings:\n%s", str1);
    printf("\n");
}