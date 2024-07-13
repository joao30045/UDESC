#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void insertionSort(int arr[], int n) {
    int i, key, j;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

void printArray(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {
    srand(time(NULL)); // Seed para números aleatórios baseados no tempo

    int M = 10; // Número de elementos no vetor
    int vetor[M];

    // Preencher o vetor com números aleatórios
    for (int i = 0; i < M; i++) {
        vetor[i] = rand() % 100; // Números aleatórios de 0 a 99
    }

    printf("Vetor original:\n");
    printArray(vetor, M);

    insertionSort(vetor, M);

    printf("Vetor ordenado:\n");
    printArray(vetor, M);

    return 0;
}
