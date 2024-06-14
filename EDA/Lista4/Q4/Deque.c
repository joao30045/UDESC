#include <stdio.h>
#include <stdlib.h>
#include "Deque.h"

void inicializa_deque ( Deque *p, int c ){
	p->inicio = p->n = 0;
	p->capacidade = c;
	p->dados = malloc( sizeof( int ) * c );
}

int deque_vazio ( Deque f ) {
	return f.n == 0;
}

int deque_cheio ( Deque f ) {
	return f.n == f.capacidade;
}

int inserir_inicio ( Deque *p, int info ){
	if( deque_cheio( *p ) )
		return ERRO_FILA_CHEIA;
	
	p->inicio = (p->capacidade + p->inicio - 1) % p->capacidade;
	p->dados[p->inicio] = info;
	p->n++;
	return 1; // Sucesso.
}

int remover_inicio ( Deque *p, int *info ){
	if( deque_vazio ( *p ) )
		return ERRO_FILA_VAZIA;

	*info = p->dados[p->inicio];
	p->inicio = (p->inicio + 1) % p->capacidade;
	p->n--;
	return 1; // Sucesso
}

int inserir_fim ( Deque *p, int info ){
	if( deque_cheio ( *p ) )
		return ERRO_FILA_CHEIA;

	int fim = ( p->inicio + p->n ) % p->capacidade;
	p->dados[fim] = info;
	p->n++;
	return 1; // Sucesso
}

int remover_fim ( Deque *p, int *info ){
	if( deque_vazio( *p ) )
		return ERRO_FILA_VAZIA;
	
	int fim = ( p->inicio + p->n - 1 ) % p->capacidade;
	*info = p->dados[fim];
	p->n--;
	return 1; // Sucesso.
}

void mostra_deque ( Deque f ){
	if( deque_vazio ( f ) )
		printf("Deque vazio!\n");
	else{
		printf("Dados do deque:\n");
		int i, cont;
		for( cont = 0, i = f.inicio ; cont < f.n ; cont++, i = (i + 1) % f.capacidade )
			printf("[%d] %d\n", i, f.dados[i]);
	}
	printf("\n\n");
}

void desaloca_deque( Deque *p ){
	free( p->dados );
}

