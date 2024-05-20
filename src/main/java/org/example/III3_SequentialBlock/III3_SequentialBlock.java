package org.example.III3_SequentialBlock;

/**
 * Clase III3_SequentialBlock que contiene métodos para la multiplicación de matrices utilizando el método de bloques secuenciales.
 */
public class III3_SequentialBlock{

    /**
     * Método para multiplicar dos matrices utilizando el método de bloques secuenciales.
     *
     * @param A La primera matriz para la multiplicación.
     * @param B La segunda matriz para la multiplicación.
     * @param size El tamaño de las matrices.
     * @param bsize El tamaño de los bloques.
     * @return La matriz resultante de la multiplicación.
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
                                result[i][j] += A[i][k] * B[k][j]; // Multiplicación dentro del bloque
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * Método principal para probar la multiplicación de matrices.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] C = blockMatrixMultiply(A, B, 2,128);
        printMatrix(C);

    }

    /**
     * Método auxiliar para imprimir una matriz.
     *
     * @param matrix La matriz a imprimir.
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