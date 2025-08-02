from PIL import Image
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.cm as cm

path = "/Users/macjp/UDESC/PIM/Exercícios/Exercício 3/taxaPerCapitaRouboCarros.png"
print(path)

# Abre imagem em tons de cinza
pil1 = Image.open(path).convert("L")  # Garante que está em escala de cinza
(l, h) = pil1.size
print(l, h)

# Cria uma nova imagem RGB
out = Image.new("RGB", (l, h))

# Colormap escolhido (pode mudar para viridis, plasma, etc.)
colormap = cm.jet

# Varre cada pixel
for j in range(h):
    for i in range(l):
        gray_value = pil1.getpixel((i, j))  # valor de 0 a 255
        normalized = gray_value / 255.0     # de 0 a 1
        color = colormap(normalized)        # retorna (R,G,B,A) entre 0 e 1
        rgb = tuple([int(255 * c) for c in color[:3]])  # converte para RGB (0–255)
        out.putpixel((i, j), rgb)

# Exibe a imagem colorida
plt.imshow(out)
plt.axis("off")
plt.title("Imagem em Pseudo-Cor (com getpixel/putpixel)")
plt.show()

# Salva a imagem
out.save("taxaPerCapitaPseudoCor_PixelAPixel.png")
print("Imagem salva como taxaPerCapitaPseudoCor_PixelAPixel.png")
