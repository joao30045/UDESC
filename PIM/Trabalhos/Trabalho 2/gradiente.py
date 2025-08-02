import cv2
import numpy as np
import math
import os
from skimage.metrics import structural_similarity as ssim


def calcula_magnitude_direcao(gx, gy):
    epsilon = 1e-8
    magnitude = np.sqrt(gx**2 + gy**2)
    direcao = np.arctan2(gy, gx + epsilon) * 180 / np.pi
    direcao[direcao < 0] += 180 
    return magnitude, direcao

if not os.path.exists("resultados"):
    os.makedirs("resultados")

imagemLua = cv2.imread("Lua1_gray.jpg", cv2.IMREAD_GRAYSCALE)
imagemXadrez = cv2.imread("chessboard_inv.png", cv2.IMREAD_GRAYSCALE)
imagemPrato = cv2.imread("img02.jpg", cv2.IMREAD_GRAYSCALE)
imagemMoedas = cv2.imread("moedas.png", cv2.IMREAD_GRAYSCALE)

imagem_array_Lua = np.array(imagemLua)
imagem_array_Xadrez = np.array(imagemXadrez)
imagem_array_Prato = np.array(imagemPrato)
imagem_array_Moedas = np.array(imagemMoedas)

tamanho_janela = 3
offset = tamanho_janela // 2  

def filtro_mediana(imagem_array):
    altura, largura = imagem_array.shape
    tamanho_janela = 3
    imagem_filtrada_mediana = np.zeros_like(imagem_array)
    imagem_padded = np.pad(imagem_array, pad_width=offset, mode='constant', constant_values=0)
    for i in range(altura):
        for j in range(largura):
            janela = imagem_padded[i:i + tamanho_janela, j:j + tamanho_janela]
            janela_flat = janela.flatten()
            imagem_filtrada_mediana[i, j] = np.median(janela_flat)
    
    imagem_filtrada_mediana = (imagem_filtrada_mediana - np.min(imagem_filtrada_mediana)) / (np.max(imagem_filtrada_mediana) - np.min(imagem_filtrada_mediana)) * 255    
    return imagem_filtrada_mediana

imagem_filtrada_mediana_Lua = filtro_mediana(imagem_array_Lua)
imagem_filtrada_mediana_Xadrez = filtro_mediana(imagem_array_Xadrez)
imagem_filtrada_mediana_Prato = filtro_mediana(imagem_array_Prato)
imagem_filtrada_mediana_Moedas = filtro_mediana(imagem_array_Moedas)

cv2.imwrite("resultados/imagem_filtrada_mediana_Lua.jpg", imagem_filtrada_mediana_Lua)
cv2.imwrite("resultados/imagem_filtrada_mediana_Xadrez.jpg", imagem_filtrada_mediana_Xadrez)
cv2.imwrite("resultados/imagem_filtrada_mediana_Prato.jpg", imagem_filtrada_mediana_Prato)
cv2.imwrite("resultados/imagem_filtrada_mediana_Moedas.jpg", imagem_filtrada_mediana_Moedas)

imagem_padded_mediana_Lua = np.pad(imagem_filtrada_mediana_Lua, pad_width=offset, mode='constant', constant_values=0)
imagem_padded_mediana_Xadrez = np.pad(imagem_filtrada_mediana_Xadrez, pad_width=offset, mode='constant', constant_values=0)
imagem_padded_mediana_Prato = np.pad(imagem_filtrada_mediana_Prato, pad_width=offset, mode='constant', constant_values=0)
imagem_padded_mediana_Moedas = np.pad(imagem_filtrada_mediana_Moedas, pad_width=offset, mode='constant', constant_values=0)

sobel_x = np.array([[-1, 0, 1], [-2, 0, 2], [-1, 0, 1]])
sobel_y = np.array([[-1, -2, -1], [0, 0, 0], [1, 2, 1]])
prewitt_x = np.array([[-1, 0, 1], [-1, 0, 1], [-1, 0, 1]])
prewitt_y = np.array([[-1, -1, -1], [0, 0, 0], [1, 1, 1]])

sobel_xCV_Lua = cv2.Sobel(imagem_filtrada_mediana_Lua, cv2.CV_64F, 1, 0, ksize=3)
sobel_yCV_Lua = cv2.Sobel(imagem_filtrada_mediana_Lua, cv2.CV_64F, 0, 1, ksize=3)
sobel_xCV_Xadrez = cv2.Sobel(imagem_filtrada_mediana_Xadrez, cv2.CV_64F, 1, 0, ksize=3)
sobel_yCV_Xadrez = cv2.Sobel(imagem_filtrada_mediana_Xadrez, cv2.CV_64F, 1, 0, ksize=3)
sobel_xCV_Prato = cv2.Sobel(imagem_filtrada_mediana_Prato, cv2.CV_64F, 1, 0, ksize=3)
sobel_yCV_Prato = cv2.Sobel(imagem_filtrada_mediana_Prato, cv2.CV_64F, 1, 0, ksize=3)
sobel_xCV_Moedas = cv2.Sobel(imagem_filtrada_mediana_Moedas, cv2.CV_64F, 1, 0, ksize=3)
sobel_yCV_Moedas = cv2.Sobel(imagem_filtrada_mediana_Moedas, cv2.CV_64F, 1, 0, ksize=3)

prewitt_xCV_Lua = cv2.filter2D(imagem_filtrada_mediana_Lua, -1, prewitt_x)
prewitt_yCV_Lua = cv2.filter2D(imagem_filtrada_mediana_Lua, -1, prewitt_y)
prewitt_xCV_Xadrez = cv2.filter2D(imagem_filtrada_mediana_Xadrez, -1, prewitt_x)
prewitt_yCV_Xadrez = cv2.filter2D(imagem_filtrada_mediana_Xadrez, -1, prewitt_y)
prewitt_xCV_Prato = cv2.filter2D(imagem_filtrada_mediana_Prato, -1, prewitt_x)
prewitt_yCV_Prato = cv2.filter2D(imagem_filtrada_mediana_Prato, -1, prewitt_y)
prewitt_xCV_Moedas = cv2.filter2D(imagem_filtrada_mediana_Moedas, -1, prewitt_x)
prewitt_yCV_Moedas = cv2.filter2D(imagem_filtrada_mediana_Moedas, -1, prewitt_y)

def aplicar_convolucao(imagem_padded, mascara):
    altura, largura = imagem_padded.shape
    resultado = np.zeros((altura - 2 * offset, largura - 2 * offset))
    for i in range(offset, altura - offset):
        for j in range(offset, largura - offset):
            janela = imagem_padded[i - offset:i + offset + 1, j - offset:j + offset + 1]
            resultado[i - offset, j - offset] = np.sum(janela * mascara)
    return resultado

epsilon = 1e-8

gradienteX_Sobel_Lua = aplicar_convolucao(imagem_padded_mediana_Lua, sobel_x)
gradienteY_Sobel_Lua = aplicar_convolucao(imagem_padded_mediana_Lua, sobel_y)
gradienteX_Sobel_Xadrez = aplicar_convolucao(imagem_padded_mediana_Xadrez, sobel_x)
gradienteY_Sobel_Xadrez = aplicar_convolucao(imagem_padded_mediana_Xadrez, sobel_y)
gradienteX_Sobel_Prato = aplicar_convolucao(imagem_padded_mediana_Prato, sobel_x)
gradienteY_Sobel_Prato = aplicar_convolucao(imagem_padded_mediana_Prato, sobel_y)
gradienteX_Sobel_Moedas = aplicar_convolucao(imagem_padded_mediana_Moedas, sobel_x)
gradienteY_Sobel_Moedas = aplicar_convolucao(imagem_padded_mediana_Moedas, sobel_y)

gradienteX_Prewitt_Lua = aplicar_convolucao(imagem_padded_mediana_Lua, prewitt_x)
gradienteY_Prewitt_Lua = aplicar_convolucao(imagem_padded_mediana_Lua, prewitt_y)
gradienteX_Prewitt_Xadrez = aplicar_convolucao(imagem_padded_mediana_Xadrez, prewitt_x)
gradienteY_Prewitt_Xadrez = aplicar_convolucao(imagem_padded_mediana_Xadrez, prewitt_y)
gradienteX_Prewitt_Prato = aplicar_convolucao(imagem_padded_mediana_Prato, prewitt_x)
gradienteY_Prewitt_Prato = aplicar_convolucao(imagem_padded_mediana_Prato, prewitt_y)
gradienteX_Prewitt_Moedas = aplicar_convolucao(imagem_padded_mediana_Moedas, prewitt_x)
gradienteY_Prewitt_Moedas = aplicar_convolucao(imagem_padded_mediana_Moedas, prewitt_y)

magnitude_Sobel_Lua, direcao_Sobel_Lua = calcula_magnitude_direcao(gradienteX_Sobel_Lua, gradienteY_Sobel_Lua)
magnitude_Sobel_Xadrez, direcao_Sobel_Xadrez = calcula_magnitude_direcao(gradienteX_Sobel_Xadrez, gradienteY_Sobel_Xadrez)
magnitude_Sobel_Prato, direcao_Sobel_Prato = calcula_magnitude_direcao(gradienteX_Sobel_Prato, gradienteY_Sobel_Prato)
magnitude_Sobel_Moedas, direcao_Sobel_Moedas = calcula_magnitude_direcao(gradienteX_Sobel_Moedas, gradienteY_Sobel_Moedas)
magnitude_Prewitt_Lua, direcao_Prewitt_Lua = calcula_magnitude_direcao(gradienteX_Sobel_Lua, gradienteY_Sobel_Lua)
magnitude_Prewitt_Xadrez, direcao_Prewitt_Xadrez = calcula_magnitude_direcao(gradienteX_Sobel_Xadrez, gradienteY_Sobel_Xadrez)
magnitude_Prewitt_Prato, direcao_Prewitt_Prato = calcula_magnitude_direcao(gradienteX_Sobel_Prato, gradienteY_Sobel_Prato)
magnitude_Prewitt_Moedas, direcao_Prewitt_Moedas = calcula_magnitude_direcao(gradienteX_Sobel_Moedas, gradienteY_Sobel_Moedas)

magnitude_Sobel_Lua_CV, direcao_Sobel_Lua_CV = calcula_magnitude_direcao(sobel_xCV_Lua, sobel_yCV_Lua)
magnitude_Sobel_Xadrez_CV, direcao_Sobel_Xadrez_CV = calcula_magnitude_direcao(sobel_xCV_Xadrez, sobel_yCV_Xadrez)
magnitude_Sobel_Prato_CV, direcao_Sobel_Prato_CV = calcula_magnitude_direcao(sobel_xCV_Prato, sobel_yCV_Prato)
magnitude_Sobel_Moedas_CV, direcao_Sobel_Moedas_CV = calcula_magnitude_direcao(sobel_xCV_Moedas, sobel_yCV_Moedas)
magnitude_Prewitt_Lua_CV, direcao_Prewitt_Lua_CV = calcula_magnitude_direcao(prewitt_xCV_Lua, prewitt_yCV_Lua)
magnitude_Prewitt_Xadrez_CV, direcao_Prewitt_Xadrez_CV = calcula_magnitude_direcao(prewitt_xCV_Xadrez, prewitt_yCV_Xadrez)
magnitude_Prewitt_Prato_CV, direcao_Prewitt_Prato_CV = calcula_magnitude_direcao(prewitt_xCV_Prato, prewitt_yCV_Prato)
magnitude_Prewitt_Moedas_CV, direcao_Prewitt_Moedas_CV = calcula_magnitude_direcao(prewitt_xCV_Moedas, prewitt_yCV_Moedas)

K = 1.5

def maximoLocal(magnitude, K, direcao_precisa):
    imagem_borda = np.zeros_like(magnitude)
    altura, largura = imagem_borda.shape
    for i in range(1, altura - 1):
        for j in range(1, largura - 1):
            angulo = direcao_precisa[i, j]
            

            if (angulo >= -22.5 and angulo < 22.5) or (angulo >= 157.5 or angulo < -157.5):  
                vizinho1, vizinho2 = magnitude[i, j - 1], magnitude[i, j + 1]
            elif (angulo >= 22.5 and angulo < 67.5) or (angulo >= -157.5 and angulo < -112.5):  
                vizinho1, vizinho2 = magnitude[i - 1, j + 1], magnitude[i + 1, j - 1]
            elif (angulo >= 67.5 and angulo < 112.5) or (angulo >= -112.5 and angulo < -67.5):  
                vizinho1, vizinho2 = magnitude[i - 1, j], magnitude[i + 1, j]
            else:  
                vizinho1, vizinho2 = magnitude[i - 1, j - 1], magnitude[i + 1, j + 1]
            

            if magnitude[i, j] >= K * vizinho1 and magnitude[i, j] >= K * vizinho2:
                imagem_borda[i, j] = magnitude[i, j] 

    return imagem_borda

imagem_final_Lua_Sobel_CV = maximoLocal(magnitude_Sobel_Lua_CV, K, direcao_Sobel_Lua_CV)
imagem_final_Lua_Prewitt_CV = maximoLocal(magnitude_Prewitt_Lua_CV, K, direcao_Prewitt_Lua_CV)
imagem_final_Lua_Sobel_manual = maximoLocal(magnitude_Sobel_Lua, K, direcao_Sobel_Lua)
imagem_final_Lua_Prewitt_manual = maximoLocal(magnitude_Prewitt_Lua, K, direcao_Prewitt_Lua)
imagem_final_Xadrez_Sobel_CV = maximoLocal(magnitude_Sobel_Xadrez_CV, K, direcao_Sobel_Xadrez_CV)
imagem_final_Xadrez_Prewitt_CV = maximoLocal(magnitude_Prewitt_Xadrez_CV, K, direcao_Prewitt_Xadrez_CV)
imagem_final_Xadrez_Sobel_manual = maximoLocal(magnitude_Sobel_Xadrez, K, direcao_Sobel_Xadrez)
imagem_final_Xadrez_Prewitt_manual = maximoLocal(magnitude_Prewitt_Xadrez, K, direcao_Prewitt_Xadrez)
imagem_final_Prato_Sobel_CV = maximoLocal(magnitude_Sobel_Prato_CV, K, direcao_Sobel_Prato_CV)
imagem_final_Prato_Prewitt_CV = maximoLocal(magnitude_Prewitt_Prato_CV, K, direcao_Prewitt_Prato_CV)
imagem_final_Prato_Sobel_manual = maximoLocal(magnitude_Sobel_Prato, K, direcao_Sobel_Prato)
imagem_final_Prato_Prewitt_manual = maximoLocal(magnitude_Prewitt_Prato, K, direcao_Prewitt_Prato)
imagem_final_Moedas_Sobel_CV = maximoLocal(magnitude_Sobel_Moedas_CV, K, direcao_Sobel_Moedas_CV)
imagem_final_Moedas_Prewitt_CV = maximoLocal(magnitude_Prewitt_Moedas_CV, K, direcao_Prewitt_Moedas_CV)
imagem_final_Moedas_Sobel_manual = maximoLocal(magnitude_Sobel_Moedas, K, direcao_Sobel_Moedas)
imagem_final_Moedas_Prewitt_manual = maximoLocal(magnitude_Prewitt_Moedas, K, direcao_Prewitt_Moedas)

cv2.imwrite("resultados/imagem_final_Lua_Sobel_CV3.jpg", imagem_final_Lua_Sobel_CV)
cv2.imwrite("resultados/imagem_final_Lua_Prewitt_CV3.jpg", imagem_final_Lua_Prewitt_CV)
cv2.imwrite("resultados/imagem_final_Lua_Sobel_manual3.jpg", imagem_final_Lua_Sobel_manual)
cv2.imwrite("resultados/imagem_final_Lua_Prewitt_manual3.jpg", imagem_final_Lua_Prewitt_manual)

cv2.imwrite("resultados/imagem_final_Xadrez_Sobel_CV3.jpg", imagem_final_Xadrez_Sobel_CV)
cv2.imwrite("resultados/imagem_final_Xadrez_Prewitt_CV3.jpg", imagem_final_Xadrez_Prewitt_CV)
cv2.imwrite("resultados/imagem_final_Xadrez_Sobel_manual3.jpg", imagem_final_Xadrez_Sobel_manual)
cv2.imwrite("resultados/imagem_final_Xadrez_Prewitt_manual3.jpg", imagem_final_Xadrez_Prewitt_manual)

cv2.imwrite("resultados/imagem_final_Prato_Sobel_CV3.jpg", imagem_final_Prato_Sobel_CV)
cv2.imwrite("resultados/imagem_final_Prato_Prewitt_CV3.jpg", imagem_final_Prato_Prewitt_CV)
cv2.imwrite("resultados/imagem_final_Prato_Sobel_manual3.jpg", imagem_final_Prato_Sobel_manual)
cv2.imwrite("resultados/imagem_final_Prato_Prewitt_manual3.jpg", imagem_final_Prato_Prewitt_manual)

cv2.imwrite("resultados/imagem_final_Moedas_Sobel_CV3.jpg", imagem_final_Moedas_Sobel_CV)
cv2.imwrite("resultados/imagem_final_Moedas_Prewitt_CV3.jpg", imagem_final_Moedas_Prewitt_CV)
cv2.imwrite("resultados/imagem_final_Moedas_Sobel_manual3.jpg", imagem_final_Moedas_Sobel_manual)
cv2.imwrite("resultados/imagem_final_Moedas_Prewitt_manual3.jpg", imagem_final_Moedas_Prewitt_manual)

def calcular_ssim(imagem1_path, imagem2_path, descricao):
    imagem1 = cv2.imread(imagem1_path, cv2.IMREAD_GRAYSCALE)
    imagem2 = cv2.imread(imagem2_path, cv2.IMREAD_GRAYSCALE)

    if imagem1.shape != imagem2.shape:
        print(f"As imagens {descricao} têm tamanhos diferentes e não podem ser comparadas com SSIM.")
    else:
        score, diff = ssim(imagem1, imagem2, full=True)
        print(f"SSIM entre {descricao}: {score:.4f}")
        return score


calcular_ssim("resultados/imagem_final_Lua_Sobel_CV3.jpg", "resultados/imagem_final_Lua_Sobel_manual3.jpg", "Lua Sobel CV vs Manual")
calcular_ssim("resultados/imagem_final_Lua_Prewitt_CV3.jpg", "resultados/imagem_final_Lua_Prewitt_manual3.jpg", "Lua Prewitt CV vs Manual")

calcular_ssim("resultados/imagem_final_Xadrez_Sobel_CV3.jpg", "resultados/imagem_final_Xadrez_Sobel_manual3.jpg", "Xadrez Sobel CV vs Manual")
calcular_ssim("resultados/imagem_final_Xadrez_Prewitt_CV3.jpg", "resultados/imagem_final_Xadrez_Prewitt_manual3.jpg", "Xadrez Prewitt CV vs Manual")

calcular_ssim("resultados/imagem_final_Prato_Sobel_CV3.jpg", "resultados/imagem_final_Prato_Sobel_manual3.jpg", "Prato Sobel CV vs Manual")
calcular_ssim("resultados/imagem_final_Prato_Prewitt_CV3.jpg", "resultados/imagem_final_Prato_Prewitt_manual3.jpg", "Prato Prewitt CV vs Manual")

calcular_ssim("resultados/imagem_final_Moedas_Sobel_CV3.jpg", "resultados/imagem_final_Moedas_Sobel_manual3.jpg", "Moedas Sobel CV vs Manual")
calcular_ssim("resultados/imagem_final_Moedas_Prewitt_CV3.jpg", "resultados/imagem_final_Moedas_Prewitt_manual3.jpg", "Moedas Prewitt CV vs Manual")