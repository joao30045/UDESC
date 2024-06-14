#include <stdio.h>
#include <stdlib.h>
#include "Fila_Lenta.h"

void inicializa_fila ( Fila_Lenta *p, int c ){
	p->fim = -1;
	p->capacidade = c;
	p->dados = malloc( sizeof( int ) * c );
}

int fila_vazia ( Fila_Lenta f ){
	return f.fim == -1;
}

int fila_cheia ( Fila_Lenta f ){
	return f.fim == f.capacidade - 1;
}

int inserir ( Fila_Lenta *p, int info ){
	if( fila_cheia ( *p ) )
		return ERRO_FILA_CHEIA;

	p->fim++;
	p->dados[p->fim] = info;
	return 1; // Sucesso
}

int remover ( Fila_Lenta *p, int *info ){
	if ( fila_vazia ( *p ) )
		return ERRO_FILA_VAZIA;
	// Copia valor no início.
	*info = p->dados[0];
	// Movimenta dados da fila para "a esquerda".
	int i;
	for( i = 0 ; i < p->fim ; i++ )
		p->dados[i] = p->dados[i+1];
	// Decrementa índice do fim.
	p->fim--;
	return 1; // Sucesso
}

void mostra_fila ( Fila_Lenta f ){
	if( fila_vazia ( f ) )
		printf("Fila vazia!\n");
	else{
		printf("Dados da Fila:\n");
		int i;
		for( i = 0 ; i <= f.fim ; i++ )
			printf("%d\n", f.dados[i]);
	}
}

void desaloca_fila ( Fila_Lenta *p ){
	free( p->dados );
}

