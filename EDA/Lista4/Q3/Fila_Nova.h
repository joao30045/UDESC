#include "Pilha.h"

#define ERRO_FILA_VAZIA -1
#define ERRO_FILA_CHEIA -2

typedef struct{
	Pilha p1, p2;
}Fila_Nova;

void inicializa_fila ( Fila_Nova *p, int c );
int fila_vazia ( Fila_Nova f );
int fila_cheia ( Fila_Nova f );
int inserir ( Fila_Nova *p, int info );
int remover ( Fila_Nova *p, int *info );
void mostra_fila ( Fila_Nova f );
void desaloca_fila( Fila_Nova *p );

