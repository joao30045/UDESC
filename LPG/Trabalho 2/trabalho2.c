#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct artista{
    char nome_ar[30], nacionalidade[30]; 
}; 
struct data{
	int dia, mes, ano;
};
struct musica{
    char nome_mus[30], estilo[30];
    int duracao;
    struct artista dados;
    struct data cadastro;
};

void carrega_arquivo(struct musica **v, int *n, char *nome_arq);
void salva_arquivo(struct musica *v, int n, char *nome_arq);

void le_musica(struct musica *p);
void le_artista(struct artista *p);
void le_data(struct data *p);

void mostra_musica(struct musica *v, int n);
void consulta(struct musica *v, int n);
void ordena(struct musica *v, int n);
int exclui(struct musica *v, int n);
int busca_musica(struct musica *v, int n);

int main(){
    int n = 0;
    struct musica *v = NULL;
    int opcao;

    carrega_arquivo( &v, &n, "musicas.txt");
    printf("--------------------------------------------------------\n");
    printf("\nBem vindo ao programa de cadastro de músicas.\n\n");
    printf("--------------------------------------------------------\n");

    do{
        printf("\nVocê tem %d músicas cadastradas.\n", n);
        printf("\nEscolha uma ação:\n\n");
        printf("1- Inserir novos registros\n");
        printf("2- Remover registros existentes\n");
        printf("3- Exibir todo o conteúdo do cadastro\n");
        printf("4- Consultar registros\n");
        printf("5- Ordenar os registros em ordem crescente do campo chave\n");
        printf("6- Sair\n");
        printf("\n");
        scanf("%d", &opcao);
        switch(opcao){
            case 1:
                n++;
                v = realloc(v, sizeof(struct musica) * n);
                if(busca_musica(v, n) == 1){
                    n--;
                    break;
                }else
                le_musica(&v[n-1]);
                break;
            case 2:
                if(exclui(v, n) == 1){
                    n--;
                }
                break;
            case 3:
                mostra_musica(v,n);
                break;
            case 4:
                consulta(v, n);
                break;
            case 5:
                ordena(v, n);
                mostra_musica(v, n);
                break;
            case 6:
                printf("Programa finalizado!\n");
                break;
        }
    }
    while(opcao != 6);
    salva_arquivo(v, n, "musicas.txt");
	free(v);
return 0;
}
void le_musica(struct musica *p){
    printf("\nVocê escolheu registrar uma nova música.\n");
    printf("Digite o nome da música: ");
    scanf(" %[^\n]", p-> nome_mus);
    printf("Digite o estilo: ");
	scanf(" %[^\n]", p->estilo);
    printf("Digite a duração da música: ");
    scanf("%d", &p-> duracao);
	printf("\nDigite a data de cadastro.\n");
	le_data(&p->cadastro);
    printf("\nDigite os dados do artista.\n");
	le_artista(&p->dados);
}
void le_data(struct data *p){
	printf("Digite o dia: ");
	scanf("%d", &p->dia);
	printf("Digite o mês: ");
	scanf("%d", &p->mes);
	printf("Digite o ano: ");
	scanf("%d", &p->ano);
}
void le_artista(struct artista *p){
    printf("Digite o nome do artista: ");
    scanf(" %[^\n]", p-> nome_ar);
    printf("Digite a nacionalidade do artista: ");
	scanf(" %[^\n]", p->nacionalidade);
}
void carrega_arquivo(struct musica **v, int *n, char *nome_arq){
	FILE *f = fopen(nome_arq, "rt");
	if( f == NULL ){
		printf("Arquivo nao encontrado!\n");
		return;
	}
	fscanf(f, "%d", n);
	
	*v = malloc(sizeof(struct musica) * *n);
	
	int i;
	for(i = 0; i < *n; i++){
		fscanf(f, " %[^\n]", (*v)[i].nome_mus);
        fscanf(f, " %[^\n]", (*v)[i].estilo);
        fscanf(f, " %d", &(*v)[i].duracao);
        fscanf(f, " %d", &(*v)[i].cadastro.dia);
        fscanf(f, " %d", &(*v)[i].cadastro.mes);
        fscanf(f, " %d", &(*v)[i].cadastro.ano);
        fscanf(f, " %[^\n]", (*v)[i].dados.nome_ar);
        fscanf(f, " %[^\n]", (*v)[i].dados.nacionalidade);
	}
	fclose(f);
}
void salva_arquivo(struct musica *v, int n, char *nome_arq){
	FILE *f = fopen( nome_arq, "wt" );
	fprintf(f, "%d\n", n );
	
	int i;
	for( i = 0 ; i < n ; i++ ){
		fprintf( f, "%s\n", v[i].nome_mus);
		fprintf( f, "%s\n", v[i].estilo);
        fprintf( f, "%d\n", v[i].duracao);
		fprintf( f, "%d %d %d\n", v[i].cadastro.dia, v[i].cadastro.mes, v[i].cadastro.ano);
        fprintf( f, "%s\n", v[i].dados.nome_ar);
        fprintf( f, "%s\n", v[i].dados.nacionalidade);
	}
	fclose( f );	
}
void mostra_musica(struct musica *v, int n){
    if(n == 0)
		printf("Não há músicas cadastradas!\n");
	else{
		int i;
		printf("\nListagem de músicas cadastradas\n");
        printf("%s %-25s %-25s %-15s %-15s %s\n", "ID", "NOME DA MUSICA", "NOME DO ARTISTA", "ESTILO", "NACIONALIDADE", "DATA DO CADASTRO");
        printf("--------------------------------------------------------------------------------------------------------------------------------\n");
		for( i = 0 ; i < n ; i++ ){
			printf("%d : ", i+1 );
            printf("%-24s %-25s %-15s %-15s %d/%d/%d\n", v[i].nome_mus, v[i].dados.nome_ar, v[i].estilo, v[i].dados.nacionalidade, v[i].cadastro.dia, v[i].cadastro.mes, v[i].cadastro.ano);
		}
        printf("\n");
	}
}
void consulta(struct musica *v, int n){
    if(n == 0){
        printf("\nNão há músicas cadastradas!\n");
    }else{
        int i, opcao, cont = 0;
        char comp[30];
        printf("\nEscolha um dos métodos de busca:\n");
        printf("1 - Nome do artista\n");
        printf("2 - Nacionalidade\n");
        printf("3 - Nome da música\n");
        printf("4 - Estilo\n");
        scanf("%d", &opcao);
        switch(opcao){
        case 1:
            printf("\nDigite o nome do artista que deseja buscar: \n");
            scanf(" %[^\n]", comp);
            for(i = 0; i < n; i++){
                if(strcmp(v[i].dados.nome_ar, comp) == 0){
                    cont++;
                    printf("%d segundos | %s | %s | %s | %s | %d/%d/%d\n", v[i].duracao, v[i].nome_mus, v[i].dados.nome_ar, v[i].estilo, v[i].dados.nacionalidade, v[i].cadastro.dia, v[i].cadastro.mes, v[i].cadastro.ano);
                }
            }
            if(cont == 0)
                printf("Artista não encontrado.\n");
            break;
        case 2:
            printf("\nDigite a nacionalidade que deseja buscar: \n");
            scanf(" %[^\n]", comp);
            for(i = 0; i < n; i++){
                if(strcmp(v[i].dados.nacionalidade, comp) == 0){
                    cont++;
                    printf("%d segundos | %s | %s | %s | %s | %d/%d/%d\n", v[i].duracao, v[i].nome_mus, v[i].dados.nome_ar, v[i].estilo, v[i].dados.nacionalidade, v[i].cadastro.dia, v[i].cadastro.mes, v[i].cadastro.ano);
                }
            }
            if(cont == 0)
                printf("Nacionalidade não encontrada.\n");
            break;
        case 3:
            printf("\nDigite o nome da música que deseja buscar: \n");
            scanf(" %[^\n]", comp);
            for(i = 0; i < n; i++){
                if(strcmp(v[i].nome_mus, comp) == 0){
                    cont++;
                    printf("%d segundos | %s | %s | %s | %s | %d/%d/%d\n", v[i].duracao, v[i].nome_mus, v[i].dados.nome_ar, v[i].estilo, v[i].dados.nacionalidade, v[i].cadastro.dia, v[i].cadastro.mes, v[i].cadastro.ano);
                }
            }
            if(cont == 0)
                printf("Música não encontrada.\n");
            break;
        case 4:
            printf("\nDigite o estilo que deseja buscar: \n");
            scanf(" %[^\n]", comp);
            for(i = 0; i < n; i++){
                if(strcmp(v[i].estilo, comp) == 0){
                    cont++;
                    printf("%d segundos | %s | %s | %s | %s | %d/%d/%d\n", v[i].duracao, v[i].nome_mus, v[i].dados.nome_ar, v[i].estilo, v[i].dados.nacionalidade, v[i].cadastro.dia, v[i].cadastro.mes, v[i].cadastro.ano);
                }
            }
            if(cont == 0)
                printf("Estilo não encontrado.\n");
            break;
        }
    }
}
void ordena(struct musica *v, int n){
    int i, j;
    struct musica aux;
    printf("\nAs músicas serão ordenadas em ordem alfabética ascendente.\n");
    for(i = 0; i < n; i++){
        aux = v[i];
        for(j = i; (j > 0) && (strcmp(aux.nome_mus, v[j-1].nome_mus) < 0); j--){
            v[j] = v[j-1];
        }
        v[j] = aux;
    }
}
int exclui(struct musica *v, int n){
    if(n == 0){
        printf("Não há músicas cadastradas!\n");
    }else{
    int i, j, cont = 0;
    char mus[30];
    printf("\nDigite o nome da música a ser excluída:\n");
    scanf(" %[^\n]", mus);
    for(i = 0; i < n; i++){
        if(strcmp(v[i].nome_mus, mus) == 0){
            cont++;
            for(j = i; j < n - 1; j++){
                v[j] = v[j + 1];
            }
            return 1;
        }
    }
    if(cont == 0){
        printf("Música não encontrada.\n");
    } 
    }
return 0;
}
int busca_musica(struct musica *v, int n){
    if(n == 0){
        printf("Não há músicas cadastradas!\n");
    }else{
    int i;
    char musica[30];
    printf("Digite o nome da música a ser cadastrada:\n");
    scanf(" %[^\n]", musica);
    for(i = 0; i < n; i++){
        if(strcmp(v[i].nome_mus, musica) == 0){   
            printf("A música - %s - já está cadastrada. Cadastre outra música.\n", musica);
            return 1;
        }
    }
    printf("Esta música ainda não consta na base de dados.\n");
    }
return 0;
}