package org.example.WinogradScaled;

import org.example.WinogradOriginal.WinogradOriginal;

/**
 * Clase WinogradScaled que implementa una versión escalada del algoritmo de Winograd para la multiplicación de matrices.
 */
public class WinogradScaled {

    /**
     * Método para multiplicar dos matrices utilizando una versión escalada del algoritmo de Winograd.
     *
     * @param A primera matriz
     * @param B segunda matriz
     * @param Result matriz de resultado
     * @param N número de filas de la matriz A
     * @param P número de columnas de la matriz A y número de filas de la matriz B
     * @param M número de columnas de la matriz B
     */
    public static void multiply(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        // Crear copias escaladas de A y B
        double[][] CopyA = new double[N][P];
        double[][] CopyB = new double[P][M];

        // Factores de escalamiento
        double a = normInf(A, N, P);
        double b = normInf(B, P, M);
        double lambda = Math.floor(0.5 + Math.log(b / a) / Math.log(4));

        // Escalamiento
        multiplyWithScalar(A, CopyA, N, P, Math.pow(2, lambda));
        multiplyWithScalar(B, CopyB, P, M, Math.pow(2, -lambda));

        // Usando Winograd con matrices escaladas
        WinogradOriginal.multiply(CopyA, CopyB, Result, N, P, M);
    }

    /**
     * Función auxiliar para calcular la norma infinito de una matriz.
     *
     * @param matrix matriz a calcular la norma
     * @param rows número de filas de la matriz
     * @param cols número de columnas de la matriz
     * @return la norma infinito de la matriz
     */
    private static double normInf(double[][] matrix, int rows, int cols) {
        double maxVal = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxVal = Math.max(maxVal, Math.abs(matrix[i][j]));
            }
        }
        return maxVal;
    }

    /**
     * Función auxiliar para multiplicar una matriz por un escalar.
     *
     * @param source matriz fuente
     * @param target matriz objetivo
     * @param rows número de filas de las matrices
     * @param cols número de columnas de las matrices
     * @param scalar escalar para multiplicar
     */
    private static void multiplyWithScalar(double[][] source, double[][] target, int rows, int cols, double scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                target[i][j] = source[i][j] * scalar;
            }
        }
    }

    /**
     * Método para imprimir una matriz.
     *
     * @param matrix matriz a imprimir
     * @param rows número de filas de la matriz
     * @param cols número de columnas de la matriz
     */
    public static void printMatrix(double[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Método principal para probar la multiplicación de matrices utilizando una versión escalada del algoritmo de Winograd.
     *
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] Result = new double[2][2];

        multiply(A, B, Result, 2, 2, 2);
        printMatrix(Result, 2, 2);
    }
}