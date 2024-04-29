package org.example.GenerarMatrices;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerMatriz {
    public static double[][] loadMatrixFromFile(String fileName) throws FileNotFoundException {
        List<double[]> matrix = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(", ");
            double[] row = new double[line.length]; // Fixed line
            for (int i = 0; i < line.length; i++) {
                row[i] = Integer.parseInt(line[i]);
            }
            matrix.add(row);
        }

        return matrix.toArray(new double[0][]);
    }
}