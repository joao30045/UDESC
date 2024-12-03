// gcc robo_animado.c -lglfw -lGL -lGLU -lm -o robo_animado && ./robo

#define GLFW_INCLUDE_NONE

#include <stdio.h>
#include <stdlib.h>
#include <GLFW/glfw3.h>   
#include <GL/gl.h>       
#include <GL/glu.h>      

void processInput(GLFWwindow *window);

#define SCREEN_WIDTH  800
#define SCREEN_HEIGHT 600

static GLfloat yRot = 0.0f, xRot = 0.0f; 
static GLint cotovelo = 0, antebraco = 0; 
static GLint coxa_direita = 0, coxa_esquerda = 0, joelho_direito = 0, joelho_esquerdo = 0;
static GLint braco_direito = 0, braco_esquerdo = 0;
static GLint cont = 0, cont2 = 0;                      

void resizeWindow(GLFWwindow* window, int w, int h ){
    GLfloat fAspect;  
    if(h == 0)  
        h = 1;  
    glViewport(0, 0, w, h);  
    fAspect = (GLfloat)w/(GLfloat)h;  
    glMatrixMode(GL_PROJECTION);  
    glLoadIdentity();  
    gluPerspective(35.0f, fAspect, 1.0, 40.0);  
    glMatrixMode(GL_MODELVIEW);  
    glLoadIdentity();  
} 

void setupRC(){  
    GLfloat  whiteLight[] = { 0.05f, 0.05f, 0.05f, 1.0f };  
    GLfloat  sourceLight[] = { 0.25f, 0.25f, 0.25f, 1.0f };  
    GLfloat  lightPos[] = { -10.f, 5.0f, 5.0f, 1.0f };  
    glEnable(GL_DEPTH_TEST);    
    glFrontFace(GL_CCW);        
    glEnable(GL_CULL_FACE);     
    glEnable(GL_LIGHTING);   
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT,whiteLight);  
    glLightfv(GL_LIGHT0,GL_AMBIENT,sourceLight);  
    glLightfv(GL_LIGHT0,GL_DIFFUSE,sourceLight);  
    glLightfv(GL_LIGHT0,GL_POSITION,lightPos);  
    glEnable(GL_LIGHT0);  
    glEnable(GL_COLOR_MATERIAL);  
    glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);  
    glClearColor(0.25f, 0.25f, 0.50f, 1.0f);  
}  

void handleKeyPress(GLFWwindow* window, int key, int scancode, int action, int mods) {
    if (action == GLFW_PRESS || action == GLFW_REPEAT) {
        switch (key) {
            case GLFW_KEY_UP:
                xRot -= 5.0f;
                break;
            case GLFW_KEY_DOWN:
                xRot += 5.0f;
                break;
            case GLFW_KEY_LEFT:
                yRot -= 5.0f;
                break;
            case GLFW_KEY_RIGHT:
                yRot += 5.0f;
                break;
            case GLFW_KEY_R:
                braco_direito = braco_esquerdo = coxa_direita = coxa_esquerda = joelho_direito = joelho_esquerdo = cotovelo = antebraco = 0;
                cont = cont2 = 0;
                break;
            case GLFW_KEY_W:
                if (cont >= 0 && cont < 17) {
                    coxa_direita = (coxa_direita - 5) % 90;
                    joelho_direito = (joelho_direito + 5) % 90;
                    braco_direito = (braco_direito + 5) % 90;
                    braco_esquerdo = (braco_esquerdo - 5) % 90;
                    cont++;
                } else if (cont >= 17 && cont < 33) {
                    coxa_direita = (coxa_direita + 5) % 90;
                    joelho_direito = (joelho_direito - 5) % 90;
                    braco_direito = (braco_direito - 5) % 90;
                    braco_esquerdo = (braco_esquerdo + 5) % 90;
                    cont++;
                } else if (cont >= 33 && cont < 49) {
                    coxa_esquerda = (coxa_esquerda - 5) % 90;
                    joelho_esquerdo = (joelho_esquerdo + 5) % 90;
                    braco_direito = (braco_direito - 5) % 90;
                    braco_esquerdo = (braco_esquerdo + 5) % 90;
                    cont++;
                } else if (cont >= 49 && cont < 65) {
                    coxa_esquerda = (coxa_esquerda + 5) % 90;
                    joelho_esquerdo = (joelho_esquerdo - 5) % 90;
                    braco_direito = (braco_direito + 5) % 90;
                    braco_esquerdo = (braco_esquerdo - 5) % 90;
                    cont++;
                } else {
                    braco_direito = braco_esquerdo = coxa_direita = coxa_esquerda = joelho_direito = joelho_esquerdo = cotovelo = antebraco = 0;
                    cont = 0;
                }
                break;
            case GLFW_KEY_S:
                if (cotovelo < 90) cotovelo = (cotovelo + 5) % 180;
                break;
            case GLFW_KEY_D:
                if (cotovelo > 0) cotovelo = (cotovelo - 5) % 180;
                break;
            case GLFW_KEY_E:
                if (cont2 >= 0 && cont2 < 19) {
                    cotovelo = (cotovelo + 5) % 180;
                    cont2++;
                } else if (cont2 >= 19 && cont2 < 100) {
                    antebraco = (antebraco + 5) % 130;
                    cont2++;
                } else if (cont2 >= 100 && cont2 < 150) {
                    antebraco = (antebraco - 5) % 130;
                    cont2++;
                } else {
                    cont2 = 19;
                }
                break;
            default:
                break;
        }
    }
}

void drawCube(float width, float height, float thickness) {
    float halfWidth = width / 2.0f;
    float halfHeight = height / 2.0f;
    float halfThickness = thickness / 2.0f;
    glBegin(GL_QUADS);
    // Frente
    glVertex3f(-halfWidth, -halfHeight,  halfThickness);
    glVertex3f( halfWidth, -halfHeight,  halfThickness);
    glVertex3f( halfWidth,  halfHeight,  halfThickness);
    glVertex3f(-halfWidth,  halfHeight,  halfThickness);
    // Tras
    glVertex3f(-halfWidth, -halfHeight, -halfThickness);
    glVertex3f(-halfWidth,  halfHeight, -halfThickness);
    glVertex3f( halfWidth,  halfHeight, -halfThickness);
    glVertex3f( halfWidth, -halfHeight, -halfThickness);
    // Esquerda
    glVertex3f(-halfWidth, -halfHeight, -halfThickness);
    glVertex3f(-halfWidth, -halfHeight,  halfThickness);
    glVertex3f(-halfWidth,  halfHeight,  halfThickness);
    glVertex3f(-halfWidth,  halfHeight, -halfThickness);
    // Direita
    glVertex3f( halfWidth, -halfHeight, -halfThickness);
    glVertex3f( halfWidth,  halfHeight, -halfThickness);
    glVertex3f( halfWidth,  halfHeight,  halfThickness);
    glVertex3f( halfWidth, -halfHeight,  halfThickness);
    // Cima
    glVertex3f(-halfWidth,  halfHeight, -halfThickness);
    glVertex3f(-halfWidth,  halfHeight,  halfThickness);
    glVertex3f( halfWidth,  halfHeight,  halfThickness);
    glVertex3f( halfWidth,  halfHeight, -halfThickness);
    // Baixo
    glVertex3f(-halfWidth, -halfHeight, -halfThickness);
    glVertex3f( halfWidth, -halfHeight, -halfThickness);
    glVertex3f( halfWidth, -halfHeight,  halfThickness);
    glVertex3f(-halfWidth, -halfHeight,  halfThickness);
    glEnd();
}

void drawGLScene(GLFWwindow* window){
    GLUquadricObj *pObj;    

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
    glPushMatrix();
    glTranslatef(0.0f, -1.0f, -5.0f); 
	glRotatef(yRot, 0.0f, 1.0f, 0.0f);  

	pObj = gluNewQuadric();  
	gluQuadricNormals(pObj, GLU_SMOOTH); 

    // Chao
    glColor3f(0.0f, 1.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.0f, -0.3f, 0.0f);
        glRotatef(90.0f, -0.3f, 0.0f, 0.0f);
        gluDisk(pObj, 0.0f, 5.0f, 26, 13);
    glPopMatrix();

    // Coxa cima direita
    glColor3f(0.67f, 0.84f, 0.9f);
    glPushMatrix();
        glTranslatef(-0.2f, 0.82f, -0.08f);
        glRotatef((GLfloat) coxa_direita, 1.0f, 0.0f, 0.0f);
        gluSphere(pObj, 0.12f, 26, 13);
        // Coxa direita
        glPushMatrix();
            glTranslatef(0.0f, -0.22f, 0.0f);
            drawCube(0.18f, 0.4f, 0.15f);
            // Joelho direito
            glColor3f(1.0f, 0.86f, 0.69f); 
            glPushMatrix();
                glTranslatef(0.0f, -0.22f, 0.0f);
                glRotatef((GLfloat) joelho_direito, 1.0f, 0.0f, 0.0f);
                gluSphere(pObj, 0.09f, 26, 13);
                // Canela direita
                glPushMatrix();
                    glTranslatef(0.0f, -0.24f, 0.0f);
                    drawCube(0.12f, 0.4f, 0.1f);
                    // Pe direito
                    glColor3f(1.0f, 0.86f, 0.69f); 
                    glPushMatrix();
                        glTranslatef(0.0f, -0.2f, 0.08f);
                        drawCube(0.15f, 0.08f, 0.3f);
                    glPopMatrix();
                glPopMatrix();
            glPopMatrix();
        glPopMatrix();
    glPopMatrix(); 

    // Coxa cima esquerda
    glColor3f(0.67f, 0.84f, 0.9f); // Azul claro
    glPushMatrix();
        glTranslatef(0.2f, 0.82f, -0.08f);
        glRotatef((GLfloat) coxa_esquerda, 1.0f, 0.0f, 0.0f);
        gluSphere(pObj, 0.12f, 26, 13);
        // Coxa esquerda
        glPushMatrix();
            glTranslatef(0.0f, -0.22f, 0.0f);
            drawCube(0.18f, 0.4f, 0.15f);
            // Joelho esquerdo
            glColor3f(1.0f, 0.86f, 0.69f); // Tom de pele
            glPushMatrix();
                glTranslatef(0.0f, -0.22f, 0.0f);
                glRotatef((GLfloat) joelho_esquerdo, 1.0f, 0.0f, 0.0f);
                gluSphere(pObj, 0.09f, 26, 13);
                // Canela esquerda
                glPushMatrix();
                    glTranslatef(0.0f, -0.24f, 0.0f);
                    drawCube(0.12f, 0.4f, 0.1f);
                // Pe esquerdo
                    glPushMatrix();
                        glTranslatef(0.0f, -0.2f, 0.08f);
                        drawCube(0.15f, 0.08f, 0.3f);
                    glPopMatrix();
                glPopMatrix();
            glPopMatrix();
        glPopMatrix();
    glPopMatrix();

    // Sunga
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.0f, 0.85f, -0.08f);
        drawCube(0.15f, 0.1f, 0.2f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.0f, 0.93f, -0.08f);
        drawCube(0.55f, 0.1f, 0.2f);
    glPopMatrix();
    // Tronco
    glColor3f(1.0f, 0.86f, 0.69f); 
    glPushMatrix();
        glTranslatef(0.0f, 1.35f, -0.08f);
        drawCube(0.6f, 0.8f, 0.35f);
    glPopMatrix();
    // Camisa 
    glColor3f(1.0f, 0.0f, 0.0f); 
    glPushMatrix();
        glTranslatef(0.2f, 1.38f, -0.08f);
        drawCube(0.23f, 0.75f, 0.39f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.2f, 1.38f, -0.08f);
        drawCube(0.23f, 0.75f, 0.39f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.0f, 1.38f, -0.17f);
        drawCube(0.23f, 0.75f, 0.3f);
    glPopMatrix();

    // Ombro direito
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.5f, 1.55f, -0.08f);
        glRotatef((GLfloat) braco_direito, 1.0f, 0.0f, 0.0f);
        gluSphere(pObj, 0.24f, 26, 13);
        // Detalhe ombro direito
        glColor3f(0.9f, 0.9f, 0.9f); // Metal
        glPushMatrix();
            glTranslatef(0.19f, 0.0f, 0.0f);
            glRotatef(-90.0f, 0.0f, 0.5f, 0.0f);
            gluCylinder(pObj, 0.18f, 0.18f, 0.2f, 26, 13);
            // Braco direito
            glColor3f(1.0f, 0.86f, 0.69f);
            glPushMatrix();
                glTranslatef(0.0f, -0.3f, 0.19f);
                drawCube(0.15f, 0.3f, 0.15f);
                // Cotovelo direito
                glColor3f(0.0f, 1.0f, 1.0f); // Ciano
                glPushMatrix();
                    glTranslatef(0.0f, -0.16f, 0.0f);
                    glRotatef((GLfloat) cotovelo, 0.0f, 0.0f, 1.0f);
                    gluSphere(pObj, 0.12f, 26, 13);
                    // Antebraco direito
                    glPushMatrix();
                        glTranslatef(0.0f, -0.2f, 0.0f);
                        glRotatef((GLfloat) antebraco, 0.0f, 1.0f, 0.0f);
                        drawCube(0.32f, 0.4f, 0.3f);
                        // Mao direito
                        glColor3f(1.0f, 0.0f, 0.0f);
                        glPushMatrix();
                            glTranslatef(0.0f, -0.2f, 0.0f);
                            drawCube(0.12f, 0.1f, 0.15f);
                        glPopMatrix();
                    glPopMatrix();
                glPopMatrix();
            glPopMatrix();
        glPopMatrix();
    glPopMatrix();

    // Ombro esquerdo
    glPushMatrix();
        glTranslatef(0.5f, 1.55f, -0.08f);
        glRotatef((GLfloat) braco_esquerdo, 1.0f, 0.0f, 0.0f);
        gluSphere(pObj, 0.24f, 26, 13);
        // Detalhe ombro esquerdo
        glColor3f(0.9f, 0.9f, 0.9f); 
        glPushMatrix();
            glTranslatef(-0.19f, 0.0f, 0.0f);
            glRotatef(90.0f, 0.0f, 0.5f, 0.0f);
            gluCylinder(pObj, 0.18f, 0.18f, 0.2f, 26, 13);
            // Braco esquerdo
            glColor3f(1.0f, 0.86f, 0.69f);
            glPushMatrix();
                glTranslatef(0.0f, -0.3f, 0.19f);
                drawCube(0.15f, 0.3f, 0.15f);
                // Cotovelo esquerdo
                glColor3f(0.0f, 1.0f, 1.0f); 
                glPushMatrix();
                    glTranslatef(0.0f, -0.16f, 0.0f);
                    glRotatef((GLfloat) cotovelo, 0.0f, 0.0f, -1.0f);
                    gluSphere(pObj, 0.12f, 26, 13);
                    // Antebraco esquerdo
                    glPushMatrix();
                        glTranslatef(0.0f, -0.2f, 0.0f);
                        glRotatef((GLfloat) antebraco, 0.0f, -1.0f, 0.0f);
                        drawCube(0.32f, 0.4f, 0.3f);
                        // Mao esquerdo
                        glColor3f(1.0f, 0.0f, 0.0f);
                        glPushMatrix();
                            glTranslatef(0.0f, -0.2f, 0.0f);
                            drawCube(0.12f, 0.1f, 0.15f);
                        glPopMatrix();
                    glPopMatrix();
                glPopMatrix();
            glPopMatrix();
        glPopMatrix();
    glPopMatrix();

    // Cabeca
    glColor3f(1.0f, 0.86f, 0.69f);
    glPushMatrix();
        glTranslatef(0.0f, 1.85f, -0.08f);
        gluSphere(pObj, 0.16f, 26, 13);
    glPopMatrix();
    // Olhos
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.06f, 1.85f, 0.05f);
        gluSphere(pObj, 0.02f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.06f, 1.85f, 0.05f);
        gluSphere(pObj, 0.02f, 26, 13);
    glPopMatrix();

    glPopMatrix();
}
int main(){
    glfwInit();
    GLFWwindow* window = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Robo", NULL, NULL);
    if (window == NULL){
        printf("Failed to open GLFW window\n");
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(window);
    glfwSetKeyCallback(window, handleKeyPress);
    glfwSetFramebufferSizeCallback(window, resizeWindow);
    glfwSwapInterval(1);  
    glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);
    resizeWindow(window, SCREEN_WIDTH, SCREEN_HEIGHT);
    setupRC();

    while (!glfwWindowShouldClose(window)){
        drawGLScene(window);
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwTerminate();
return 0;
}