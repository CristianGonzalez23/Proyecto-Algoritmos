package org.example.III5_EnhancedParallelBlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase III5_EnhancedParallelBlock que contiene métodos para la multiplicación de matrices utilizando el método de bloques paralelos mejorado.
 */
public class III5_EnhancedParallelBlock {

    // Número de hilos disponibles en el sistema
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    /**
     * Método para multiplicar dos matrices en paralelo utilizando el método de bloques mejorado.
     *
     * @param A La primera matriz para la multiplicación.
     * @param B La segunda matriz para la multiplicación.
     * @param size El tamaño de las matrices.
     * @param bsize El tamaño de los bloques.
     * @return La matriz resultante de la multiplicación.
     */
    public static double[][] matrixMultiplicationParallel(double[][] A, double[][] B, int size, int bsize) {
        double[][] result = new double[size][size];

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Primera parte de la multiplicación de matrices
        executor.execute(() -> {
            for (int i1 = 0; i1 < size / 2; i1 += bsize) {
                for (int j1 = 0; j1 < size; j1 += bsize) {
                    for (int k1 = 0; k1 < size; k1 += bsize) {
                        for (int i = i1; i < i1 + bsize && i < size; i++) {
                            for (int j = j1; j < j1 + bsize && j < size; j++) {
                                for (int k = k1; k < k1 + bsize && k < size; k++) {
                                    result[i][j] += A[i][k] * B[k][j];
                                }
                            }
                        }
                    }
                }
            }
        });

        // Segunda parte de la multiplicación de matrices
        executor.execute(() -> {
            for (int i1 = size / 2; i1 < size; i1 += bsize) {
                for (int j1 = 0; j1 < size; j1 += bsize) {
                    for (int k1 = 0; k1 < size; k1 += bsize) {
                        for (int i = i1; i < i1 + bsize && i < size; i++) {
                            for (int j = j1; j < j1 + bsize && j < size; j++) {
                                for (int k = k1; k < k1 + bsize && k < size; k++) {
                                    result[i][j] += A[i][k] * B[k][j];
                                }
                            }
                        }
                    }
                }
            }
        });

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return result;
    }

    /**
     * Método principal para probar la multiplicación de matrices en paralelo.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] result = matrixMultiplicationParallel(A, B, A.length, 1);

        // Imprimir la matriz resultante
        System.out.println("Matriz Resultante:");
        printMatrix(result);
    }

    /**
     * Método auxiliar para imprimir una matriz.
     *
     * @param matrix La matriz a imprimir.
     */
    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}