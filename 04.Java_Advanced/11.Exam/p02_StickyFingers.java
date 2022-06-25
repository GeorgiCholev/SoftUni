import java.util.Scanner;

public class p02_StickyFingers {

    public static int burglarRow;
    public static int burglarCol;
    private static int stolenMoney;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixSize = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");
        char[][] town = fillMatrix(scanner, matrixSize);

        boolean isCaught = false;
        for (String command : commands) {
            switch (command) {
                case "up":
                    isCaught = move(-1, 0, town);
                    break;
                case "down":
                    isCaught = move(1, 0, town);
                    break;
                case "left":
                    isCaught = move(0, -1, town);
                    break;
                case "right":
                    isCaught = move(0, 1, town);
                    break;
            }
            if (isCaught) {
                printMatrix(town);
                return;
            }
        }
        System.out.println("Your last theft has finished successfully with " + stolenMoney + "$ in your pocket.");
        printMatrix(town);
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                if (i == row.length - 1) {
                    System.out.println(row[i]);
                } else {
                    System.out.print(row[i] + " ");
                }
            }
        }
    }

    private static boolean move(int rowChange, int colChange, char[][] matrix) {
        int newRow = burglarRow + rowChange;
        int newCol = burglarCol + colChange;

        if (!isInBounds(newRow, newCol, matrix)) {
            System.out.println("You cannot leave the town, there is police outside!");
            return false;
        }
        matrix[burglarRow][burglarCol] = '+';

        if (matrix[newRow][newCol] == 'P') {
            matrix[newRow][newCol] = '#';
            System.out.println("You got caught with " + stolenMoney + "$, and you are going to jail.");
            return true;
        }

        burglarRow = newRow;
        burglarCol = newCol;

        if (matrix[newRow][newCol] == '$') {

            int newHit = newRow * newCol;
            System.out.println("You successfully stole " + newHit + "$.");
            stolenMoney += newHit;

        }
        matrix[burglarRow][burglarCol] = 'D';
        return false;

    }

    private static boolean isInBounds(int newRow, int newCol, char[][] matrix) {
        return newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix.length;
    }

    private static char[][] fillMatrix(Scanner scanner, int matrixSize) {
        char[][] matrix = new char[matrixSize][matrixSize];
        for (int row = 0; row < matrixSize; row++) {
            String[] thisRow = scanner.nextLine().split(" ");
            for (int col = 0; col < matrixSize; col++) {
                char field = thisRow[col].charAt(0);
                if (field == 'D') {
                    burglarRow = row;
                    burglarCol = col;
                    stolenMoney = 0;
                }
                matrix[row][col] = field;
            }
        }
        return matrix;
    }
}
