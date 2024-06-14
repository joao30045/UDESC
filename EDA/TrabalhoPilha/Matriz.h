#define COORDENADA_INVALIDA -1

typedef struct{
	int **dados;
	int lin, col;
} Matriz;

void inicializa_matriz( Matriz *p, int l, int c );
void mostra_matriz( Matriz m );
int carrega_arquivo( char *nome_arquivo, Matriz *p );
int set_valor( Matriz *p, int i, int j, int valor );
int get_valor( Matriz *p, int i, int j, int *p_valor );
int matrizes_iguais( Matriz m1, Matriz m2 );
void desaloca_matriz( Matriz *p );

