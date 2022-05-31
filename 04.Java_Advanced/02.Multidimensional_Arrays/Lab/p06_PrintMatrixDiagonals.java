package MultidimensionalArrays_02.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class p06_PrintMatrixDiagonals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int[][] squaredMatrix = readMatrix(scanner);

        int[] diagonalOne = new int[squaredMatrix.length];
        int[] diagonalTwo = new int[squaredMatrix.length];

        fillDiagonals(squaredMatrix, diagonalOne, diagonalTwo);

        printArray(diagonalOne);
        printArray(diagonalTwo);
    }

    private static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println();
    }

    private static void fillDiagonals(int[][] squaredMatrix, int[] diagonalOne, int[] diagonalTwo) {
        for (int i = 0; i < squaredMatrix.length; i++) {
            diagonalOne[i] = squaredMatrix[i][i];
            diagonalTwo[i] = squaredMatrix[squaredMatrix.length - 1 - i][i];
        }
    }

    private static int[][] readMatrix(Scanner scanner) {
        int quantity = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[quantity][quantity];
        for (int row = 0; row < quantity; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }
        return matrix;
    }
}
