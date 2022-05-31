package MultidimensionalArrays_02.Exercise;

import java.util.Scanner;

public class p01_FillMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] properties = scanner.nextLine().split(",\\s+");
        int quantity = Integer.parseInt(properties[0]);
        int[][] matrix = new int[quantity][quantity];
        char pattern = properties[1].charAt(0);
        switch (pattern) {
            case 'A':
                fillMatrixPatternA(quantity, matrix);
                break;
            case 'B':
                fillMatrixPatternB(quantity, matrix);
                break;
        }
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void fillMatrixPatternB(int quantity, int[][] matrix) {
        int counter = 1;
        for (int col = 0; col < quantity; col++) {
            if (col % 2 == 0) {
                for (int row = 0; row < quantity; row++) {
                    matrix[row][col] = counter;
                    counter++;
                }
            } else {
                for (int row = quantity - 1; row >= 0; row--) {
                    matrix[row][col] = counter;
                    counter++;
                }
            }
        }
    }

    private static void fillMatrixPatternA(int quantity, int[][] matrix) {
        int counter = 1;
        for (int col = 0; col < quantity; col++) {
            for (int row = 0; row < quantity; row++) {
                matrix[row][col] = counter;
                counter++;
            }
        }
    }
}
