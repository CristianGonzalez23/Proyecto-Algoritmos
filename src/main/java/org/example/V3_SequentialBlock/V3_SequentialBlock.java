package org.example.V3_SequentialBlock;

/**
 * Clase V3_SequentialBlock que implementa la multiplicación de matrices utilizando un enfoque de bloque secuencial.
 */
public class V3_SequentialBlock {

    /**
     * Método para multiplicar dos matrices utilizando un enfoque de bloque secuencial.
     *
     * @param A primera matriz
     * @param B segunda matriz
     * @param size tamaño de las matrices
     * @param bsize tamaño del bloque
     * @return Resultado de la multiplicación de las matrices A y B
     */
    public static double[][] blockMatrixMultiply(double[][] A, double[][] B, int size, int bsize) {
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
                                result[k][i] += A[k][j] * B[j][i]; // Multiplicación dentro del bloque
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * Método principal para probar la multiplicación de matrices utilizando un enfoque de bloque secuencial.
     *
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] C = blockMatrixMultiply(A, B, 2,128);
        printMatrix(C);

    }

    /**
     * Método para imprimir una matriz.
     *
     * @param matrix matriz a imprimir
     */
    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.print(num + " ");
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }
}