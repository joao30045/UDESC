#include <GLFW/glfw3.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

#define ROWS 20
#define COLS 10
#define BLOCK_SIZE 30
#define MAX_PARTICLES 1000

typedef struct {
    float x, y;
    float dx, dy;
    float life;
    int color;
} Particle;

Particle particles[MAX_PARTICLES];
int particleCount = 0;

typedef struct {
    int x, y;
} Point;

typedef struct {
    Point blocks[4];
    int color;
} Tetromino;

float colors[7][3] = {
    {1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 1.0},
    {1.0, 1.0, 0.0}, {1.0, 0.0, 1.0}, {0.0, 1.0, 1.0},
    {1.0, 0.5, 0.0}};

int board[ROWS][COLS] = {0};

Tetromino tetrominos[7] = {
    {{{0, 0}, {1, 0}, {2, 0}, {3, 0}}, 1}, {{{0, 0}, {0, 1}, {1, 0}, {1, 1}}, 2},
    {{{0, 0}, {1, 0}, {2, 0}, {1, 1}}, 3}, {{{0, 0}, {1, 0}, {1, 1}, {2, 1}}, 4},
    {{{1, 0}, {2, 0}, {0, 1}, {1, 1}}, 5}, {{{0, 0}, {1, 0}, {2, 0}, {0, 1}}, 6},
    {{{0, 0}, {1, 0}, {2, 0}, {2, 1}}, 7}};

Tetromino currentPiece;

double lastManualMoveTime = 0.0;
double manualMoveDelay = 0.2;  
double lastRotationTime = 0.0;  // Nova variável para controlar a rotação
double rotationDelay = 0.2;     // Tempo de espera para a rotação (em segundos)

void addParticle(float x, float y, int color) {
    if (particleCount < MAX_PARTICLES) {
        particles[particleCount++] = (Particle){
            x, y,
            ((float)rand() / RAND_MAX - 0.5f) * 2.0f,  
            ((float)rand() / RAND_MAX - 0.5f) * 2.0f,  
            2.0f,  
            color};
    }
}

void drawParticle(Particle* p) {
    float r = colors[p->color - 1][0];
    float g = colors[p->color - 1][1];
    float b = colors[p->color - 1][2];

    glColor4f(r, g, b, p->life);  
    glBegin(GL_QUADS);
    glVertex2f(p->x, p->y);
    glVertex2f(p->x + BLOCK_SIZE / 4, p->y);
    glVertex2f(p->x + BLOCK_SIZE / 4, p->y + BLOCK_SIZE / 4);
    glVertex2f(p->x, p->y + BLOCK_SIZE / 4);
    glEnd();
}

void updateParticles(float deltaTime) {
    for (int i = 0; i < particleCount; i++) {
        particles[i].x += particles[i].dx * deltaTime * 50;
        particles[i].y += particles[i].dy * deltaTime * 50;
        particles[i].life -= deltaTime;

        if (particles[i].life <= 0) {
            particles[i] = particles[--particleCount];
            i--;
        }
    }
}

void drawParticles() {
    for (int i = 0; i < particleCount; i++) {
        drawParticle(&particles[i]);
    }
}

void drawBlock(int x, int y, int color) {
    float r = colors[color - 1][0];
    float g = colors[color - 1][1];
    float b = colors[color - 1][2];

    glColor3f(r, g, b);
    glBegin(GL_QUADS);
    glVertex2f(x, y);
    glVertex2f(x + BLOCK_SIZE, y);
    glVertex2f(x + BLOCK_SIZE, y + BLOCK_SIZE);
    glVertex2f(x, y + BLOCK_SIZE);
    glEnd();
}

void drawBoard() {
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if (board[i][j] != 0) {
                drawBlock(j * BLOCK_SIZE, i * BLOCK_SIZE, board[i][j]);
            }
        }
    }
}

bool checkCollision(Tetromino piece) {
    for (int i = 0; i < 4; i++) {
        int x = piece.blocks[i].x;
        int y = piece.blocks[i].y;

        if (x < 0 || x >= COLS || y >= ROWS || (y >= 0 && board[y][x] != 0)) {
            return true;
        }
    }
    return false;
}

void clearFullLines() {
    for (int i = 0; i < ROWS; i++) {
        bool full = true;
        for (int j = 0; j < COLS; j++) {
            if (board[i][j] == 0) {
                full = false;
                break;
            }
        }

        if (full) {
            for (int j = 0; j < COLS; j++) {
                addParticle(j * BLOCK_SIZE, i * BLOCK_SIZE, board[i][j]);
            }

            for (int k = i; k > 0; k--) {
                for (int j = 0; j < COLS; j++) {
                    board[k][j] = board[k - 1][j];
                }
            }
            for (int j = 0; j < COLS; j++) {
                board[0][j] = 0;
            }
        }
    }
}

void spawnPiece() {
    currentPiece = tetrominos[rand() % 7];
    for (int i = 0; i < 4; i++) {
        currentPiece.blocks[i].y -= 2;
        currentPiece.blocks[i].x += 3;
    }
}

void movePiece(int dx, int dy) {
    Tetromino temp = currentPiece;
    for (int i = 0; i < 4; i++) {
        temp.blocks[i].x += dx;
        temp.blocks[i].y += dy;
    }
    if (!checkCollision(temp)) {
        currentPiece = temp;
    } else if (dy > 0) {
        for (int i = 0; i < 4; i++) {
            int x = currentPiece.blocks[i].x;
            int y = currentPiece.blocks[i].y;
            if (y >= 0) board[y][x] = currentPiece.color;
        }
        clearFullLines();
        spawnPiece();
    }
}

void drawPiece() {
    for (int i = 0; i < 4; i++) {
        int x = currentPiece.blocks[i].x * BLOCK_SIZE;
        int y = currentPiece.blocks[i].y * BLOCK_SIZE;
        drawBlock(x, y, currentPiece.color);
    }
}

void rotatePiece() {
    Tetromino temp = currentPiece;
    Point pivot = temp.blocks[1];

    for (int i = 0; i < 4; i++) {
        int x = temp.blocks[i].x - pivot.x;
        int y = temp.blocks[i].y - pivot.y;
        int rotatedX = -y;
        int rotatedY = x;
        temp.blocks[i].x = rotatedX + pivot.x;
        temp.blocks[i].y = rotatedY + pivot.y;
    }
    if (!checkCollision(temp)) {
        currentPiece = temp;
    }
}

int main() {
    srand(time(NULL));

    if (!glfwInit()) {
        return -1;
    }

    GLFWwindow* window = glfwCreateWindow(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE, "Tetris", NULL, NULL);
    if (!window) {
        glfwTerminate();
        return -1;
    }
    glfwMakeContextCurrent(window);

    glOrtho(0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE, 0, -1, 1);

    spawnPiece();

    double lastTime = glfwGetTime();
    while (!glfwWindowShouldClose(window)) {
        glClear(GL_COLOR_BUFFER_BIT);

        double currentTime = glfwGetTime();
        float deltaTime = currentTime - lastTime;

        if (currentTime - lastTime > 0.5) {
            movePiece(0, 1);
            lastTime = currentTime;
        }

        updateParticles(deltaTime);
        drawBoard();
        drawParticles();
        drawPiece();

        glfwSwapBuffers(window);
        glfwPollEvents();

        // Controle da movimentação manual
        if (glfwGetTime() - lastManualMoveTime > manualMoveDelay) {
            if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
                movePiece(-1, 0);
                lastManualMoveTime = glfwGetTime();
            }
            if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
                movePiece(1, 0);
                lastManualMoveTime = glfwGetTime();
            }
            if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
                movePiece(0, 1);
                lastManualMoveTime = glfwGetTime();
            }


            if (glfwGetKey(window, GLFW_KEY_R) == GLFW_PRESS && glfwGetTime() - lastRotationTime > rotationDelay) {
                rotatePiece();
                lastRotationTime = glfwGetTime(); 
            }
        }
    }

    glfwTerminate();
    return 0;
}

