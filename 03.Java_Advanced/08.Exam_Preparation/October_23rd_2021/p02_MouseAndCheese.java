package ExamPreparation_10.October_23rd_2021;

import java.util.Scanner;

public class p02_MouseAndCheese {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixSize = Integer.parseInt(scanner.nextLine());
        char[][] territory = new char[matrixSize][matrixSize];

        int[] mouseInTerritory = locateMouseInTerritory(territory, scanner);
        int mouseRow = mouseInTerritory[0];
        int mouseColumn = mouseInTerritory[1];

        int eatenCheese = 0;
        String direction = scanner.nextLine();
        while (!direction.equals("end")) {

            if (direction.equals("up")) {
                int oldRow = mouseRow;
                mouseRow--;
                territory[oldRow][mouseColumn] = '-';
                if (isOutsideTerritory(matrixSize, mouseRow, mouseColumn)) {
                    break;
                }
                if (territory[mouseRow][mouseColumn] == 'B') {
                    direction = "up";
                    territory[mouseRow][mouseColumn] = 'M';
                    continue;
                } else if (territory[mouseRow][mouseColumn] == 'c') {
                    eatenCheese++;
                }
            } else if (direction.equals("down")) {
                int oldRow = mouseRow;
                mouseRow++;
                territory[oldRow][mouseColumn] = '-';
                if (isOutsideTerritory(matrixSize, mouseRow, mouseColumn)) {
                    break;
                }
                if (territory[mouseRow][mouseColumn] == 'B') {
                    direction = "down";
                    territory[mouseRow][mouseColumn] = 'M';
                    continue;
                } else if (territory[mouseRow][mouseColumn] == 'c') {
                    eatenCheese++;
                }
            } else if (direction.equals("left")) {
                int oldColumn = mouseColumn;
                mouseColumn--;
                territory[mouseRow][oldColumn] = '-';
                if (isOutsideTerritory(matrixSize, mouseRow, mouseColumn)) {
                    break;
                }
                if (territory[mouseRow][mouseColumn] == 'B') {
                    direction = "left";
                    territory[mouseRow][mouseColumn] = 'M';
                    continue;
                } else if (territory[mouseRow][mouseColumn] == 'c') {
                    eatenCheese++;
                }
            } else if (direction.equals("right")) {
                int oldColumn = mouseColumn;
                mouseColumn++;
                territory[mouseRow][oldColumn] = '-';
                if (isOutsideTerritory(matrixSize, mouseRow, mouseColumn)) {
                    break;
                }
                if (territory[mouseRow][mouseColumn] == 'B') {
                    direction = "right";
                    territory[mouseRow][mouseColumn] = 'M';
                    continue;
                } else if (territory[mouseRow][mouseColumn] == 'c') {
                    eatenCheese++;
                }
            }

            territory[mouseRow][mouseColumn] = 'M';
            direction = scanner.nextLine();
        }

        if (isOutsideTerritory(matrixSize, mouseRow, mouseColumn)) {
            System.out.println("Where is the mouse?");
        }

        if (eatenCheese >= 5) {
            System.out.println("Great job, the mouse is fed " + eatenCheese + " cheeses!");
        } else {
            System.out.println("The mouse couldn't eat the cheeses, she needed "  + (5 - eatenCheese) + " cheeses more.");
        }

        printMatrix(territory);
    }

    private static void printMatrix(char[][] territory) {
        for (char[] row : territory) {
            System.out.println(String.valueOf(row));
        }
    }

    private static boolean isOutsideTerritory(int matrixSize, int mouseRow, int mouseColumn) {
        return mouseRow < 0 || mouseRow >= matrixSize || mouseColumn < 0 || mouseColumn >= matrixSize;
    }

    private static int[] locateMouseInTerritory(char[][] territory, Scanner scanner) {
        int mouseRow = -1;
        int mouseColumn = -1;
        for (int i = 0; i < territory.length; i++) {
            char[] row = scanner.nextLine().toCharArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 'M') {
                    mouseRow = i;
                    mouseColumn = j;
                }
                territory[i][j] = row[j];
            }
        }
        return new int[]{mouseRow, mouseColumn};
    }
}
