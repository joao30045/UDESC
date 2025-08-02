import cv2
import numpy as np
import matplotlib.pyplot as plt

def lightness_method(img):
    return ((np.max(img, axis=2) + np.min(img, axis=2)) / 2).astype(np.uint8)

def average_method(img):
    return np.mean(img, axis=2).astype(np.uint8)

def luminosity_method(img):
    return (0.3 * img[:, :, 2] + 0.59 * img[:, :, 1] + 0.11 * img[:, :, 0]).astype(np.uint8)

img = cv2.imread("casa.png") 
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

gray_lightness = lightness_method(img)
gray_average = average_method(img)
gray_luminosity = luminosity_method(img)

fig, axes = plt.subplots(1, 4, figsize=(12, 5))
axes[0].imshow(img)
axes[0].set_title("Original")
axes[0].axis("off")

axes[1].imshow(gray_lightness, cmap="gray")
axes[1].set_title("Lightness")
axes[1].axis("off")

axes[2].imshow(gray_average, cmap="gray")
axes[2].set_title("Average")
axes[2].axis("off")

axes[3].imshow(gray_luminosity, cmap="gray")
axes[3].set_title("Luminosity")
axes[3].axis("off")

plt.show()
