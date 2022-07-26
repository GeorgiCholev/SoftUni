package MultidimensionalArrays_02.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class p01_CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int[][] matrixOne = readMatrix(scanner);

        int[][] matrixTwo = readMatrix(scanner);

        System.out.println(compareMatrices(matrixOne, matrixTwo) ? "equal" : "not equal");

    }

    private static boolean compareMatrices(int[][] matrixOne, int[][] matrixTwo) {
        if (matrixOne.length != matrixTwo.length) {
            return false;
        }
        for (int row = 0; row < matrixOne.length; row++) {
            int[] arr1 = matrixOne[row];
            int[] arr2 = matrixTwo[row];
            if (arr1.length != arr2.length) {
                return false;
            }
            for (int col = 0; col < arr1.length; col++) {
                if (arr1[col] != arr2[col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] readMatrix(Scanner scanner) {
        int[] quantity = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = quantity[0];
        int cols = quantity[1];
        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }
        return matrix;
    }
}
