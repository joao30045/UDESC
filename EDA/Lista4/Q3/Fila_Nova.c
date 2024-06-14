#include "Fila_Nova.h"
#include <stdio.h>

void inicializa_fila ( Fila_Nova *p, int c ){
	inicializa_pilha( &p->p1, c );
	inicializa_pilha( &p->p2, c );
}

int fila_vazia ( Fila_Nova f ){
	return pilha_vazia( f.p1 ) && pilha_vazia( f.p2 );
}

int fila_cheia ( Fila_Nova f ){
	return pilha_cheia( f.p1 ) || pilha_cheia( f.p2 );
}

int inserir ( Fila_Nova *p, int info ){
	// 1 - Mover dados de p2 para p1;
	int aux;
	while( !pilha_vazia( p->p2 ) ){
		desempilha( &p->p2, &aux );
		empilha( &p->p1, aux );
	}
	// 2 - Empilhar info em p1;
	empilha( &p->p1, info );
}

int remover ( Fila_Nova *p, int *info ){
	// 1 - Mover dados de p1 para p2;
	int aux;
	while( !pilha_vazia( p->p1 ) ){
		desempilha( &p->p1, &aux );
		empilha( &p->p2, aux );
	}
	// 2 - Desempilhar de p2 e copiar para variável apontada por info;
	desempilha( &p->p2, info );
}

void mostra_fila ( Fila_Nova f ){
	if( fila_vazia( f ) )
		printf("Fila Vazia!\n");
	else{
		int aux;
		while( !pilha_vazia( f.p1 ) ){
			desempilha( &f.p1, &aux );
			empilha( &f.p2, aux );
		}
		printf("Dados da fila:\n");
		while( !pilha_vazia( f.p2 ) ){
			desempilha( &f.p2, &aux );
			printf("%d\n", aux);
			empilha( &f.p1, aux );
		}
	}
}

void desaloca_fila( Fila_Nova *p ){
	desaloca_pilha( &p->p1 );
	desaloca_pilha( &p->p2 );
}


