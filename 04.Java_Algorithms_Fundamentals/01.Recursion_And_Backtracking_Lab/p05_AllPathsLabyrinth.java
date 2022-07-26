package _1_RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p05_AllPathsLabyrinth {

    private final static List<Character> directions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        scanner.nextLine();

        char[][] labyrinth = new char[row][col];
        fillMatrix(labyrinth, scanner);

        move(labyrinth, 0, 0, 'S');
    }

    private static void move(char[][] labyrinth, int row, int column, char step) {

        if (!isInBounds(labyrinth, row, column)) {
            return;
        } else if (isExit(labyrinth, row, column)) {
            directions.add(step);
            printDirections();
            directions.remove(directions.size() - 1);
            return;
        }

        labyrinth[row][column] = 'V';
        directions.add(step);

        move(labyrinth, row, column + 1, 'R');
        move(labyrinth, row + 1, column, 'D');
        move(labyrinth, row, column - 1, 'L');
        move(labyrinth, row - 1, column, 'U');

        labyrinth[row][column] = '-';
        directions.remove(directions.size() - 1);
    }

    private static boolean isPassable(char[][] labyrinth, int row, int column) {
        return labyrinth[row][column] != 'V' && labyrinth[row][column] != '*';
    }

    private static boolean isExit(char[][] labyrinth, int row, int column) {
        return labyrinth[row][column] == 'e';
    }

    private static boolean isInBounds(char[][] labyrinth, int row, int column) {
        return row >= 0 && row < labyrinth.length && column >= 0 && column < labyrinth[row].length
                && isPassable(labyrinth, row, column);
    }

    private static void printDirections() {
        for (int i = 1; i < directions.size(); i++) {
            System.out.print(directions.get(i));
        }
        System.out.println();
    }


    private static void fillMatrix(char[][] labyrinth, Scanner scanner) {
        for (int row = 0; row < labyrinth.length; row++) {
            labyrinth[row] = scanner.nextLine().toCharArray();
        }
    }
}
