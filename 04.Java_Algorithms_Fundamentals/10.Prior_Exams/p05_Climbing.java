package _10_Prior_Exams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class p05_Climbing {

//    Roi has to climb a building (N x M matrix with positive integers) from bottom right to top left.
//    However, his path must be the path with the highest possible sum. Also, he can move only up or left.

    private static int[][] matrix;
    private static int[][] maxSumMatrix;
    private static final ArrayDeque<Integer> bestPath = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());

        readMatrix(rows, cols, reader);
        fillMaxSumMatrix(rows, cols);

        traverseBack(0, 0);

        StringBuilder output = new StringBuilder(maxSumMatrix[0][0] + System.lineSeparator());

        while (!bestPath.isEmpty()) {
            output.append(bestPath.pop()).append(" ");
        }

        System.out.println(output);
    }

    private static void traverseBack(int row, int col) {
        if (finish(row, col)) {
            return;
        }

        int currentPlot = matrix[row][col];
        bestPath.push(currentPlot);

        int maxSumToCurrentPlot = maxSumMatrix[row][col];

        boolean isHeadingDown = false;

        if (isInBounds(row + 1, col)) {
             isHeadingDown = maxSumToCurrentPlot - currentPlot == maxSumMatrix[row + 1][col];
        }

        if (isHeadingDown) {
            traverseBack(row + 1, col);
        } else {
            traverseBack(row, col + 1);
        }
    }

    private static void fillMaxSumMatrix(int rows, int cols) {

        maxSumMatrix = new int[rows][cols];

        int row = rows - 1;
        int colToFill = cols - 1;
        maxSumMatrix[row][colToFill] = matrix[row][colToFill];

        row--;
        for (; row >= 0; row--) {
            maxSumMatrix[row][colToFill] = maxSumMatrix[row + 1][colToFill] + matrix[row][colToFill];
        }

        int rowToFill = rows - 1;
        int col = cols - 2;
        for (; col >= 0; col--) {
            maxSumMatrix[rowToFill][col] = maxSumMatrix[rowToFill][col + 1] + matrix[rowToFill][col];
        }

        for (row = rows - 2; row >= 0; row--) {
            for (col = cols - 2; col >= 0; col--) {
                int plot = matrix[row][col];
                int bestPathSum = Math.max(maxSumMatrix[row + 1][col], maxSumMatrix[row][col + 1]);
                maxSumMatrix[row][col] = plot + bestPathSum;
            }
        }

    }

    private static void readMatrix(int rows, int cols, BufferedReader reader) throws IOException {

        matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            int[] row = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            matrix[i] = row;
        }

    }

    private static boolean finish(int row, int col) {
        return row == matrix.length || col == matrix[row].length;
    }

    private static boolean isInBounds(int row, int column) {
        return row >= 0 && row < matrix.length && column >= 0 && column < matrix[row].length;
    }
}
