package org.example;

import org.example.GenerarMatrices.LeerMatriz;
import org.example.NaivLoopUnrollingFour.NaivLoopUnrollingFour;
import org.example.NaivLoopUnrollingTwo.NaivLoopUnrollingTwo;
import org.example.NaiveOnArray.NaiveOnArray;
import org.example.WinogradOriginal.WinogradOriginal;
import org.example.WinogradScaled.WinogradScaled;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Lista de nombres de archivos de matrices
        double[][] a1 = LeerMatriz.loadMatrixFromFile("matriz100000.txt");
        double[][] b1 = LeerMatriz.loadMatrixFromFile("matriz100000(2).txt");

        double [][] result1 = new double[a1.length][b1[0].length];
        int n = a1.length;
        int p = b1.length;
        int m = b1[0].length;
        // GeneradorMatrices.generateMatrizToFile(10000,"matriz1000000(2).txt");

        long start = System.currentTimeMillis();
        double[][] result = NaiveOnArray.multiply(a1, b1);
        double[][] result2 = NaivLoopUnrollingFour.multiply(a1, b1);
        double[][] result3 = NaivLoopUnrollingTwo.multiply(a1, b1);
        double[][] result4 = WinogradOriginal.multiply(a1, b1,result1,n,p,m);
        double[][] result5 = WinogradScaled.multiply(a1, b1,result1,n,p,m);
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
