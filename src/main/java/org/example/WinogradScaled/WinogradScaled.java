package org.example.WinogradScaled;


import org.example.WinogradOriginal.WinogradOriginal;

import java.lang.Math;

public class WinogradScaled {

    public static double[][] multiply(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        // Create scaled copies of A and B
        double[][] CopyA = new double[N][P];
        double[][] CopyB = new double[P][M];

        // Scaling factors
        double a = NormInf(A, N, P);
        double b = NormInf(B, P, M);
        double lambda = Math.floor(0.5 + Math.log(b/a) / Math.log(4));

        // Scaling
        double[][] escalada1 =MultiplyWithScalar(A, CopyA, N, P, Math.pow(2, lambda));
        double[][] escalada2 =MultiplyWithScalar(B, CopyB, P, M, Math.pow(2, lambda));


        // Using Winograd with scaled matrices
        return WinogradOriginal.multiply(escalada1, escalada2, Result, N, P, M);
    }

    public static double NormInf(double[][] A, int N, int P) {
        double norm = 0.0;
        double aux;
        for (int i = 0; i < N; i++) {
            aux = 0.0;
            for (int j = 0; j < P; j++) {
                aux += Math.abs(A[i][j]);
            }
            norm = Math.max(norm, aux);
        }
        return norm;
    }

    private static double[][] MultiplyWithScalar(double[][] source, double[][] target, int rows, int cols, double scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                target[i][j] = source[i][j] * scalar;
            }
        }
        return target;
    }


}
