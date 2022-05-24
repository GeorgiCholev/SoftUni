package MultidimensionalArrays_02.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class p02_Position {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int[][] matrix = readMatrix(scanner);

        int number = Integer.parseInt(scanner.nextLine());

        findNumberInMatrix(matrix, number);

    }

    private static void findNumberInMatrix(int[][] matrix, int number) {
        boolean isPresent = false;
        for (int row = 0; row < matrix.length; row++) {
            int[] arr = matrix[row];
            for (int col = 0; col < arr.length; col++) {
                if (arr[col] == number) {
                    isPresent = true;
                    System.out.println(row + " " + col);
                }
            }
        }
        if (!isPresent) {
            System.out.println("not found");
        }
    }

    private static int[][] readMatrix(Scanner scanner) {
        int[] quantity = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
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
