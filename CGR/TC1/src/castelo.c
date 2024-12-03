// gcc castelo.c -lglfw -lGL -lGLU -lm -o castelo && ./castelo

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

void drawWall(float width, float height, float thickness) {
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
    glColor3f(0.0f, 1.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.0f, 0.0f, 0.0f);
        glRotatef(90.0f, -0.3f, 0.0f, 0.0f);
        gluDisk(pObj, 0.0f, 5.0f, 26, 13);
    glPopMatrix();
    // Torre 1
    glColor3f(0.8f, 0.8f, 0.8f); // Cinza
    glPushMatrix();
        glTranslatef(0.8f, 0.0f, 0.0f);
        glRotatef(-90.0f, 0.8f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.45f, 26, 13);
    glPopMatrix();
    glPushMatrix(); // 2 parte torre
        glTranslatef(0.8f, 0.45f, 0.0f);
        glRotatef(-90.0f, 0.8f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.15f, 0.15f, 0.15f, 26, 13);
    glPopMatrix();
    glColor3f(0.5f, 0.35f, 0.05f); // Marrom
    glPushMatrix(); // Teto
        glTranslatef(0.8f, 0.6f, 0.0f);
        glRotatef(-90.0f, 0.8f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.18f, 0.0f, 0.24f, 26, 13);
    glPopMatrix();
    glPushMatrix(); // Poste teto
        glTranslatef(0.8f, 0.8f, 0.0f);
        glRotatef(-90.0f, 0.8f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.006f, 0.006f, 0.2f, 26, 13);
    glPopMatrix();
    // Torre 2
    glColor3f(0.8f, 0.8f, 0.8f);
    glPushMatrix();
        glTranslatef(-0.8f, 0.0f, 0.0f);
        glRotatef(-90.0f, 0.01f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.45f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.8f, 0.45f, 0.0f);
        glRotatef(-90.0f, 0.01f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.15f, 0.15f, 0.15f, 26, 13);
    glPopMatrix();
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix();
        glTranslatef(-0.8f, 0.6f, 0.0f);
        glRotatef(-90.0f, 0.01f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.18f, 0.0f, 0.24f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.8f, 0.8f, 0.0f);
        glRotatef(-90.0f, 0.01f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.006f, 0.006f, 0.2f, 26, 13);
    glPopMatrix();
    // Torre 3
    glColor3f(0.8f, 0.8f, 0.8f);
    glPushMatrix();
        glTranslatef(0.01f, 0.0f, 0.8f);
        glRotatef(-90.0f, 0.01f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.45f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.01f, 0.45f, 0.8f);
        glRotatef(-90.0f, 0.01f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.15f, 0.15f, 0.15f, 26, 13);
    glPopMatrix();
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix();
        glTranslatef(0.01f, 0.6f, 0.8f);
        glRotatef(-90.0f, 0.01f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.18f, 0.0f, 0.24f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.01f, 0.8f, 0.8f);
        glRotatef(-90.0f, 0.01f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.006f, 0.006f, 0.2f, 26, 13);
    glPopMatrix();
    // Torre 4
    glColor3f(0.8f, 0.8f, 0.8f);
    glPushMatrix();
        glTranslatef(0.08f, 0.0f, -0.8f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.1f, 0.1f, 0.45f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.08f, 0.45f, -0.8f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.15f, 0.15f, 0.15f, 26, 13);
    glPopMatrix();
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix();
        glTranslatef(0.08f, 0.6f, -0.8f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.18f, 0.0f, 0.24f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.08f, 0.8f, -0.8f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.006f, 0.006f, 0.2f, 26, 13);
    glPopMatrix();
    // Muro
    glColor3f(0.8f, 0.8f, 0.8f); 
    glPushMatrix();
        glTranslatef(0.4f, 0.18f, 0.4f);
        glRotatef(-135.0f, 0.0f, 1.0f, 0.0f);
        drawWall(1.0f, 0.36f, 0.1f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.4f, 0.18f, 0.4f);
        glRotatef(-225.0f, 0.0f, 1.0f, 0.0f);
        drawWall(1.0f, 0.36f, 0.1f);
    glPopMatrix();
    glPushMatrix(); 
        glTranslatef(-0.4f, 0.18f, -0.4f);
        glRotatef(-317.0f, 0.0f, 1.0f, 0.0f);
        drawWall(1.2f, 0.36f, 0.1f);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(0.4f, 0.18f, -0.4f);
        glRotatef(-225.0f, 0.0f, 1.0f, 0.0f);
        drawWall(1.0f, 0.36f, 0.1f);
    glPopMatrix();
    // Muralha portao
    glColor3f(0.95f, 0.95f, 0.95f); 
    glPushMatrix();
        glTranslatef(-0.4f, 0.2f, 0.4f);
        glRotatef(-225.0f, 0.0f, 1.0f, 0.0f);
        drawWall(0.50f, 0.4f, 0.2f);
    glPopMatrix();
    // Teto portao
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix();
        glTranslatef(-0.4f, 0.5f, 0.4f);
        glRotatef(-225.0f, 0.0f, 1.0f, 0.0f);
        drawWall(0.55f, 0.05f, 0.25f);
    glPopMatrix();
    // Pilar portao
    glPushMatrix();
        glTranslatef(-0.3f, 0.4f, 0.6f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.009f, 0.009f, 0.1f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.25f, 0.4f, 0.55f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.009f, 0.009f, 0.1f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.6f, 0.4f, 0.35f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.009f, 0.009f, 0.1f, 26, 13);
    glPopMatrix();
    glPushMatrix();
        glTranslatef(-0.58f, 0.4f, 0.3f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.009f, 0.009f, 0.1f, 26, 13);
    glPopMatrix();
    // Portao
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix();
        glTranslatef(-0.4f, 0.1f, 0.4f);
        glRotatef(-225.0f, 0.0f, 1.0f, 0.0f);
        drawWall(0.25f, 0.2f, 0.21f);
    glPopMatrix();
    // Arvore
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix(); // Tronco
        glTranslatef(1.5f, 0.0f, 0.0f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.019f, 0.019f, 0.15f, 26, 13);
    glPopMatrix();
    glColor3f(0.6f, 1.0f, 0.0f);
    glPushMatrix(); //Folha
        glTranslatef(1.5f, 0.1f, 0.0f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.05f, 0.0f, 0.25f, 26, 13);
    glPopMatrix();
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix(); 
        glTranslatef(1.8f, 0.0f, 0.0f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.019f, 0.019f, 0.15f, 26, 13);
    glPopMatrix();
    glColor3f(0.6f, 1.0f, 0.0f);
    glPushMatrix(); 
        glTranslatef(1.8f, 0.1f, 0.0f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.05f, 0.0f, 0.25f, 26, 13);
    glPopMatrix();
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix(); 
        glTranslatef(1.5f, 0.0f, -0.2f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.019f, 0.019f, 0.15f, 26, 13);
    glPopMatrix();
    glColor3f(0.6f, 1.0f, 0.0f);
    glPushMatrix(); 
        glTranslatef(1.5f, 0.1f, -0.2f);
        glRotatef(-90.0f, 0.08f, 0.0f, 0.0f);
        gluCylinder(pObj, 0.05f, 0.0f, 0.25f, 26, 13);
    glPopMatrix();
    // Agua
    glColor3f(0.0f, 0.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.0f, 0.01f, 0.0f);
        glRotatef(90.0f, -0.3f, 0.0f, 0.0f);
        gluDisk(pObj, 1.0f, 1.2f, 26, 13);
    glPopMatrix();
    // Ponte
    glColor3f(0.5f, 0.35f, 0.05f);
    glPushMatrix();
        glTranslatef(-0.4f, 0.01f, 0.4f);
        glRotatef(-225.0f, 0.0f, 1.0f, 0.0f);
        drawWall(0.28f, 0.01f, 1.4f);
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