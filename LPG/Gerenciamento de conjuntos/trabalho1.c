#include <stdio.h>
#include <stdlib.h>

#define M 4
#define N 4

int pertence(int x, int v[], int n);
void remover_conjunto(int conjunto, int cont, int m[M][N], int tamanho[]);
void uniao_cojunto(int *x, int m, int *y, int n, int *z, int k);
void interseccao_conjunto(int *x, int m, int *y, int n, int *z, int k);
void mostrar_matriz(int *a, int m, int n);
void busca_valor(int a, int cont, int m[M][N], int tamanho[]);

int main(){

    int m[M][N] = {0};
    int tamanho[M] = {0};
    int cont = 0, parada = 1;
    int conjunto, conjunto1, conjunto2;
    int opcao, a;

    while(parada == 1){
        printf("\n");
        printf("Escolha uma das opções para o gerenciamento de conjuntos:\n");
        printf("1 - Criar um novo conjunto vazio.\n");
        printf("2 - Inserir dados em um conjunto.\n");
        printf("3 - Remover um conjunto.\n");
        printf("4 - Fazer a união entre dois conjuntos.\n");
        printf("5 - Fazer a intersecção entre dois conjuntos.\n");
        printf("6 - Mostrar um conjunto.\n");
        printf("7 - Mostrar todos os conjuntos.\n");
        printf("8 - Busca por um valor.\n");
        printf("9 - Sair do programa.\n");
        scanf("%d", &opcao);
        switch (opcao){
            case 1:
                if(cont == M){
                    printf("Limite de conjuntos excedido.\n");
                    break;
                }
                printf("Criado o conjunto %d.\n", cont);
                cont++;
                break;
            case 2:
                if(cont == N){
                    printf("Limite de conjuntos excedido.\n");
                    break;
                }
                if(cont == 0){
                    printf("Conjunto ainda não foi criado.\n");
                    break;
                }else{
                    printf("Qual o conjunto escolhido?\n");
                    printf("Há os conjuntos entre 0 e %d.\n", cont-1);
                    scanf("%d", &conjunto);
                    if(conjunto > cont-1 || conjunto < 0){
                        printf("Conjunto inválido.\n");
                    }
                    if(tamanho[conjunto] == N){
                        printf("Conjunto cheio, use outro conjunto.\n");
                    }else{
                        while(tamanho[conjunto] < N){
                            printf("Entre com os valores do conjunto %d.\n", conjunto);
                            scanf("%d", &a);
                            if(a != 0){
                                if(pertence(a, *m + N *conjunto, tamanho[conjunto])){
                                    printf("O número já pertence ao conjunto.\n");
                                }else{
                                    m[conjunto][tamanho[conjunto]] = a;
                                    tamanho[conjunto] += 1;
                                }
                            }else{
                                break;
                            }
                        }
                    }                    
                }
                break;
            case 3:
                if(cont == 0){
                    printf("Não há conjunto para remover.\n");
                }else{
                    printf("Qual o conjunto para remover?\n");
                    printf("Há os conjuntos entre 0 e %d.\n", cont-1);
                    scanf("%d", &conjunto);
                    if(conjunto > cont-1 || conjunto < 0){
                        printf("Conjunto inválido.\n");
                    }else{
                        remover_conjunto(conjunto, cont, m, tamanho);
                    } 
                    cont = cont - 1;
                    printf("Conjunto %d removido.\n", conjunto);
                }
                break;
            case 4:
                if(cont == 0){
                    printf("Não há conjunto criado.\n");
                    break;
                }
                if(cont == 1){
                    printf("Só há 1 conjunto criado.\n");
                    break;
                }
                if(cont == M){
                    printf("Limite de conjuntos atingido. Não há espaço.\n");
                    break;
                }else{
                    printf("Digite o primeiro conjunto:\n");
                    scanf("%d", &conjunto1);
                    printf("Digite o segundo conjunto:\n");
                    scanf("%d", &conjunto2);
                    if(conjunto1 > cont - 1 || conjunto1 < 0 || conjunto1 > cont - 2 || conjunto2 < 0){
                        printf("Conjunto inválido.\n");
                    }else{
                        uniao_cojunto(*m+N*conjunto1,tamanho[conjunto1],*m+N*conjunto2,tamanho[conjunto2],*m+N*cont,N);
                        printf("União feita no conjunto %d.", cont);
                        int j = 0;
                        while(m[cont][j] != 0 && j < N){
                            tamanho[cont]++;
                            j++;
                        }
                    cont++;
                    }
                }
                cont++;
                break;
            case 5:
                if(cont == 0){
                    printf("Não há conjunto criado.\n");
                    break;
                }
                if(cont == 1){
                    printf("Só há 1 conjunto criado.\n");
                    break;
                }
                if(cont == M){
                    printf("Limite de conjuntos atingido. Não há espaço.\n");
                    break;
                }else{
                    printf("Digite o primeiro conjunto:\n");
                    scanf("%d", &conjunto1);
                    printf("Digite o segundo conjunto:\n");
                    scanf("%d", &conjunto2);
                    if(conjunto1 > cont - 1 || conjunto1 < 0 || conjunto1 > cont - 2 || conjunto2 < 0){
                        printf("Conjunto inválido.\n");
                    }else{
                        interseccao_conjunto(*m+N*conjunto1,tamanho[conjunto1],*m+N*conjunto2,tamanho[conjunto2],*m+N*cont,N);
                        printf("Intersecção feita no conjunto %d.", cont);
                        int j = 0;
                        while (m[cont][j] != 0 && j < N){
                            tamanho[cont]++;
                            j++;
                        }
                    cont++;
                    } 
                }
                break;
            case 6:
                if(cont == 0){
                    printf("Não há conjunto para mostrar.\n");
                }else{
                    printf("Qual conjunto mostrar?\n");
                    printf("Há os conjuntos entre 0 e %d.\n", cont - 1);
                    scanf("%d", &conjunto);
                    if(conjunto > cont-1 || conjunto < 0){
                        printf("Conjunto inválido.\n");
                    }else{
                        printf("\nConjunto %d:\n", conjunto);
                        mostrar_matriz(*m + (conjunto *N), 1, tamanho[conjunto]);
                    }
                    printf("\n");
                }
                break;
            case 7:
                if(cont == 0){
                    printf("Não há conjunto para mostrar.\n");
                }else{
                    printf("Há os seguintes conjuntos criados:\n");
                    int i;
                    for(i = 0; i < cont; i++){
                        printf("\nConjunto %d: \n", i);
                        mostrar_matriz(*m + i *N, 1, tamanho[i]);
                    }
                    printf("\n");
                }
                break;
            case 8: 
                if(cont == 0){
                    printf("Não há conjunto criado.\n");
                }else{
                    busca_valor(a, cont, m, tamanho);
                }
                break;
            case 9: 
                printf("Programa finalizado.\n");
                parada = 0;
                break;
        }
    }
return 0;
}
int pertence(int a, int v[], int n){
    if(n == 0){
        return 0;
    }
    if(a == v[0]){
        return 1;
    }
    return pertence(a, v+1, n-1);
}
void remover_conjunto(int conjunto, int cont, int m[M][N], int tamanho[]){
    int i, j;
    for(i = conjunto + 1; i < cont; i++){
        for(j = 0; j < N; j++){
            m[i - 1][j] = m[i][j];
        }
        tamanho[i - 1] = tamanho[i];
    }
    for(j = 0; j < N; j++){
        m[cont - 1][j] = 0;
    }
    tamanho[cont - 1] = 0; 
}
void uniao_cojunto(int *x, int m, int *y, int n, int *z, int k){
    int i, j;
    for(i = 0; i < m; i++){
        z[i] = x[i];
    }
    for(j = 0; j < n && i < k; j++){
        if(!(pertence(y[j], z, i))){
            z[i] = y[j];
            i++;
        }    
    }
}
void interseccao_conjunto(int *x, int m, int *y, int n, int *z, int k){
    int i, j = 0;
    for (i = 0; i < m; i++){
        if(pertence(x[i], y, n)){
            z[j] = x[i];
            j++;
        }
    }
}
void mostrar_matriz(int *a, int m, int n){
    if(n == 0){
        return;
    }
    int i, j;
    for(i = 0; i < m; i++){
        for(j = 0; j < n; j++){
            printf("%d ", a[n *i + j]);
        }
    }
}
void busca_valor(int a, int cont, int m[M][N], int tamanho[]){
    printf("Digite o número de busca.\n");
    scanf("%d", &a);
    if(a == 0){
        printf("Número inválido (0).\n");
    }else{
        int i, j = 0, contem[M] = {0};
        for (i = 0; i < cont; i++){
            if(pertence(a, *m + N *i, tamanho[i])){
                contem[j]=i;
                j++;
            }
        }
        if(j == 0){
            printf("Não há conjunto com %d.\n",a);
        }
        if(j == 1){
            printf("O conjunto %d contém %d.\n", contem[0], a);
        }else{
            for(i = 0; i < j - 2; i++){  
            }
            printf("Os conjuntos %d e %d contém %d.\n",contem[j-2],contem[j-1],a);
        }
    }
}