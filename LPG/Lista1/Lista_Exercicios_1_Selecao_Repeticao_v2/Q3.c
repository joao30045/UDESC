#include <stdio.h>
int main(){

int hora_inicial, hora_final, tempo_jogo;

scanf("%i%i", &hora_inicial, &hora_final);

tempo_jogo = hora_final - hora_inicial;

if (tempo_jogo < 0){
    tempo_jogo = 24 + (hora_final - hora_inicial);
}
if (hora_final == hora_inicial){
    printf("O JOGO DUROU 24 HORAS\n");
}else{
    printf("O JOGO DUROU %i HORA(S)\n", tempo_jogo);
}
return 0;    
}
