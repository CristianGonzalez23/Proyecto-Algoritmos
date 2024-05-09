package org.example.EnhancedParallelBlock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class EnhancedParalleBlock {
    



    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int aRows = a.length;
        int aCols = a[0].length;
        int bCols = b[0].length;

        if (aCols != b.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied: dimensions mismatch");
        }

        int[][] result = new int[aRows][bCols];
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
        private final int[][] a;
        private final int[][] b;
        private final int[][] result;
        private final int startRow;
        private final int startCol;
        private final int startCommon;
        private final int endRow;
        private final int endCol;
        private final int endCommon;

        public MatrixMultiplier(int[][] a, int[][] b, int[][] result,
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
                    for (int k = startCommon; k < endCommon; k++) {
                        result[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] matrixB = {
            {7, 8},
            {9, 10},
            {11, 12}
        };

        int[][] result = multiplyMatrices(matrixA, matrixB);

        // Print the result matrix
        System.out.println("Result Matrix:");
        printMatrix(result);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

