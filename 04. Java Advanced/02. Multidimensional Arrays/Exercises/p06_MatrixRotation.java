package MultidimensionalArrays_02.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p06_MatrixRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int degrees = findDegrees(scanner);

        char[][] matrix = createCharMatrix(scanner);

        int rotations = (degrees / 90) % 4;

        switch (rotations) {
            case 1:
                matrix = rotation(matrix);
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    matrix = rotation(matrix);
                }
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    matrix = rotation(matrix);
                }
                break;
        }
        printCharMatrix(matrix);
    }

    private static char[][] rotation(char[][] matrix) {
        int rows = matrix[0].length;
        int cols = matrix.length;
        char[][] rotatedMatrix = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rotatedMatrix[row][col] = matrix[matrix.length - 1 - col][row];
            }
        }
        return rotatedMatrix;
    }

    private static void printCharMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(String.valueOf(row));
        }
    }

    private static char[][] createCharMatrix(Scanner scanner) {
        List<String> matrixRows = new ArrayList<>();
        String incomingRow = scanner.nextLine();
        int largestRow = Integer.MIN_VALUE;
        while (!incomingRow.equals("END")) {
            matrixRows.add(incomingRow);
            if (incomingRow.length() > largestRow) {
                largestRow = incomingRow.length();
            }

            incomingRow = scanner.nextLine();
        }
        return fillMatrix(matrixRows, largestRow);

    }

    private static char[][] fillMatrix(List<String> matrixRows, int largestRow) {
        int rows = matrixRows.size();
        char[][] matrix = new char[rows][largestRow];
        for (int row = 0; row < rows; row++) {
            String wordAtRow = matrixRows.get(row);
            for (int col = 0; col < largestRow; col++) {
                if (col < wordAtRow.length()) {
                    matrix[row][col] = wordAtRow.charAt(col);
                } else {
                    matrix[row][col] = ' ';
                }
            }
        }
        return matrix;
    }

    private static int findDegrees(Scanner scanner) {
        String command = scanner.nextLine();
        int indexStart = command.indexOf('(');
        int indexEnd = command.indexOf(')');
        return Integer.parseInt(command.substring(indexStart + 1, indexEnd));
    }
}
