import cv2
import numpy as np

def calcularMediaPixels(image):
    soma = float(0) 
    altura, largura = image.shape
    totalPixels = altura * largura

    for i in range(altura):
        for j in range(largura):
            soma += float(image[i, j])  
    
    return soma / totalPixels  

image = cv2.imread('figuraCinza.jpg', cv2.IMREAD_GRAYSCALE)
cv2.imshow('Image', image)

mediaNumpy = np.mean(image)
mediaManual = calcularMediaPixels(image)

print(f'Média NumPy: {mediaNumpy}')
print(f'Média calculada: {mediaManual}')

limiarEscuro = 55
limiarClaro = 205

if mediaManual < limiarEscuro:
    print("Imagem muito escura.")
elif mediaManual > limiarClaro: 
    print("Imagem muito clara.")
else: 
    print("Imagem cinzenta.")

cv2.waitKey(0)