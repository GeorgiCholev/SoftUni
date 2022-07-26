package _10_Prior_Exams;

import java.util.Scanner;

public class p01_TBC {

//    Given a matrix (city map) of 't's (tunnel) and 'd's (dirt),
//    count the number of connected tunnels in the city.
//    A connected tunnel is surrounded by dirt
//    and is formed by connecting adjacent 't's horizontally, vertically or diagonally.

    private static char[][] field;
    private static boolean[][] isVisited;
    private static int numberOfTunnels = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        fillMatrix(scanner);

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {

                if (field[row][col] == 't' && !isVisited[row][col]) {
                    findTunnel(row, col);
                    numberOfTunnels++;
                }

            }
        }

        System.out.println(numberOfTunnels);
    }

    private static void findTunnel(int row, int col) {

        if (!isInBounds(row, col) || !isTunnel(row,col) || isVisited[row][col]){
            return;
        }

        char plot = field[row][col];
        isVisited[row][col] = true;

        findTunnel(row, col - 1);
        findTunnel(row - 1, col - 1);
        findTunnel(row - 1, col);
        findTunnel(row - 1, col + 1);
        findTunnel(row, col + 1);
        findTunnel(row + 1, col + 1);
        findTunnel(row + 1, col);
        findTunnel(row + 1, col - 1);

    }

    private static boolean isTunnel(int row, int col) {
        return field[row][col] == 't';
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < field.length && col >= 0 && col < field[row].length;
    }

    private static char[][] fillMatrix(Scanner scanner) {
        int rows = Integer.parseInt(scanner.nextLine());
        int columns = Integer.parseInt(scanner.nextLine());

        field = new char[rows][columns];
        isVisited = new boolean[rows][columns];

        for (int row = 0; row < rows; row++) {
            String line = scanner.nextLine();
            field[row] = line.toCharArray();
        }
        return field;
    }
}
