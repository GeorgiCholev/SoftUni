package ExamPreparation_10.April_13th_2022;

import java.util.*;

public class p02_Armory {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        char[][] army = new char[n][n];

        Figure armyOfficer = new Figure();
        Figure mirrorOne = new Figure();
        Figure mirrorTwo = new Figure();
        int mirrorCounter = 1;

        for (int i = 0; i < n; i++) {
            char[] row = scanner.nextLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (row[j] == 'A') {
                    armyOfficer = new Figure(i, j);
                } else if (row[j] == 'M' && mirrorCounter == 1) {
                    mirrorCounter++;
                    mirrorOne = new Figure(i, j);
                } else if (row[j] == 'M' && mirrorCounter == 2) {
                    mirrorTwo = new Figure(i, j);
                }
                army[i][j] = row[j];
            }
        }

        int goldAcquired = 0;
        while (isInside(n, armyOfficer, goldAcquired)) {

            String command = scanner.nextLine();
            switch (command) {
                case "left":
                    goldAcquired = moveLeft(army, armyOfficer, mirrorOne, mirrorTwo, goldAcquired);
                    break;
                case "right":
                    goldAcquired = moveRight(n, army, armyOfficer, mirrorOne, mirrorTwo, goldAcquired);
                    break;
                case "up":
                    goldAcquired = moveUp(army, armyOfficer, mirrorOne, mirrorTwo, goldAcquired);
                    break;
                case "down":
                    goldAcquired = moveDown(n, army, armyOfficer, mirrorOne, mirrorTwo, goldAcquired);
                    break;
            }
        }
        if (goldAcquired < 65) {
            System.out.println("I do not need more swords!");
        } else {
            System.out.println("Very nice swords, I will come back for more!");
        }

        System.out.println("The king paid " + goldAcquired + " gold coins.");

        printMatrix(army);
    }

    private static void printMatrix(char[][] army) {
        for (char[] row : army) {
            System.out.println(String.valueOf(row));
        }
    }

    private static int moveLeft(char[][] army, Figure armyOfficer, Figure mirrorOne, Figure mirrorTwo, int goldAcquired) {
        int column = armyOfficer.getColumn();
        int row = armyOfficer.getRow();
        army[row][column] = '-';

        armyOfficer.setColumn(column - 1);
        column = armyOfficer.getColumn();
        if (column >= 0) {

            goldAcquired = acquireGold(army, armyOfficer, mirrorOne, mirrorTwo, goldAcquired, column, row);
        }
        return goldAcquired;
    }

    private static int moveRight(int n, char[][] army, Figure armyOfficer, Figure mirrorOne, Figure mirrorTwo, int goldAcquired) {
        int column = armyOfficer.getColumn();
        int row = armyOfficer.getRow();
        army[row][column] = '-';

        armyOfficer.setColumn(column + 1);
        column = armyOfficer.getColumn();
        if (column < n) {

            goldAcquired = acquireGold(army, armyOfficer, mirrorOne, mirrorTwo, goldAcquired, column, row);
        }
        return goldAcquired;
    }

    private static int moveDown(int n, char[][] army, Figure armyOfficer, Figure mirrorOne, Figure mirrorTwo, int goldAcquired) {
        int column = armyOfficer.getColumn();
        int row = armyOfficer.getRow();
        army[row][column] = '-';

        armyOfficer.setRow(row + 1);
        row = armyOfficer.getRow();
        if (row < n) {

            goldAcquired = acquireGold(army, armyOfficer, mirrorOne, mirrorTwo, goldAcquired, column, row);
        }
        return goldAcquired;
    }

    private static int moveUp(char[][] army, Figure armyOfficer, Figure mirrorOne, Figure mirrorTwo, int goldAcquired) {
        int column = armyOfficer.getColumn();
        int row = armyOfficer.getRow();
        army[row][column] = '-';

        armyOfficer.setRow(row - 1);
        row = armyOfficer.getRow();
        if (row >= 0) {
            goldAcquired = acquireGold(army, armyOfficer, mirrorOne, mirrorTwo, goldAcquired, column, row);
        }
        return goldAcquired;
    }

    private static int acquireGold(char[][] army, Figure armyOfficer, Figure mirrorOne, Figure mirrorTwo, int goldAcquired, int column, int row) {
        if (army[row][column] == 'M') {
            if (mirrorOne.getRow() == row && mirrorOne.getColumn() == column) {
                army[row][column] = '-';
                row = mirrorTwo.getRow();
                column = mirrorTwo.getColumn();
                armyOfficer.setRow(row);
                armyOfficer.setColumn(column);
                army[row][column] = 'A';
            } else {
                army[row][column] = '-';
                row = mirrorOne.getRow();
                column = mirrorOne.getColumn();
                army[row][column] = 'A';
            }

        } else if (army[row][column] > 47 && army[row][column] <= 57) {
            goldAcquired += (army[row][column] - '0');
            army[row][column] = 'A';
        } else {
            army[row][column] = 'A';
        }
        return goldAcquired;
    }


    private static boolean isInside(int n, Figure figure, int goldAcquired) {
        return figure.getColumn() >= 0 && figure.getColumn() < n
                && figure.getRow() >= 0 && figure.getRow() < n
                && goldAcquired < 65;
    }


    static class Figure {
        int row;
        int column;

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getColumn() {
            return column;
        }

        public Figure() {
            this(-1, -1);
        }

        public Figure(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
