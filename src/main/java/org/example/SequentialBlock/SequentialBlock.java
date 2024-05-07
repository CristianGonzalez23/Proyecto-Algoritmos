package org.example.SequentialBlock;

public class SequentialBlock{



    public static double[][] blockMatrixMultiply(double[][] A, double[][] B, double[][] C, int size, int bsize) {
        // Crear la matriz resultado
        double[][] result = new double[size][size];

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