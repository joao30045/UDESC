#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct data{
	int dia, mes, ano;
};

struct pessoa{
	char nome[30], telefone[20];
	struct data nascimento;
};

void le_pessoa( struct pessoa *p );
void le_pessoa_v2( struct pessoa *p );
void mostra_pessoa( struct pessoa x );
void mostra_pessoa_v2( struct pessoa x );

void le_data( struct data *p );
void mostra_data (struct data x );

void mostra_vetor( struct pessoa *v, int n );
int busca_pessoa( struct pessoa *v, int n, char *nome );

void carrega_arquivo( struct pessoa **v, int *n, char *nome_arq );
void salva_arquivo( struct pessoa *v, int n, char *nome_arq );


int main(int argc, char *argv[]) {
	int n = 0;  // Quantidade de pessoas
	struct pessoa *v = NULL; // Ponteiro para o vetor de pessoas
	char nome[30];
	int idx;
	
	carrega_arquivo( &v, &n, "pessoas.txt");
	
	int opcao;
	do{
		system("CLS");
		printf("1-Cadastrar\n2-Mostrar lista\n3-Buscar\n4-Sair\n");
		scanf("%d", &opcao);
		
		switch( opcao ){
			case 1:
				n++;
				v = realloc( v, sizeof( struct pessoa ) * n );
				le_pessoa_v2( &v[n-1] );
				system("PAUSE");
				break;
			
			case 2:
				mostra_vetor( v, n );
				system("PAUSE");
				break;
				
			case 3:
				printf("Digite o nome da pessoa a ser buscada: ");
				scanf(" %[^\n]", nome );
				
				idx = busca_pessoa( v, n, nome );
				if( idx == -1 )
					printf("Pessoa nao encotrada!\n");
				else{
					printf("%d : ", idx+1 );
					mostra_pessoa_v2( v[idx] );
				}
				system("PAUSE");
				break;
			
		}
		
	}while( opcao != 4 );
	
	salva_arquivo( v, n, "pessoas.txt");
	
	free( v );
	
	return 0;
}

void le_pessoa_v2( struct pessoa *p ){
	printf("Digite o nome: ");
	scanf(" %[^\n]", p->nome);
	printf("Digite o telefone: ");
	scanf(" %[^\n]", p->telefone);
	printf("Digite a data de nascimento:\n");
	le_data( &p->nascimento );
}

void mostra_pessoa_v2( struct pessoa x ){
	printf("(%s, %s, [", x.nome, x.telefone);
	mostra_data( x.nascimento );
	printf("])\n");
}

void le_data( struct data *p ){
	printf("Digite o dia: ");
	scanf("%d", &p->dia);
	printf("Digite o mes: ");
	scanf("%d", &p->mes);
	printf("Digite o ano: ");
	scanf("%d", &p->ano);
}

void mostra_data (struct data x ){
	printf("%d/%d/%d", x.dia, x.mes, x.ano);
}

void mostra_vetor( struct pessoa *v, int n ){
	if( n == 0 )
		printf("Nao ha pessoas cadastradas!\n");
	else{
		int i;
		printf("Lista de pessoas cadatradas:\n");
		for( i = 0 ; i < n ; i++ ){
			printf("%d : ", i+1 );
			mostra_pessoa_v2( v[i] );
		}
	}
}

int busca_pessoa( struct pessoa *v, int n, char *nome ){
	int i;
	for( i = 0 ; i < n ; i++ )
		if( strcmp( v[i].nome, nome ) == 0 )
			return i;
	
	return -1;
}

void carrega_arquivo( struct pessoa **v, int *n, char *nome_arq ){
	FILE *f = fopen( nome_arq, "rt" );
	if( f == NULL ){
		printf("Arquivo nao encontrado!\n");
		system("PAUSE");
		return;
	}
	fscanf( f, "%d", n );
	
	*v = malloc( sizeof( struct pessoa ) * *n );
	
	int i;
	for( i = 0 ; i < *n ; i++ ){
		fscanf( f, " %[^\n]", (*v)[i].nome );
		fscanf( f, " %[^\n]", (*v)[i].telefone );
		fscanf( f, "%d %d %d", &(*v)[i].nascimento.dia, &(*v)[i].nascimento.mes, &(*v)[i].nascimento.ano );
	}
	
	fclose( f );
	printf("%d registros carregados!\n", *n);
	system("PAUSE");
}


void salva_arquivo( struct pessoa *v, int n, char *nome_arq ){
	FILE *f = fopen( nome_arq, "wt" );
	
	fprintf(f, "%d\n", n );
	
	int i;
	for( i = 0 ; i < n ; i++ ){
		fprintf( f, "%s\n", v[i].nome);
		fprintf( f, "%s\n", v[i].telefone);
		fprintf( f, "%d %d %d\n", v[i].nascimento.dia, v[i].nascimento.mes, v[i].nascimento.ano);
	}
	fclose( f );	
}

