// gcc snow.c -lglfw -lGL -lGLU -lm -o snow && ./snow
// gcc snow.c -lglfw3dll -lopengl32 -lglu32 -o snow && ./snow

#define GLFW_INCLUDE_NONE

#include <stdio.h>
#include <stdlib.h>
#include <GLFW/glfw3.h>   
#include <GL/gl.h>       
#include <GL/glu.h> 
#include <unistd.h>
#include <stdbool.h>

#define num 5000
#define SCREEN_WIDTH  800
#define SCREEN_HEIGHT 600

typedef struct create_snow {
    double life;        
    double pos_x;     
    double pos_y;     
    double pos_z;      
    double vel;        
    double gravity;     
    double radius;     
    bool alive;        
} Snow;

Snow snow[num];

void initFlakes(int part) {
    snow[part].life = (double) (10 + rand() % 4) / 5;
    snow[part].pos_x = (double) ((rand() % 120) - 60) / 10;
    snow[part].pos_y = 0.5;
    snow[part].pos_z = (double) ((rand() % 150) - 100) / 10;
    snow[part].vel = -2;
    snow[part].gravity = -0.5;
    snow[part].radius = 0.01;
    snow[part].alive = true;
}

void initSnow() {
    int cont = 0;
    glColor3f(1.0, 1.0, 1.0);
    for (int i = 0; i < num; i++) {
        glPushMatrix();
        glTranslatef(snow[i].pos_x, snow[i].pos_y, snow[i].pos_z);

        GLUquadric* quad = gluNewQuadric();  
        gluSphere(quad, snow[i].radius, 13, 13); 
        gluDeleteQuadric(quad);  
        
        glPopMatrix();

        if (snow[i].alive) {
            cont++;
            double fall = -5000 + rand() % 2000;
            if (cont % 15 == 0) {
                snow[i].pos_x += -snow[i].vel / fall;
            }
            snow[i].pos_x += snow[i].vel / fall;
            snow[i].vel += snow[i].gravity;
            snow[i].pos_y += snow[i].vel / 1000;
            snow[i].life -= 0.01;
        } else {
            if (rand() % 100 < 2) {
                snow[i].alive = true;
            }
        }
        if (snow[i].life < 0.0) {
            initFlakes(i);
        }
    }
}

void display() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();
    glTranslatef(0.0f, 0.0f, 0.0f);
    initSnow();
}

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

void handleKeyPress(GLFWwindow *window){
    if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
        glfwTerminate();
        exit(EXIT_SUCCESS);
    }
}

int main() {
    glfwInit();
    GLFWwindow* window = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Snow", NULL, NULL);
    if (window == NULL)
    {
        printf("Failed to open GLFW window\n");
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(window);
    glfwSetFramebufferSizeCallback(window, resizeWindow);
    glfwSwapInterval(1);
    glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);
    resizeWindow(window, SCREEN_WIDTH, SCREEN_HEIGHT);

    while (!glfwWindowShouldClose(window)){
        handleKeyPress(window);
        display();
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwTerminate();
    return 0;
}
