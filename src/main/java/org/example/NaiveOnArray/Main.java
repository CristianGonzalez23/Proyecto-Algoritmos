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
        int[][] a1 = LeerMatriz.loadMatrixFromFile("matriz6.txt");
        int[][] b1 = LeerMatriz.loadMatrixFromFile("matriz6(2).txt");



            long start = System.currentTimeMillis();
            int[][] result = NaiveOnArray.multiply(a1, b1);
            long end = System.currentTimeMillis();


        long executionTime = end - start;
        System.out.println("Tiempo de ejecución: " + executionTime + " milisegundos");

        List<String> categories = new ArrayList<>();
        List<Long> executionTimes = new ArrayList<>();

// Añade la categoría y el tiempo de ejecución para el algoritmo NaiveOnArray
        categories.add("NaiveOnArray" + (a1.length / 2 + 1));
        executionTimes.add(executionTime);


        // Guarda el tiempo de ejecución y el tamaño de la entrada
        PerformanceLogger.logPerformance("NaiveOnArray", a1.length, executionTime);


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