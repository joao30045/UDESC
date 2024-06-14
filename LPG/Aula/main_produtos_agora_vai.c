#include <stdio.h>
#include <stdlib.h>

struct Produto{
	int codigo;
	char descricao[20];
	float preco;
};

void le_produto( struct Produto *p );
void mostra_produto( struct Produto x );

int main(int argc, char *argv[]) {
	int n, i;
	printf("Quantos produtos? ");
	scanf("%d", &n);
	
	struct Produto *v = malloc( sizeof(struct Produto) * n );
	
	for( i = 0 ; i < n ; i++ ){
		printf("Entrada de dados do produto %d:\n", i+1);
		le_produto( v+i ); // &v[i] 
	}
	printf("\nLista de Produtos:\n");
	for( i = 0 ; i < n ; i++ ){
		printf("Produto %d: ", i+1 );
		mostra_produto( v[i] );
	}
	free( v );
	
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

