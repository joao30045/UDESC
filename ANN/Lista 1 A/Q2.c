#include <stdio.h>

int main() {
    float epsilon = 1.0;  // Comece com um valor grande para epsilon

    while (1 != (1 + epsilon)) {
        epsilon = epsilon / 2;
    }

    printf("Maior valor de epsilon tal que 1 + epsilon é arredondado para 1: %e\n", epsilon);

    return 0;
}
