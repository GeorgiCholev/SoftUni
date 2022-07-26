package MultidimensionalArrays_02.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class p05_MatrixShuffling {
    public static void main(String[] args) {

        Scanner scanner = new Scanner (System.in);
        String[][] matrix = readStringMatrix(scanner);

        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String[] components = input.split("\\s+");

            if (!isValidCommand(components, matrix)) {
                System.out.println("Invalid input!");
                input = scanner.nextLine();
                continue;
            }
            shuffleMatrixElements(components, matrix);
            printMatrix(matrix);

            input = scanner.nextLine();
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    private static void shuffleMatrixElements(String[] components, String[][] matrix) {

        int rowOne = Integer.parseInt(components[1]);
        int colOne = Integer.parseInt(components[2]);
        int rowTwo = Integer.parseInt(components[3]);
        int colTwo = Integer.parseInt(components[4]);

        String firstElementToMove = matrix[rowOne][colOne];
        String secondElementToMove = matrix[rowTwo][colTwo];
        matrix[rowOne][colOne] = secondElementToMove;
        matrix[rowTwo][colTwo] = firstElementToMove;
    }

    private static boolean isValidCommand(String[] components, String[][] matrix) {
        boolean isValidCommand = false;
        if (components.length == 5 && components[0].equals("swap")){
            int rowOne = Integer.parseInt(components[1]);
            int colOne = Integer.parseInt(components[2]);
            int rowTwo = Integer.parseInt(components[3]);
            int colTwo = Integer.parseInt(components[4]);

            if (rowOne < matrix.length && colOne < matrix[rowOne].length
            && rowTwo < matrix.length && colTwo < matrix[rowTwo].length) {
                isValidCommand = true;
            }
        }
        return isValidCommand;
    }

    private static String[][] readStringMatrix(Scanner scanner) {
        int[] size = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int rows = size[0];
        int cols = size[1];
        String[][] matrix = new String[rows][cols];
        for (int row = 0; row < rows; row++) {
            String[] nextRow = scanner.nextLine().split(" ");
            matrix[row] = nextRow;
        }
        return matrix;
    }
}
