package org.example.NaivLoopUnrollingTwo;


public class NaivLoopUnrollingTwo {
    public static int[][] multiply(int[][] a, int[][] b) {
        int size = a.length;
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i += 2) {
            for (int j = 0; j < size; j += 2) {
                for (int k = 0; k < size; k += 2) {
                    int sum1 = a[i][k] * b[k][j] + a[i][k + 1] * b[k + 1][j];
                    int sum2 = a[i][k] * b[k][j + 1] + a[i][k + 1] * b[k + 1][j + 1];
                    int sum3 = a[i + 1][k] * b[k][j] + a[i + 1][k + 1] * b[k + 1][j];
                    int sum4 = a[i + 1][k] * b[k][j + 1] + a[i + 1][k + 1] * b[k + 1][j + 1];
                    result[i][j] += sum1;
                    result[i][j + 1] += sum2;
                    result[i + 1][j] += sum3;
                    result[i + 1][j + 1] += sum4;
                }
            }
        }
        return result;
    }
}
