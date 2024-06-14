
#define ERRO_FILA_VAZIA -1
#define ERRO_FILA_CHEIA -2

typedef struct{
	int *dados; // dados da fila
	int fim;    // indica o índice do fim da fila
	int capacidade;
} Fila_Lenta;

void inicializa_fila ( Fila_Lenta *p, int c );
int fila_vazia ( Fila_Lenta f );
int fila_cheia ( Fila_Lenta f );
int inserir ( Fila_Lenta *p, int info );
int remover ( Fila_Lenta *p, int *info );
void mostra_fila ( Fila_Lenta f );
void desaloca_fila ( Fila_Lenta *p );

