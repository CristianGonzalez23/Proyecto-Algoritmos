package org.example.NaiveOnArray;

import org.example.ChartGenerator;
import org.example.GenerarMatrices.LeerMatriz;
import org.example.PerformanceLogger;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * La clase Main contiene el método principal que se ejecuta al iniciar el programa.
 * Esta clase se encarga de generar matrices aleatorias de diferentes tamaños, multiplicarlas
 * utilizando el algoritmo NaiveOnArray, registrar el rendimiento y generar un gráfico de barras
 * con los tiempos de ejecución.
 */
public class Main {
    /**
     * El método principal que se ejecuta al iniciar el programa.
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) throws FileNotFoundException {
      //  GeneradorMatrices.generateMatrizToFile(1000, "matrix1000(2).txt");
        int[][] a1 = LeerMatriz.loadMatrixFromFile("matrix1000.txt");
        int[][] b1 = LeerMatriz.loadMatrixFromFile("matrix1000(2).txt");

        long start1 = System.currentTimeMillis();
        int[][] result1 = NaiveOnArray.multiply(a1, b1);
        long end1 = System.currentTimeMillis();

        long executionTime1 = end1 - start1;
        PerformanceLogger.logPerformance("NaiveOnArray1", result1.length, executionTime1);

        int[][] a2 = LeerMatriz.loadMatrixFromFile("matrix100.txt");
        int[][] b2 = LeerMatriz.loadMatrixFromFile("matrix100(2).txt");

        long start2 = System.currentTimeMillis();
        int[][] result2 = NaiveOnArray.multiply(a2, b2);
        long end2 = System.currentTimeMillis();

        long executionTime2 = end2 - start2;
        PerformanceLogger.logPerformance("NaiveOnArray2", result2.length, executionTime2);

        // Genera el gráfico de barras con los tiempos de ejecución de ambos algoritmos
        ChartGenerator.generateBarChart(
                Arrays.asList("NaiveOnArray1", "NaiveOnArray2"),
                Arrays.asList(executionTime1, executionTime2),
                "Performance of NaiveOnArray",
                "Algorithm",
                "Execution time (milliseconds)"
        );
    }

}