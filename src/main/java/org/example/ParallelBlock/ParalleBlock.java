package org.example.ParallelBlock;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;





public class ParalleBlock {

    

    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        int[][] A = {{1, 2, 3}, {4, 5, 6}};
        int[][] B = {{7, 8}, {9, 10}, {11, 12}};

        int[][] C = matrixMultiplicationParallel(A, B);
        printMatrix(C);
        
    }

    public static int[][] matrixMultiplicationParallel(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int p = B.length;
        int q = B[0].length;

        if (n != p) {
            throw new IllegalArgumentException("Las dimensiones de las matrices no son compatibles para la multiplicación.");
        }

        int[][] C = new int[m][q];

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < m; i++) {
            final int row = i;
            executor.execute(() -> {
                for (int j = 0; j < q; j++) {
                    for (int k = 0; k < n; k++) {
                        C[row][j] += A[row][k] * B[k][j];
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

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

