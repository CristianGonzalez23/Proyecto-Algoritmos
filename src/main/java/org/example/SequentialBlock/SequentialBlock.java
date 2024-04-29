package org.example.SequentialBlock;

class SequentialBlock{



    public static int[][] blockMatrixMultiply(int[][] A, int[][] B, int[][] C, int size, int bsize) {
        // Crear la matriz resultado
        int[][] result = new int[size][size];

        // Iterar sobre bloques i1, j1, k1
        for (int i1 = 0; i1 < size; i1 += bsize) {
            for (int j1 = 0; j1 < size; j1 += bsize) {
                for (int k1 = 0; k1 < size; k1 += bsize) {
                    // Iterar dentro del bloque (i1, j1, k1)
                    for (int i = i1; i < i1 + bsize && i < size; i++) {
                        for (int j = j1; j < j1 + bsize && j < size; j++) {
                            for (int k = k1; k < k1 + bsize && k < size; k++) {
                                result[i][j] += A[i][k] * B[k][j] * C[i][k]; // Multiplicación dentro del bloque
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Tamaño de las matrices y tamaño de bloque
        int size = 4;
        int bsize = 2;

        // Ejemplo de matrices A, B y C
        int[][] A = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        int[][] B = {
            {17, 18, 19, 20},
            {21, 22, 23, 24},
            {25, 26, 27, 28},
            {29, 30, 31, 32}
        };

        int[][] C = {
            {33, 34, 35, 36},
            {37, 38, 39, 40},
            {41, 42, 43, 44},
            {45, 46, 47, 48}
        };

        // Realizar multiplicación de matrices por bloques
        int[][] result = blockMatrixMultiply(A, B, C, size, bsize);

        // Imprimir el resultado
        System.out.println("Resultado de la multiplicación de matrices por bloques:");
        printMatrix(result);
    }

    // Función auxiliar para imprimir una matriz
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }
}