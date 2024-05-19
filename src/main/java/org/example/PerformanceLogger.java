package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase PerformanceLogger proporciona un método estático para registrar el rendimiento de un algoritmo.
 * Los datos de rendimiento se escriben en un archivo llamado "performance_log.txt".
 */
public class PerformanceLogger {
    // Nombre del archivo donde se escribirán los datos de rendimiento
    private static final String FILE_NAME = "Tiempos_ejecucion.txt";

    /**
     * Registra el rendimiento de un algoritmo.
     *
     * @param algorithmName el nombre del algoritmo
     * @param inputSize el tamaño de la entrada al algoritmo
     * @param executionTime el tiempo de ejecución del algoritmo en nanosegundos
     */
    public static void logPerformance(String algorithmName, int inputSize, double executionTime) {
        // Utiliza try-with-resources para asegurar que el BufferedWriter se cierre después de su uso
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Escribe los datos de rendimiento en el archivo
            writer.write(String.format("%s, %d, %.4f\n", algorithmName, inputSize, executionTime));
        } catch (IOException e) {
            // Imprime la traza de pila para cualquier IOException
            e.printStackTrace();
        }
    }
}
