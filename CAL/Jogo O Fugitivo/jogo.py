import pygame, sys
from pygame.locals import *
import random
import time
 
pygame.init()
 
FPS = 60
FramePerSec = pygame.time.Clock()
 
# Cores
BLUE  = (0, 0, 255)
RED   = (255, 0, 0)
GREEN = (0, 255, 0)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
 
# Screen information
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600
 
DISPLAYSURF = pygame.display.set_mode((SCREEN_WIDTH,SCREEN_HEIGHT))
DISPLAYSURF.fill(WHITE)
pygame.display.set_caption("O Fugitivo")

class Enemy(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__() 
        self.image = pygame.image.load("PlayerRed.png")
        self.rect = self.image.get_rect()
        self.rect.center = (100, 100)
        self.speed = 3  
    
    def setPos(self, x, y):
        self.rect.center = (x, y)
        
    def getRect(self):
        return self.rect  
    
    def move(self, player_rect):
        diff_x = player_rect.centerx - self.rect.centerx
        diff_y = player_rect.centery - self.rect.centery

        distance = (diff_x ** 2 + diff_y ** 2) ** 0.5
        if distance != 0:  
            move_x = (diff_x / distance) * self.speed
            move_y = (diff_y / distance) * self.speed
        else:
            move_x, move_y = 0, 0

        self.rect.move_ip(move_x, move_y)
    
    def draw(self, surface):
        surface.blit(self.image, self.rect)
			
class Player(pygame.sprite.Sprite):
	def __init__(self):
		super().__init__() 
		self.image = pygame.image.load("PlayerBlue.png")
		self.rect = self.image.get_rect()
		self.rect.center = (160, 520)
		
	def setPos(self, x, y):
		self.rect.center = (x, y)
	
	def getRect(self):
		return self.rect
 
	def update(self):
		pressed_keys = pygame.key.get_pressed()
		if pressed_keys[K_UP]:
			self.rect.move_ip(0, -5)
		if pressed_keys[K_DOWN]:
			self.rect.move_ip(0,5)
		 
		if self.rect.left > 0:
			  if pressed_keys[K_LEFT]:
				  self.rect.move_ip(-5, 0)
				  
		if self.rect.right < SCREEN_WIDTH:		
			  if pressed_keys[K_RIGHT]:
				  self.rect.move_ip(5, 0)
 
	def draw(self, surface):
		surface.blit(self.image, self.rect)	 
  
P1 = Player()
E1 = Enemy()

enemies = []
N_ENEMIES = 3
for i in range(N_ENEMIES):
	enemies.append(Enemy())

while True:     
    for event in pygame.event.get():              
        if event.type == QUIT:
            pygame.quit()
            sys.exit()
    
    P1.update()
    
    for i in range(N_ENEMIES):
        enemies[i].move(P1.getRect())  
    
    DISPLAYSURF.fill(WHITE)
    P1.draw(DISPLAYSURF)
    
    for i in range(N_ENEMIES):
        enemies[i].draw(DISPLAYSURF)
        
    l = P1.getRect().collidelistall([enemy.getRect() for enemy in enemies])
    if len(l) > 0:
        time.sleep(2)
        P1.setPos(int(SCREEN_WIDTH / 2), int(SCREEN_HEIGHT / 2))
        for i in range(N_ENEMIES):
            enemies[i].setPos(100 + 50 * i, 100 + 50 * i)
    
    pygame.display.update()
    FramePerSec.tick(FPS)
