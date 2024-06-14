#include <stdio.h>
#include <stdlib.h>

struct Produto{
	int codigo;
	char descricao[20];
	float preco;
};

void le_produto( struct Produto *p );
void mostra_produto( struct Produto x );
int busca_produto( struct Produto *vet, int k, int codigo );

int main(int argc, char *argv[]) {
	int n, i;
	/*
	printf("Quantos produtos? ");
	scanf("%d", &n);
	
	struct Produto *v = malloc( sizeof(struct Produto) * n );
	
	for( i = 0 ; i < n ; i++ ){
		printf("Entrada de dados do produto %d:\n", i+1);
		le_produto( v+i ); // &v[i] 
	}
	*/
	n = 4;
	struct Produto v[4] = { { 11, "expresso", 5 },
							{ 22, "capuccino", 10},
							{ 33, "bolo de cenoura", 12 },
							{ 44, "bolo de chocolate", 15} };
	
	printf("\nLista de Produtos:\n");
	for( i = 0 ; i < n ; i++ ){
		printf("Produto %d: ", i+1 );
		mostra_produto( v[i] );
	}
	
	int cod;
	printf("Digite um codigo: ");
	scanf("%d", &cod);
	
	int idc = busca_produto( v, n, cod );
	if( idc == -1 )
		printf("Produto nao encontrado!\n");
	else{
		printf("Produto %d: ", idc+1 );
		mostra_produto( v[idc] );
	}
	
	//free( v );
	
	/*
	struct Produto p1;	
	le_produto( &p1 );	
	mostra_produto( p1 );	
	struct Produto p2 = { 234, "cafe expresso", 5 };	
	mostra_produto( p2 );
	*/	
	return 0;
}

void le_produto( struct Produto *p ){
	printf("Digite o codigo: ");
	scanf("%d", &p->codigo);
	printf("Digite a descricao: ");
	scanf(" %[^\n]s", p->descricao);
	printf("Digite o preco: ");
	scanf("%f", &p->preco);
}

void mostra_produto( struct Produto x ){
	printf("(%d, %s, R$%.2f)\n", x.codigo, x.descricao, x.preco);
}

int busca_produto( struct Produto *vet, int k, int codigo ){
	int i;
	for( i = 0 ; i < k ; i++ )
		if( vet[i].codigo == codigo )
			return i; // Encontrou no índice i.
	
	return -1; // Não encontrou!
}

