package ExamPreparation_10.June_26th_2021;

import java.util.Scanner;

public class p02_Python {

    public static int foodCount = 0;
    public static int pythonRow;
    public static int pythonColumn;
    public static int pythonLength = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixSize = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(", ");
        char[][] matrix = fillMatrix(scanner, matrixSize);

        for (String command : commands) {
            boolean isAlive = true;
            switch (command) {
                case "up":
                    isAlive = move(-1, 0, matrix);
                    break;
                case "down":
                    isAlive = move(1, 0, matrix);
                    break;
                case "left":
                    isAlive = move(0, -1, matrix);
                    break;
                case "right":
                    isAlive = move(0, 1, matrix);
                    break;
            }
            if (!isAlive) {
                System.out.println("You lose! Killed by an enemy!");
                return;
            }
            if (foodCount == 0) {
                System.out.println("You win! Final python length is " + pythonLength);
                return;
            }
        }

            System.out.println("You lose! There is still " + foodCount + " food to be eaten.");
    }

    private static boolean move(int rowChange, int colChange, char[][] matrix) {

        int newRow = calculatePosition(rowChange + pythonRow, matrix);
        int newCol = calculatePosition(colChange + pythonColumn, matrix);

        matrix[pythonRow][pythonColumn] = '*';
        if (matrix[newRow][newCol] == 'e') {
            return false;
        } else if (matrix[newRow][newCol] == 'f') {
            pythonLength++;
            foodCount--;
        }
        pythonRow = newRow;
        pythonColumn = newCol;
        matrix[pythonRow][pythonColumn] = 'p';
        return true;
    }

    private static int calculatePosition(int position, char[][] matrix) {
        if (position < 0) {
            return matrix.length - 1;
        } else if (position > matrix.length - 1) {
            return 0;
        }
        return position;
    }

    private static char[][] fillMatrix(Scanner scanner, int matrixSize) {
        char[][] matrix = new char[matrixSize][matrixSize];
        for (int row = 0; row < matrixSize; row++) {
            String[] fieldsInString = scanner.nextLine().split(" ");
            for (int col = 0; col < matrixSize; col++) {
                char field = fieldsInString[col].charAt(0);
                if (field == 's') {
                    pythonRow = row;
                    pythonColumn = col;
                } else if (field == 'f') {
                    foodCount++;
                }
                matrix[row][col] = field;
            }
        }
        return matrix;
    }
}
