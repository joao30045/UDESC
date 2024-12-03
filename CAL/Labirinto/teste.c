#include <stdio.h>
#include <stdlib.h>

#define N 61

int isSafe(int maze[N][N], int x, int y) {
    return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 0);
}

int solveMazeUtil(int maze[N][N], int x, int y) {
    if (x == N-1 && y == N-1 && maze[x][y] == 0) {
        maze[x][y] = 2;
        return 1;
    }
    if (isSafe(maze, x, y)) {
        maze[x][y] = 2;  

        if (solveMazeUtil(maze, x + 1, y))  // Direita
            return 1;

        if (solveMazeUtil(maze, x, y + 1))  // Baixo
            return 1;

        if (solveMazeUtil(maze, x - 1, y))  // Esquerda
            return 1;

        if (solveMazeUtil(maze, x, y - 1))  // Cima
            return 1;

        maze[x][y] = 0;  // Backtracking
        return 0;
    }

    return 0;
}

void printMaze(int maze[N][N]){
    printf("Caminho no labirinto:\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (maze[i][j] == 1) {
                printf("#");  // Parede
            } else if (maze[i][j] == 2) {
                printf(".");  // Caminho
            } else {
                printf(" ");  // Espaço vazio
            }
        }
        printf("\n");
    }
}

void loadMaze(int maze[N][N], char *fname){
    FILE *f;
    int i, lineN = 0;
    char line[N+10];
    f = fopen(fname, "r");
    while(fgets(line, sizeof(line), f) != NULL){
        i = 0;
        if(line[0]=='0' || line[0] == '1'){
            while(line[i] != '\0'){
                maze[lineN][i] = line[i] - '0';
                i++;
                if(i>=N) break;
            }
            lineN++;
            if(lineN>=N){ break;}
        }
    }

    fclose(f);
}

int solveMaze(int maze[N][N]) {
    if (solveMazeUtil(maze, 0, 0) == 0) {
        printf("Não há solução para o labirinto.\n");
        return 0;
    }

    printMaze(maze);
    return 1;
}

int main() {
    int maze[N][N];

    loadMaze(maze, "lab.txt");

    if (solveMaze(maze)) {
        printf("Labirinto resolvido!\n");
    } else {
        printf("Não foi possível encontrar uma solução.\n");
    }

    return 0;
}
