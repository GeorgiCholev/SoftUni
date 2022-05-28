package MultidimensionalArrays_02.Exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class p04_MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = fillMatrix(scanner);

        findMaximalSum(matrix);
    }

    private static void findMaximalSum(int[][] matrix) {
        int probabilitiesForRows = matrix.length - 2;
        int probabilitiesForCols = matrix[0].length - 2;
        int maxSum = Integer.MIN_VALUE;
        int[][] maxMatrix = new int[3][3];

        for (int i = 0; i < probabilitiesForRows; i++) {
            for (int j = 0; j < probabilitiesForCols; j++) {
                Map<Integer, int[][]> matrixAndSum = iterateTheMatrixForSum(matrix, i, j);
                for (var e : matrixAndSum.entrySet()) {
                    if (e.getKey() > maxSum) {
                        maxSum = e.getKey();
                        maxMatrix = e.getValue();
                    }
                }
            }
        }
        printOutput(maxSum, maxMatrix);

    }

    private static void printOutput(int sum, int[][] matrix) {
        System.out.println("Sum = " + sum);
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    private static Map<Integer, int[][]> iterateTheMatrixForSum(int[][] matrix, int i, int j) {
        Map<Integer, int[][]> matrixAndSum = new HashMap<>();
        int[][] matrixCandidate = new int[3][3];
        int sum = 0;
        for(int row = i; row < 3 + i; row++) {
            for(int col = j; col < 3 + j; col++) {
                matrixCandidate[row - i][col - j] = matrix[row][col];
                sum += matrixCandidate[row - i][col - j];
            }
        }
        matrixAndSum.put(sum, matrixCandidate);
        return matrixAndSum;
    }

    private static int[][] fillMatrix(Scanner scanner) {
        int[] size = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int rows = size[0];
        int cols = size[1];
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}
