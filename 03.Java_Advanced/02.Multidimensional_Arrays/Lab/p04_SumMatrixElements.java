package MultidimensionalArrays_02.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class p04_SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        Matrix matrix = readMatrix(scanner);

        System.out.print(matrix);
    }

    private static Matrix readMatrix(Scanner scanner) {
        int[] quantity = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int rows = quantity[0];
        int columns = quantity[1];
        int[][] matrix = new int[rows][columns];
        for (int row = 0; row < matrix.length; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split(",\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }
        return new Matrix(matrix, rows, columns);
    }

    static class Matrix {
        int rows;
        int columns;
        int[][] matrix;
        int sumOfElements;


        public Matrix(int[][] matrix, int rows, int columns) {
            this.matrix = matrix;
            this.rows = rows;
            this.columns = columns;
            this.sumOfElements = sum(this.matrix);
        }

        private int sum(int[][] matrix) {
            int sum = 0;
            for (int[] array : matrix) {
                for (int element : array) {
                    sum += element;
                }
            }
            return sum;
        }

        @Override
        public String toString() {
            return this.rows + System.lineSeparator() + this.columns + System.lineSeparator() + this.sumOfElements;
        }
    }
}
