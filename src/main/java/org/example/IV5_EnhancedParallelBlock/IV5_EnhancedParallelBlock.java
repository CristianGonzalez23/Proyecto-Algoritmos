package org.example.IV5_EnhancedParallelBlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IV5_EnhancedParallelBlock {
    

    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static double[][] multiplyMatrices(double[][] a, double[][] b) {
        int aRows = a.length;
        int aCols = a[0].length;
        int bCols = b[0].length;

        if (aCols != b.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied: dimensions mismatch");
        }

        double[][] result = new double[aRows][bCols];
        int blockSize = Math.max(1, aRows / NUM_THREADS);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < aRows; i += blockSize) {
            for (int j = 0; j < bCols; j += blockSize) {
                for (int k = 0; k < aCols; k += blockSize) {
                    executor.submit(new MatrixMultiplier(a, b, result, i, j, k,
                            Math.min(i + blockSize, aRows),
                            Math.min(j + blockSize, bCols),
                            Math.min(k + blockSize, aCols)));
                }
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static class MatrixMultiplier implements Runnable {
        private final double[][] a;
        private final double[][] b;
        private final double[][] result;
        private final int startRow;
        private final int startCol;
        private final int startCommon;
        private final int endRow;
        private final int endCol;
        private final int endCommon;

        public MatrixMultiplier(double[][] a, double[][] b, double[][] result,
                                int startRow, int startCol, int startCommon,
                                int endRow, int endCol, int endCommon) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.startRow = startRow;
            this.startCol = startCol;
            this.startCommon = startCommon;
            this.endRow = endRow;
            this.endCol = endCol;
            this.endCommon = endCommon;
        }

        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                for (int j = startCol; j < endCol; j++) {
                    synchronized (result) { // Synchronize on the result matrix
                        for (int k = startCommon; k < endCommon; k++) {
                            result[i][k] += a[i][j] * b[j][k];
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};

        double[][] result = multiplyMatrices(A, B);

        // Print the result matrix
        System.out.println("Result Matrix:");
        printMatrix(result);
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
