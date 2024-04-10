package org.example.NaivLoopUnrollingTwo;

import org.example.GenerarMatrices.LeerMatriz;
import org.example.ChartGenerator;
import org.example.PerformanceLogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int[][] a1 = LeerMatriz.loadMatrixFromFile("matriz100.txt");
        int[][] b1 = LeerMatriz.loadMatrixFromFile("matriz100(2).txt");

        long start = System.currentTimeMillis();
        int[][] result = NaivLoopUnrollingTwo.multiply(a1, b1);
        long end = System.currentTimeMillis();

        long executionTime = end - start;
        System.out.println("Tiempo de ejecución: " + executionTime + " milisegundos");

        List<String> categories = new ArrayList<>();
        List<Long> executionTimes = new ArrayList<>();

        categories.add("NaivLoopUnrollingTwo" + (a1.length / 2 + 1));
        executionTimes.add(executionTime);

        // Guarda el tiempo de ejecución y el tamaño de la entrada
        PerformanceLogger.logPerformance("NaivLoopUnrollingTwo", a1.length, executionTime);

        ChartGenerator.generateBarChart(
                categories,
                executionTimes,
                "Performance of NaivLoopUnrollingTwo",
                "Algorithm",
                "Execution time (nano seconds)"
        );
    }
}