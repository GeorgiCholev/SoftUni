package MultidimensionalArrays_02.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class p05_2x2SubMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = readMatrix(scanner);

       SubMatrix matrixAndSum = best2x2SubMatrixSum(matrix);

        System.out.println(matrixAndSum.toString());
    }

    private static SubMatrix best2x2SubMatrixSum(int[][] matrix) {
        SubMatrix subMatrixObject = new SubMatrix();
        int[][] subMatrix = new int[2][2];
        int biggestSum = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                int sum = matrix[i][j] + matrix[i + 1][j] + matrix[i][j + 1] + matrix[i + 1][j + 1];
                if (sum > biggestSum) {
                    biggestSum = sum;
                    int[] arr1 = new int[]{matrix[i][j], matrix[i + 1][j]};
                    int[] arr2 = new int[]{matrix[i][j + 1], matrix[i + 1][j + 1]};
                    subMatrix[0] = arr1;
                    subMatrix[1] = arr2;
                    subMatrixObject = new SubMatrix(subMatrix, biggestSum);
                }
            }
        }
        return subMatrixObject;
    }

    private static int[][] readMatrix(Scanner scanner) {
        int[] quantity = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int rows = quantity[0];
        int cols = quantity[1];
        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split(",\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }
        return matrix;
    }

    static class SubMatrix {
        public SubMatrix() {
        }

        int[][] matrix;
        int sumOfElements;

        public SubMatrix(int[][] matrix, int sumOfElements) {
            this.matrix = matrix;
            this.sumOfElements = sumOfElements;
        }

        @Override
        public String toString() {
            return this.matrix[0][0] + " " + this.matrix[1][0] + System.lineSeparator() +
                    this.matrix[0][1] + " " + this.matrix[1][1] + System.lineSeparator() +
                    this.sumOfElements;
        }
    }
}
