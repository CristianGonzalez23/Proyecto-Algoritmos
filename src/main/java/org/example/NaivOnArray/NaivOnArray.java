package org.example.NaivOnArray;

/**
 * Clase NaivOnArray que contiene métodos para la multiplicación de matrices utilizando el método náive.
 */
public class NaivOnArray {
    /**
     * Método para multiplicar dos matrices utilizando el método náive.
     *
     * @param A La primera matriz para la multiplicación.
     * @param B La segunda matriz para la multiplicación.
     * @return La matriz resultante de la multiplicación.
     */
    public static double[][] multiply(double[][] A, double[][] B) {
        int N = A.length;
        int P = A[0].length;
        int M = B[0].length;
        double[][] Result = new double[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Result[i][j] = 0.0;
                for (int k = 0; k < P; k++) {
                    Result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return Result;
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
            System.out.println();
        }
    }

    /**
     * Método principal para probar la multiplicación de matrices.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] C = multiply(A, B);
        printMatrix(C);
    }
}