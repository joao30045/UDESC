#include <stdio.h>
#include <stdlib.h>
#include "PilhaDupla.h"

void inicializa_pilhas ( PilhaDupla *p, int c ){
	p->topo1 = -1;
	p->topo2 = c;
	p->capacidade = c;
	p->dados = malloc( sizeof( int ) * c );	
}

int pilha1_vazia ( PilhaDupla p ){
	return p.topo1 == -1;
}

int pilha2_vazia ( PilhaDupla p ){
	return p.topo2 == p.capacidade;
}

int pilhas_cheias ( PilhaDupla p ){
	return p.topo1 - p.topo2 == 1;
}

int empilha1 ( PilhaDupla *p, int info ){
	if( pilhas_cheias ( *p ) )
		return ERRO_PILHA_CHEIA;

	p->topo1++;
	p->dados[p->topo1] = info;
	return 1; // Sucesso
}

int desempilha1 ( PilhaDupla *p, int *info ){
	if ( pilha1_vazia ( *p ) )
		return ERRO_PILHA_VAZIA;

	*info = p->dados[p->topo1];
	p->topo1--;
	return 1; // Sucesso
}

int empilha2 ( PilhaDupla *p, int info ){
	if( pilhas_cheias ( *p ) )
		return ERRO_PILHA_CHEIA;

	p->topo2--;
	p->dados[p->topo2] = info;
	return 1; // Sucesso
}

int desempilha2 ( PilhaDupla *p, int *info ){
	if ( pilha2_vazia ( *p ) )
		return ERRO_PILHA_VAZIA;

	*info = p->dados[p->topo2];
	p->topo2++;
	return 1; // Sucesso
}

int le_topo1 ( PilhaDupla p, int *info ){
	if ( pilha1_vazia( p ) )
		return ERRO_PILHA_VAZIA;

	*info = p.dados[p.topo1];
	return 1; // Sucesso
}

int le_topo2 ( PilhaDupla p, int *info ){
	if ( pilha2_vazia( p ) )
		return ERRO_PILHA_VAZIA;

	*info = p.dados[p.topo2];
	return 1; // Sucesso	
}

void mostra_pilha1 ( PilhaDupla p ){
	if( pilha1_vazia ( p ) )
		printf("Pilha 1 vazia!\n");
	else{
		printf("Dados da Pilha 1:\n");
		int i;
		for( i = 0 ; i <= p.topo1 ; i++ )
			printf("%d\n", p.dados[i]);
	}	
}

void mostra_pilha2 ( PilhaDupla p ){
	if( pilha2_vazia ( p ) )
		printf("Pilha 2 vazia!\n");
	else{
		printf("Dados da Pilha 2:\n");
		int i;
		for( i = p.capacidade-1 ; i >= p.topo2 ; i-- )
			printf("%d\n", p.dados[i]);
	}		
}

void desaloca_pilhas( PilhaDupla *p ){
	free( p->dados );	
}

