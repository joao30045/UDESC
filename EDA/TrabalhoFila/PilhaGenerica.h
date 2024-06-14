
#define ERRO_PILHA_VAZIA -1
#define ERRO_PILHA_CHEIA -2

typedef struct{
	void **dados; // dados da PilhaGenerica (vetor de ponteiros void)
	int topo;     // indica o índice do topo
	int capacidade; // capacidade do vetor
	int tamInfo;    // tamanho (em bytes) da informação
} PilhaGenerica;

void inicializa_pilha ( PilhaGenerica *p, int c, int t );
int pilha_vazia ( PilhaGenerica p );
int pilha_cheia ( PilhaGenerica p );
int empilha ( PilhaGenerica *p, void *info );
int desempilha ( PilhaGenerica *p, void *info );
int le_topo ( PilhaGenerica p, void *info );
void mostra_pilha ( PilhaGenerica p, void (*mostra)(void*) );
void desaloca_pilha( PilhaGenerica *p );

