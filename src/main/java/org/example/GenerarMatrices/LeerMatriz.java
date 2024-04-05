package org.example.GenerarMatrices;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerMatriz {
    public static int[][] loadMatrixFromFile(String fileName) throws FileNotFoundException {
        List<int[]> matrix = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(", ");
            int[] row = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                row[i] = Integer.parseInt(line[i]);
            }
            matrix.add(row);
        }

        return matrix.toArray(new int[0][]);
    }
}