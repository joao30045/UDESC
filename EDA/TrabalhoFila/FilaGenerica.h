#define ERRO_FILA_VAZIA -1
#define ERRO_FILA_CHEIA -2

typedef struct{
	void **dados;	// dados da fila
	int inicio;		// �ndice do in�cio da fila
	int n;			// qtd. de elementos da fila
	int capacidade;	// capacidade da fila
	int tamInfo;	// tamanho (em bytes) a informa��o
} FilaGenerica;

void inicializa_fila ( FilaGenerica *p, int c, int t );
int fila_vazia ( FilaGenerica f );
int fila_cheia ( FilaGenerica f );
int inserir ( FilaGenerica *p, void *info );
int remover ( FilaGenerica *p, void *info );
void mostra_fila ( FilaGenerica f, void (*mostra)(void*) );
void desaloca_fila( FilaGenerica *p );

