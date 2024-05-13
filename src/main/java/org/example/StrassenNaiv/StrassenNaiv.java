
package org.example.StrassenNaiv;
import java.util.*;

public class StrassenNaiv{

public static void add_matrix(double[][] matrix_A,double[][] matrix_B,double[][] matrix_C, int split_index)
{
	for (int i = 0; i < split_index; i++){
	for (int j = 0; j < split_index; j++){
		matrix_C[i][j] = matrix_A[i][j] + matrix_B[i][j];
	}
	}
}

public static void initWithZeros(double a[][], int r, int c){
	for(int i=0;i<r;i++){
	for(int j=0;j<c;j++){
		a[i][j]=0;
	}
	}
}

public static double[][] multiply_matrix(double[][] matrix_A,double[][] matrix_B)
{
	int col_1 = matrix_A[0].length;
	int row_1 = matrix_A.length;
	int col_2 = matrix_B[0].length;
	int row_2 = matrix_B.length;

	if (col_1 != row_2) {
	System.out.println("\nError:El numero de las matrices tanto en filas como en columnas debe coincidir \n");
	double temp[][] = new double[1][1];
	temp[0][0]=0;
	return temp;
	}

	int[] result_matrix_row = new int[col_2];
	Arrays.fill(result_matrix_row,0);
	double[][] result_matrix = new double[row_1][col_2];
	initWithZeros(result_matrix,row_1,col_2);

	if (col_1 == 1){
	result_matrix[0][0] = matrix_A[0][0] * matrix_B[0][0]; 
	}else {
	int split_index = col_1 / 2;

	int[] row_vector = new int[split_index];
	Arrays.fill(row_vector,0);

	double[][] result_matrix_00 = new double[split_index][split_index];
	double[][] result_matrix_01 = new double[split_index][split_index];
	double[][] result_matrix_10 = new double[split_index][split_index];
	double[][] result_matrix_11 = new double[split_index][split_index];
	initWithZeros(result_matrix_00,split_index,split_index);
	initWithZeros(result_matrix_01,split_index,split_index);
	initWithZeros(result_matrix_10,split_index,split_index);
	initWithZeros(result_matrix_11,split_index,split_index);
 // subdividimos las matrices 
	double[][] a00 = new double[split_index][split_index];
	double[][] a01 = new double[split_index][split_index];
	double[][] a10 = new double[split_index][split_index];
	double[][] a11 = new double[split_index][split_index];
	double[][] b00 = new double[split_index][split_index];
	double[][] b01 = new double[split_index][split_index];
	double[][] b10 = new double[split_index][split_index];
	double[][] b11 = new double[split_index][split_index];
	initWithZeros(a00,split_index,split_index);
	initWithZeros(a01,split_index,split_index);
	initWithZeros(a10,split_index,split_index);
	initWithZeros(a11,split_index,split_index);
	initWithZeros(b00,split_index,split_index);
	initWithZeros(b01,split_index,split_index);
	initWithZeros(b10,split_index,split_index);
	initWithZeros(b11,split_index,split_index);


	for (int i = 0; i < split_index; i++){
		for (int j = 0; j < split_index; j++) {
		a00[i][j] = matrix_A[i][j];
		a01[i][j] = matrix_A[i][j + split_index];
		a10[i][j] = matrix_A[split_index + i][j];
		a11[i][j] = matrix_A[i + split_index][j + split_index];
		b00[i][j] = matrix_B[i][j];
		b01[i][j] = matrix_B[i][j + split_index];
		b10[i][j] = matrix_B[split_index + i][j];
		b11[i][j] = matrix_B[i + split_index][j + split_index];
		}
	}

	add_matrix(multiply_matrix(a00, b00),multiply_matrix(a01, b10),result_matrix_00, split_index);
	add_matrix(multiply_matrix(a00, b01),multiply_matrix(a01, b11),result_matrix_01, split_index);
	add_matrix(multiply_matrix(a10, b00),multiply_matrix(a11, b10),result_matrix_10, split_index);
	add_matrix(multiply_matrix(a10, b01),multiply_matrix(a11, b11),result_matrix_11, split_index);

	for (int i = 0; i < split_index; i++){
		for (int j = 0; j < split_index; j++) {
		result_matrix[i][j] = result_matrix_00[i][j];
		result_matrix[i][j + split_index] = result_matrix_01[i][j];
		result_matrix[split_index + i][j] = result_matrix_10[i][j];
		result_matrix[i + split_index] [j + split_index] = result_matrix_11[i][j];
		}
	}
	}
	return result_matrix;
}

public static void main(String[] args)
{
	double[][] matrix_A = {{1, 2}, {3, 4}};
	double[][] matrix_B = {{5, 6}, {7, 8}};
	double[][] result_matrix = multiply_matrix(matrix_A, matrix_B);
	for (int i = 0; i < result_matrix.length; i++){
	for (int j = 0; j < result_matrix[0].length; j++){
		System.out.print(result_matrix[i][j] + " ");
	}
	System.out.println();
	}
}

}
// Time Complexity: O(n^3)
//This code is contributed by shruti456rawal

