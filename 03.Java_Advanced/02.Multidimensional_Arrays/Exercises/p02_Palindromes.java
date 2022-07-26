package MultidimensionalArrays_02.Exercise;

import java.util.Scanner;

public class p02_Palindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
                char outside = (char) ('a' + row);
            for (int col = 0; col < cols; col++) {
                char middle = (char) (outside + col);
                matrix[row][col] = String.valueOf(outside) + middle + outside;
            }
        }
        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] palindromes : matrix) {
            for (int col = 0; col < palindromes.length; col++) {
                System.out.print(palindromes[col] + " ");
            }
            System.out.println();
        }
    }
}
