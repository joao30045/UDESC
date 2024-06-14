#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "FilaGenerica.h"

void inicializa_fila ( FilaGenerica *p, int c, int t ){
	p->inicio = p->n = 0;
	p->capacidade = c;
	p->tamInfo = t;
	p->dados = malloc( sizeof( void* ) * c );
}

int fila_vazia ( FilaGenerica f ) {
	return f.n == 0;
}

int fila_cheia ( FilaGenerica f ) {
	return f.n == f.capacidade;
}

int inserir ( FilaGenerica *p, void *info ){
	if( fila_cheia ( *p ) )
		return ERRO_FILA_CHEIA;

	int fim = ( p->inicio + p->n ) % p->capacidade;
	//p->dados[fim] = info;
	p->dados[fim] = malloc( p->tamInfo );
	memcpy( p->dados[fim], info, p->tamInfo );
	p->n++;
	return 1; // Sucesso
}

int remover ( FilaGenerica *p, void *info ){
	if( fila_vazia ( *p ) )
		return ERRO_FILA_VAZIA;

	//*info = p->dados[p->inicio];
	memcpy( info, p->dados[p->inicio], p->tamInfo );
	free( p->dados[p->inicio] );
	p->inicio = (p->inicio + 1) % p->capacidade;
	p->n--;
	return 1; // Sucesso
}

void mostra_fila ( FilaGenerica f, void (*mostra)(void*) ){
	if( fila_vazia ( f ) )
		printf("Fila vazia!\n");
	else{
		printf("Dados da fila:\n");
		int i, cont;
		for( cont = 0, i = f.inicio ; cont < f.n ; cont++, i = (i + 1) % f.capacidade ){
			printf("[%d] ", i);
			mostra( f.dados[i] ); // Código polimórfico.
		}
	}
	printf("\n\n");
}

void desaloca_fila( FilaGenerica *p ){
	int i, cont;
	for( cont = 0, i = p->inicio ; cont < p->n ; cont++, i = (i + 1) % p->capacidade ){
		free( p->dados[i] );
	}
	free( p->dados );
}

