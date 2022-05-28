package MultidimensionalArrays_02.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class p03_DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int[][] squareMatrix = fillSquaredMatrix(scanner);
        System.out.println(differenceOfDiagonals(squareMatrix));
    }

    private static int differenceOfDiagonals(int[][] matrix) {
        int size = matrix.length;
        int primaryDiagonalSum = 0;
        int secondaryDiagonalSum = 0;
        for (int i = 0; i < size; i++) {
            primaryDiagonalSum += matrix[i][i];
            secondaryDiagonalSum += matrix[i][size - 1 - i];
        }
        return  Math.abs(primaryDiagonalSum - secondaryDiagonalSum);
    }

    private static int[][] fillSquaredMatrix(Scanner scanner) {
        int size = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[size][size];
        for (int row = 0; row < size; row++) {
            matrix[row] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}
