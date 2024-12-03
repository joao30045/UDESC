// gcc robo.c -lglfw -lGL -lGLU -lm -o robo && ./robo

#define GLFW_INCLUDE_NONE

#include <stdio.h>
#include <stdlib.h>
#include <GLFW/glfw3.h>   
#include <GL/gl.h>       
#include <GL/glu.h>      

void processInput(GLFWwindow *window);

#define SCREEN_WIDTH  800
#define SCREEN_HEIGHT 600

static GLfloat yRot = 0.0f;

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

void handleKeyPress(GLFWwindow *window){
    if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
        glfwTerminate();
        exit(EXIT_SUCCESS);
    }

    if(glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS) 
        yRot -= 5.0f;  
    if(glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS)  
        yRot += 5.0f;  
                  
    yRot = (GLfloat)((const int)yRot % 360);  
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
    GLUquadricObj *pObj;    // Quadric Object  

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
    glPushMatrix();
	glTranslatef(0.0f, -1.0f, -5.0f);  
	glRotatef(yRot, 0.0f, 1.0f, 0.0f);  
	pObj = gluNewQuadric();  
	gluQuadricNormals(pObj, GLU_SMOOTH); 

    // Chao
    glColor3f(0.0f, 1.0f, 0.0f); //Amarelo
    glPushMatrix();
        glTranslatef(0.0f, -0.3f, 0.0f);
        glRotatef(90.0f, -0.3f, 0.0f, 0.0f);
        gluDisk(pObj, 0.0f, 5.0f, 26, 13);
    glPopMatrix();
    // Pe esquerdo
    glColor3f(1.0f, 0.86f, 0.69f); //Tom de pele
    glPushMatrix();
        glTranslatef(-0.2f, 0.0f, 0.0f);
        drawCube(0.15f, 0.08f, 0.3f);
    glPopMatrix();
    // Pe direito
    glPushMatrix();
        glTranslatef(0.2f, 0.0f, 0.0f);
        drawCube(0.15f, 0.08f, 0.3f);
    glPopMatrix();
    // Canela esquerda
    glPushMatrix();
        glTranslatef(-0.2f, 0.18f, -0.08f);
        drawCube(0.12f, 0.4f, 0.1f);
    glPopMatrix();
    // Canela direita
    glPushMatrix();
        glTranslatef(0.2f, 0.18f, -0.08f);
        drawCube(0.12f, 0.4f, 0.1f);
    glPopMatrix();
    // Joelho esquerdo
    glColor3f(0.67f, 0.84f, 0.9f); //Azul claro
    glPushMatrix();
        glTranslatef(-0.2f, 0.38f, -0.08f);
        gluSphere(pObj, 0.09f, 26, 13);
    glPopMatrix();
    // Joelho direito
    glPushMatrix();
        glTranslatef(0.2f, 0.38f, -0.08f);
        gluSphere(pObj, 0.09f, 26, 13);
    glPopMatrix();
    // Coxa esquerda
    glPushMatrix();
        glTranslatef(-0.2f, 0.58f, -0.08f);
        drawCube(0.18f, 0.4f, 0.15f);
    glPopMatrix();
    // Coxa direita
    glPushMatrix();
        glTranslatef(0.2f, 0.58f, -0.08f);
        drawCube(0.18f, 0.4f, 0.15f);
    glPopMatrix();
    // Coxa cima esquerda
    glPushMatrix();
        glTranslatef(-0.2f, 0.82f, -0.08f);
        gluSphere(pObj, 0.12f, 26, 13);
    glPopMatrix();
    // Coxa cima direita
    glPushMatrix();
        glTranslatef(0.2f, 0.82f, -0.08f);
        gluSphere(pObj, 0.12f, 26, 13);
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
    // Ombro esquerdo
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.5f, 1.55f, -0.08f);
        gluSphere(pObj, 0.24f, 26, 13);
    glPopMatrix();
    // Ombro direito
    glPushMatrix();
        glTranslatef(0.5f, 1.55f, -0.08f);
        gluSphere(pObj, 0.24f, 26, 13);
    glPopMatrix();
    // Detalhe ombro esquerdo
    glColor3f(0.9f, 0.9f, 0.9f);
    glPushMatrix();
        glTranslatef(-0.3f, 1.55f, -0.08f);
        glRotatef(-90.0f, 0.0f, 0.5f, 0.0f);
        gluCylinder(pObj, 0.18f, 0.18f, 0.2f, 26, 13);
    glPopMatrix();
    // Detalhe ombro direito
    glColor3f(0.9f, 0.9f, 0.9f);
    glPushMatrix();
        glTranslatef(0.3f, 1.55f, -0.08f);
        glRotatef(90.0f, 0.0f, 0.5f, 0.0f);
        gluCylinder(pObj, 0.18f, 0.18f, 0.2f, 26, 13);
    glPopMatrix();
    // Braco esquerdo
    glColor3f(1.0f, 0.86f, 0.69f);
    glPushMatrix();
        glTranslatef(-0.5f, 1.2f, -0.08f);
        drawCube(0.15f, 0.3f, 0.15f);
    glPopMatrix();
    // Braco direto
    glPushMatrix();
        glTranslatef(0.5f, 1.2f, -0.08f);
        drawCube(0.15f, 0.3f, 0.15f);
    glPopMatrix();
    // Antebraco esquerdo
    glColor3f(0.0f, 1.0f, 1.0f); //Ciano
    glPushMatrix();
        glTranslatef(-0.5f, 1.1f, -0.08f);
        gluSphere(pObj, 0.12f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.5f, 0.9f, -0.08f);
        drawCube(0.32f, 0.4f, 0.3f);
    glPopMatrix();
    // Antebraco direito
    glPushMatrix();
        glTranslatef(0.5f, 1.1f, -0.08f);
        gluSphere(pObj, 0.12f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.5f, 0.9f, -0.08f);
        drawCube(0.32f, 0.4f, 0.3f);
    glPopMatrix();
    // Mao esquerda
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.5f, 0.7f, -0.08f);
        drawCube(0.12f, 0.1f, 0.15f);
    glPopMatrix();
    // Mao direita
    glPushMatrix();
        glTranslatef(0.5f, 0.7f, -0.08f);
        drawCube(0.12f, 0.1f, 0.15f);
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
    GLFWwindow* window = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Fireworks", NULL, NULL);
    if (window == NULL){
        printf("Failed to open GLFW window\n");
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(window);
    glfwSetFramebufferSizeCallback(window, resizeWindow);
    glfwSwapInterval(1);  
    glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);
    resizeWindow(window, SCREEN_WIDTH, SCREEN_HEIGHT);
    setupRC();
    while (!glfwWindowShouldClose(window)){
        handleKeyPress(window);
        drawGLScene(window);
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwTerminate();
return 0;
}