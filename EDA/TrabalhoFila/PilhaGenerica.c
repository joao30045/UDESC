#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "PilhaGenerica.h"

void inicializa_pilha ( PilhaGenerica *p, int c, int t ){
	p->topo = -1;
	p->capacidade = c;
	p->dados = malloc( sizeof( void* ) * c );
	p->tamInfo = t;
}

int pilha_vazia ( PilhaGenerica p ){
	return p.topo == -1;
}

int pilha_cheia ( PilhaGenerica p ){
	return p.topo == p.capacidade - 1;
}

int empilha ( PilhaGenerica *p, void *info ){
	if( pilha_cheia ( *p ) )
		return ERRO_PILHA_CHEIA;

	p->topo++;
	//p->dados[p->topo] = info;
	p->dados[p->topo] = malloc( p->tamInfo );
	memcpy( p->dados[p->topo], info, p->tamInfo );
	return 1; // Sucesso
}

int desempilha ( PilhaGenerica *p, void *info ){
	if ( pilha_vazia ( *p ) )
		return ERRO_PILHA_VAZIA;

	//*info = p->dados[p->topo];
	memcpy( info, p->dados[p->topo], p->tamInfo );
	free( p->dados[p->topo] );
	p->topo--;
	return 1; // Sucesso
}

int le_topo ( PilhaGenerica p, void *info ){
	if ( pilha_vazia( p ) )
		return ERRO_PILHA_VAZIA;

	//*info = p.dados[p.topo];
	memcpy( info, p.dados[p.topo], p.tamInfo );
	return 1; // Sucesso
}

void mostra_pilha ( PilhaGenerica p, void (*mostra)(void*) ){
	if( pilha_vazia ( p ) )
		printf("Pilha vazia!\n");
	else{
		printf("Dados da Pilha:\n");
		int i;
		for( i = 0 ; i <= p.topo ; i++ )
			mostra( p.dados[i] ); // Polimorfismo.
	}
}

void desaloca_pilha( PilhaGenerica *p ){
	free( p->dados );
}

