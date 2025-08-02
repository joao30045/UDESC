import pickle
import numpy as np
import matplotlib.pyplot as plt
from collections import deque
from mpl_toolkits.mplot3d import Axes3D

def visualize_slice(volume):
    for i in volume.size():
        if 0 <= i < volume.shape[0]:
            plt.imshow(volume[i], cmap='gray')
            plt.title(f'Slice {i}')
            plt.colorbar()
            plt.show()
        else:
            print("Index out of range")

def generate_neighbours(connectivity):
    neighbours = []
    for dz in [-1, 0, 1]:
        for dy in [-1, 0, 1]:
            for dx in [-1, 0, 1]:
                if dx == dy == dz == 0:
                    continue
                if connectivity == 6:
                    if abs(dx) + abs(dy) + abs(dz) == 1:
                        neighbours.append((dz, dy, dx))
                else:  # C26
                    neighbours.append((dz, dy, dx))
    return neighbours

def label_volume(volume, target_intensity, connectivity):
    M, N, P = volume.shape
    labeled = np.zeros_like(volume, dtype=int)
    visited = np.zeros_like(volume, dtype=bool)
    neighbours = generate_neighbours(connectivity)
    current_label = 1
    component = {}
    total_voxels = 0  

    for z in range(M):
        for y in range(N):
            for x in range(P):
                if volume[z, y, x] == target_intensity and not visited[z, y, x]:
                    # BFS
                    queue = deque()
                    queue.append((z, y, x))
                    visited[z, y, x] = True
                    labeled[z, y, x] = current_label
                    component_size = 1

                    while queue:
                        cz, cy, cx = queue.popleft()
                        for dz, dy, dx in neighbours:
                            nz, ny, nx = cz + dz, cy + dy, cx + dx
                            if 0 <= nz < M and 0 <= ny < N and 0 <= nx < P:
                                if (not visited[nz, ny, nx] and 
                                    volume[nz, ny, nx] == target_intensity):
                                    queue.append((nz, ny, nx))
                                    visited[nz, ny, nx] = True
                                    labeled[nz, ny, nx] = current_label
                                    component_size += 1
                    component[current_label] = component_size
                    total_voxels += component_size 
                    current_label += 1

    largest_group = max(component, key=component.get) if component else None

    return labeled, component, largest_group, total_voxels


def plotar_agrupamento_3d(labeled_volume, desired_label, intensity):


    z, y, x = np.where(labeled_volume == desired_label)

    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    
    ax.scatter(x, y, z, c='purple', marker='s')

    if intensity == 255:
        ax.set_title('Maior grupo do tipo Proliferativa')
    elif intensity == 200:
        ax.set_title('Maior grupo do tipo Quiescente')
    elif intensity == 140:
        ax.set_title('Maior grupo do tipo Necrótica')
    else:
        ax.set_title(f'Agrupamento de intensidade {intensity}')
    
    ax.set_xlabel('Eixo X')
    ax.set_ylabel('Eixo Y')
    ax.set_zlabel('Eixo Z')
    
    plt.show()


def plotar_histograma(volume):
    intensidades = {
        'Proliferativas': 255,
        'Quiescentes': 200,
        'Necróticas': 140
    }

    totais = {}
    for tipo, intensidade in intensidades.items():
        totais[tipo] = np.sum(volume == intensidade)

    tipos = list(totais.keys())
    quantidades = list(totais.values())

    plt.figure(figsize=(8, 5))
    bars = plt.bar(tipos, quantidades, color=['purple', 'blue', 'black'])

    for bar, quantidade in zip(bars, quantidades):
        plt.text(bar.get_x() + bar.get_width() / 2, bar.get_height(), 
                 str(quantidade), ha='center', va='bottom', fontsize=10)

    plt.title('Quantidade de Células por Tipo')
    plt.xlabel('Tipo de Célula')
    plt.ylabel('Quantidade')
    plt.grid(axis='y', linestyle='--', alpha=0.7)
    plt.tight_layout()
    plt.show()


def main():
    with open('volume_TAC', 'rb') as f:
        volume = pickle.load(f)

    plotar_histograma(volume)

    while True:
        intensity_map = {
            "1": ("Proliferativa", 255),
            "2": ("Quiescente", 200),
            "3": ("Necrótica", 140)
        }

        print("Escolha o tipo de célula:")
        print("1 - Proliferativa (255)")
        print("2 - Quiescente (200)")
        print("3 - Necrótica (140)")
        type = input("Digite o número correspondente: ").strip()

        if type not in intensity_map:
            print("Opção inválida.")
            return

        name, intensity = intensity_map[type]

        conectivity = int(input("Digite a conectividade desejada (6 ou 26): ").strip())
        if conectivity not in [6, 26]:
            print("Conectividade inválida.")
            return

        label, components, largest_group, total_voxels = label_volume(volume, intensity, conectivity)

        print(f"\nTipo de célula: {name}")
        print(f"Conectividade usada: C{conectivity}")
        print(f"total de células: {total_voxels}")
        print(f"Total de agrupamentos encontrados: {len(components)}")
        print("Tamanhos:", components)
        print(f"Maior agrupamento: rótulo {largest_group} com {components[largest_group]} voxels")



        plotar_agrupamento_3d(label, largest_group, intensity)


if __name__ == "__main__":
    main()