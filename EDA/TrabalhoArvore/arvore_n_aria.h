typedef struct noArvore{
	int info;
	struct noArvore *filho, *irmao;
}NoArvore;

typedef struct{
	NoArvore *raiz;
}Arvore;

void inicializa_Arvore( Arvore *p, int valor_raiz );
int insere_filho( Arvore a, int pai, int filho );
void percurso_profundidade( Arvore a );
void gera_xml( Arvore a , int deslocamento);
void desaloca_arvore( Arvore *p );

/*
  Vers�o 1.0
  
  To do List:
  - Percurso em profundidade;
  - Remo��o;
  - Tratamento de valores repetidos:
    - Inser��o (?);
    - Busca retornando uma lista de n�s;

*/
