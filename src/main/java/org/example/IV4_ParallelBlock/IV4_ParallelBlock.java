package org.example.IV4_ParallelBlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IV4_ParallelBlock {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] C = matrixMultiplicationParallel(A, B);
        printMatrix(C);
        
    }

    public static double[][] matrixMultiplicationParallel(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        int p = B.length;
        int q = B[0].length;

        if (n != p) {
            throw new IllegalArgumentException("Las dimensiones de las matrices no son compatibles para la multiplicación.");
        }

        double[][] C = new double[m][q];

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < m; i++) {
            final int row = i;
            executor.execute(() -> {
                for (int j = 0; j < q; j++) {
                    for (int k = 0; k < n; k++) {
                        C[row][k] += A[row][j] * B[j][k];
                    }
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Esperar a que todas las tareas se completen
        }

        return C;
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
