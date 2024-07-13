#include <stdio.h>
#include <stdlib.h>

#define CAPACIDADE_MEMORIA 7

void selectionSort(int arr[], int n) {
    int i, j, min_idx;
    for (i = 0; i < n - 1; i++) {
        min_idx = i;
        for (j = i + 1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        int temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;
    }
}

void printPartition(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void merge(int arr[], int inicio, int meio, int fim) {
    int n1 = meio - inicio + 1;
    int n2 = fim - meio;

    int *L = (int *)malloc(n1 * sizeof(int));
    int *R = (int *)malloc(n2 * sizeof(int));

    for (int i = 0; i < n1; i++) {
        L[i] = arr[inicio + i];
    }
    for (int j = 0; j < n2; j++) {
        R[j] = arr[meio + 1 + j];
    }

    int i = 0, j = 0, k = inicio;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }

    free(L);
    free(R);
}

void selectionNatural(int arr[], int n) {
    int numParticoes = (n + CAPACIDADE_MEMORIA - 1) / CAPACIDADE_MEMORIA;
    int *indicesParticoes = (int *)malloc(numParticoes * sizeof(int));
    if (indicesParticoes == NULL) {
        printf("Erro ao alocar memoria para indices de particoes.\n");
        return;
    }

    for (int i = 0; i < numParticoes; i++) {
        indicesParticoes[i] = i * CAPACIDADE_MEMORIA;
    }

    while (1) {
        for (int i = 0; i < numParticoes; i++) {
            if (indicesParticoes[i] >= n) {
                continue;
            }

            int tamanhoParticao = (i == numParticoes - 1) ? (n - indicesParticoes[i]) : CAPACIDADE_MEMORIA;
            selectionSort(&arr[indicesParticoes[i]], tamanhoParticao);
            indicesParticoes[i] += tamanhoParticao;
        }

        int numAtivos = 0;
        for (int i = 0; i < numParticoes; i++) {
            if (indicesParticoes[i] < n) {
                numAtivos++;
            }
        }

        if (numAtivos <= 1) {
            break;
        }

        int i = 0, j = 0;
        while (i < numParticoes) {
            if (indicesParticoes[i] < n) {
                arr[j++] = arr[indicesParticoes[i]];
                indicesParticoes[i]++;
            }
            if (i < numParticoes - 1 && indicesParticoes[i] == indicesParticoes[i + 1]) {
                i += 2;
            } else {
                i++;
            }
        }

        int fim = numParticoes - 1;
        for (int inicio = 0; inicio < fim; inicio++, fim--) {
            merge(arr, inicio * CAPACIDADE_MEMORIA, (inicio + 1) * CAPACIDADE_MEMORIA - 1, (fim + 1) * CAPACIDADE_MEMORIA - 1);
        }
    }

    free(indicesParticoes);
}

int main() {
    int arquivo[] = {30, 14, 15, 75, 32, 6, 5, 81, 48, 41, 87, 18, 56, 20, 26, 4, 21, 65, 22, 49, 11, 16, 8, 12, 44, 9, 7, 81, 23, 19, 1, 78, 13, 16, 51, 8};
    int tamanhoArquivo = sizeof(arquivo) / sizeof(arquivo[0]);

    printf("Arquivo original:\n");
    printPartition(arquivo, tamanhoArquivo);

    selectionNatural(arquivo, tamanhoArquivo);

    printf("\nArquivo ordenado usando Selecao Natural:\n");
    printPartition(arquivo, tamanhoArquivo);

    return 0;
}
