package org.example.NaivLoopUnrollingFour;

public class NaivLoopUnrollingFour {
    public static double[][] multiply(double[][] a, double[][] b) {
        int size = a.length;
        double[][] result = new double[size][size]; // Fixed line
        for (int i = 0; i < size; i += 4) {
            for (int j = 0; j < size; j += 4) {
                for (int k = 0; k < size; k += 4) {
                    for (int i1 = i; i1 < i + 4; i1++) {
                        for (int j1 = j; j1 < j + 4; j1++) {
                            for (int k1 = k; k1 < k + 4; k1++) {
                                result[i1][j1] += a[i1][k1] * b[k1][j1];
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        double[][] B = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        double[][] C = multiply(A, B);
        printMatrix(C);
    }
}
