package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.example.GenerarMatrices.LeerMatriz;
import org.example.III3_SequentialBlock.III3_SequentialBlock;
import org.example.III4_ParallelBlock.III4_ParallelBlock;
import org.example.III5_EnhancedParallelBlock.III5_EnhancedParallelBlock;
import org.example.IV3_SequentialBlock.IV3_SequentialBlock;
import org.example.IV4_ParallelBlock.IV4_ParallelBlock;
import org.example.NaivLoopUnrollingFour.NaivLoopUnrollingFour;
import org.example.NaivLoopUnrollingTwo.NaivLoopUnrollingTwo;
import org.example.NaivOnArray.NaivOnArray;
import org.example.StrassenNaiv.StrassenNaiv;
import org.example.StrassenWinograd.StrassenWinograd;
import org.example.V3_SequentialBlock.V3_SequentialBlock;
import org.example.V4_ParallelBlock.V4_ParallelBlock;
import org.example.WinogradOriginal.WinogradOriginal;
import org.example.WinogradScaled.WinogradScaled;
import org.example.IV5_EnhancedParallelBlock.IV5_EnhancedParallelBlock;

/**
 * Clase CalculadoraTiempos que implementa la comparación de tiempos de ejecución de diferentes algoritmos de multiplicación de matrices.
 */
public class CalculadoraTiempos {


        protected static final List<String> categorias = new ArrayList<>();
        protected static final List<Double> tiemposEjecucion = new ArrayList<>();
    public static void calcularTiempos(int i) {

        double[][] a;
        double[][] b;
        try {
            a = LeerMatriz.loadMatrixFromFile("./matrices/matriz" + i + ".txt");
            b = LeerMatriz.loadMatrixFromFile("./matrices/matriz" + i + "(2).txt");
            double[][] resultado = new double[a.length][b[0].length];
            int n = a.length;
            int p = b.length;
            int m = b[0].length;

            calcularNaivOnArray(a, b);
            calcularNaivLoopUnrollingTwo(a, b);
            calcularNaivLoopUnrollingFour(a, b);
            calcularWinogradOriginal(a, b, resultado, n, p, m);
            calcularWinogradScaled(a, b, resultado, n, p, m);
            calcularStrassenNaiv(a, b); //Realentiza demasiado el programa
            calcularStrassenWinograd(a, b, resultado, n, p, m);
            calcularIII3SequentialBlock(a, b, i, i/4);
            calcularIII4ParallelBlock(a, b);
            calcularIII5EnhanchedParallelBlock(a, b);
            calcularIV3SequentialBlock(a, b, i, i/4);
            calcularIV4ParallelBlock(a, b);
            calcularIV5EnhanchedParallelBlock(a, b);
            calcularV3SequentialBlock(a,b,i,i/4);
            calcularV4ParallelBlock(a,b);

            ChartGenerator.generateBarChart(
                categorias,
                tiemposEjecucion,
                "Tiempos de ejecución de los algoritmos con matrices de tamaño " + i,
                "Algoritmo",
                "Tiempo de ejecucion (ms)",
                i
        );

        categorias.clear();
        tiemposEjecucion.clear();

        JOptionPane.showMessageDialog(null, "Tiempos de ejecución de los algoritmos con matrices de tamaño " + i + " generados correctamente");

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }

    }
    private static void calcularV4ParallelBlock(double[][] a, double[][] b) {
        long start = System.nanoTime();
        V4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("V4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución V4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("V.4ParallelBlock ", a.length, executionTime);
    }
    private static void calcularV3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        long start = System.nanoTime();
        V3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("V3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución V3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("V.3SequentialBlock ", a.length, executionTime);
    }
    private static void calcularIV5EnhanchedParallelBlock(double[][] a, double[][] b) {
        long start = System.nanoTime();
        IV5_EnhancedParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("IV5.EnhanchedParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV5.EnhanchedParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("IV.5EnhanchedParallelBlock ", a.length, executionTime);
    }
    private static void calcularIV4ParallelBlock(double[][] a, double[][] b) {
        long start = System.nanoTime();
        IV4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("IV4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("IV.4ParallelBlock ", a.length, executionTime);
    }
    private static void calcularIV3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        long start = System.nanoTime();
        IV3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("IV3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("IV.3SequentialBlock ", a.length, executionTime);
    }
    private static void calcularIII5EnhanchedParallelBlock(double[][] a, double[][] b) {
        long start = System.nanoTime();
        III5_EnhancedParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("III5.EnhanchedParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III5.EnhanchedParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("III.5EnhanchedParallelBlock ", a.length, executionTime);
    }
    private static void calcularIII4ParallelBlock(double[][] a, double[][] b) {
        long start = System.nanoTime();
        III4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("III4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("III.4ParallelBlock ", a.length, executionTime);
    }
    private static void calcularIII3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        long start = System.nanoTime();
        III3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("III3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("III.3SequentialBlock ", a.length, executionTime);
    }
    private static void calcularStrassenWinograd(double[][] a, double[][] b, double[][] resultado, int n, int p,
            int m) {
        long start = System.nanoTime();
        StrassenWinograd.multiply(a, b, resultado, n, p, m);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("StrassenWinograd");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución StrassenWinograd: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("StrassenWinograd ", a.length, executionTime);

    }
    private static void calcularStrassenNaiv(double[][] a, double[][] b) {
        long start = System.nanoTime();
        StrassenNaiv.multiply(a, b);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("StrassenNaiv");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución StrassenNaiv: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("StrassenNaiv ", a.length, executionTime);
    }
    private static void calcularWinogradScaled(double[][] a, double[][] b, double[][] resultado, int n, int p, int m) {
        long start = System.nanoTime();
        WinogradScaled.multiply(a, b, resultado, n, p, m);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("WinogradScaled");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución WinogradScaled: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("WinogradScaled ", a.length, executionTime);
    }
    private static void calcularWinogradOriginal(double[][] a, double[][] b, double[][] resultado, int n, int p,
            int m) {
        long start = System.nanoTime();
        WinogradOriginal.multiply(a, b, resultado, n, p, m);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("WinogradOriginal");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución WinogradOriginal: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("WinogradOriginal ", a.length, executionTime);
        
    }
    private static void calcularNaivLoopUnrollingFour(double[][] a, double[][] b) {
        long start = System.nanoTime();
        NaivLoopUnrollingFour.multiply(a, b);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("NaivLoopUnrollingFour");
        tiemposEjecucion.add(executionTime);    
        System.out.println("Tiempo de ejecución NaivLoopUnrollingFour: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("NaivLoopUnrollingFour ", a.length, executionTime);
    }
    private static void calcularNaivLoopUnrollingTwo(double[][] a, double[][] b) {
        long start = System.nanoTime();
        NaivLoopUnrollingTwo.multiply(a, b);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("NaivLoopUnrollingTwo");
        tiemposEjecucion.add(executionTime);    
        System.out.println("Tiempo de ejecución NaivLoopUnrollingTwo: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("NaivLoopUnrollingTwo ", a.length, executionTime);
    }
    private static void calcularNaivOnArray(double[][] a, double[][] b) {
        long start = System.nanoTime();
        NaivOnArray.multiply(a, b);
        long end = System.nanoTime();
        double executionTime = (end - start)/1000000.0;
        categorias.add("NaivOnArray");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución NaivOnArray: " + executionTime + " milisegundos");    
        PerformanceLogger.logPerformance("NaivOnArray ", a.length, executionTime);
    }

}
