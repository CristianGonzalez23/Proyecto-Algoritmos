package org.example.NaiveOnArray;

import org.example.ChartGenerator;
import org.example.GenerarMatrices.LeerMatriz;
import org.example.PerformanceLogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Lista de nombres de archivos de matrices
        List<String> matrixFileNames = Arrays.asList("matriz6.txt", "matriz6(2).txt","matriz30.txt","matriz30(2).txt","matriz10.txt","matriz10(2).txt","matriz100.txt","matriz100(2).txt","matriz1000.txt","matriz1000(2).txt");

        List<String> categories = new ArrayList<>();
        List<Long> executionTimes = new ArrayList<>();

        for (int i = 0; i < matrixFileNames.size(); i += 2) {
            int[][] a = LeerMatriz.loadMatrixFromFile(matrixFileNames.get(i));
            int[][] b = LeerMatriz.loadMatrixFromFile(matrixFileNames.get(i + 1));

            long start = System.currentTimeMillis();
            int[][] result = NaiveOnArray.multiply(a, b);
            long end = System.currentTimeMillis();

            long executionTime = end - start;
            PerformanceLogger.logPerformance("NaiveOnArray" + (i / 2 + 1), result.length, executionTime);

            categories.add("NaiveOnArray" + (i / 2 + 1) + " (Matrix Size: " + a.length + ")");
            executionTimes.add(executionTime);
        }

        // Genera el gráfico de barras con los tiempos de ejecución de todos los algoritmos
        ChartGenerator.generateBarChart(
                categories,
                executionTimes,
                "Performance of NaiveOnArray",
                "Algorithm",
                "Execution time (nano seconds)"
        );
    }
}