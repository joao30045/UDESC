#define ERRO_FILA_VAZIA -1
#define ERRO_FILA_CHEIA -2

typedef struct{
	int *dados;		// dados da fila
	int inicio;		// índice do início da fila
	int n;			// qtd. de elementos da fila
	int capacidade;	// capacidade da fila
} Deque;

void inicializa_deque ( Deque *p, int c );
int deque_vazio ( Deque f );
int deque_cheio ( Deque f );
int inserir_inicio ( Deque *p, int info );
int remover_inicio ( Deque *p, int *info );
int inserir_fim ( Deque *p, int info );
int remover_fim ( Deque *p, int *info );
void mostra_deque ( Deque f );
void desaloca_deque ( Deque *p );

/*
Incremento circular: i = ( i + 1 ) % MAX;
Decremento circular: i = (MAX + i - 1) % MAX.
*/

