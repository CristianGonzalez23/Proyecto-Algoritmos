package org.example.GenerarMatrices;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneradorMatrices {
    public static void generateMatrizToFile(int size, String fileName) {
        int[][] matrix = new int[size][size];
        Random random = new Random();

        // Llenar la matriz con números aleatorios de 6 dígitos
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(900000) + 100000;
            }
        }

        // Escribir la matriz en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    writer.write(Integer.toString(matrix[i][j]));
                    if (j < size - 1) {
                        writer.write(", ");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}