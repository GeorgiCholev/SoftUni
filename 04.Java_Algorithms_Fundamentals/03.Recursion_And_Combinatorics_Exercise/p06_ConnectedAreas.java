package _3_Exercise_RecursionAndCombinatorics;

import java.util.*;
import java.util.stream.Collectors;

public class p06_ConnectedAreas {
    public static char[][] matrix;
    public static int instanceAreaSize = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();
        matrix = new char[rows][cols];
        fillMatrix(scanner);

        List<ConnectedArea> areas = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '-') {
                    ConnectedArea area = new ConnectedArea(i, j);
                    findConnectedArea(i, j);
                    area.setSize(instanceAreaSize);
                    areas.add(area);
                    instanceAreaSize = 0;
                }
            }
        }

        System.out.println("Total areas found: " + areas.size());
        areas = areas.stream().sorted((a1, a2) -> Integer.compare(a2.getSize(), a1.getSize()))
                .collect(Collectors.toList());
        int counter = 1;
        for (ConnectedArea area : areas) {
            System.out.println("Area #" + (counter++) + " at " + area.toString());
        }
    }

    private static void findConnectedArea(int row, int col) {
        if (isOutOfBounds(row, col) || isNotPassable(row, col)) {
            return;
        }
        matrix[row][col] = 'V';
        instanceAreaSize++;
        findConnectedArea(row, col + 1);
        findConnectedArea(row + 1, col);
        findConnectedArea(row, col - 1);
        findConnectedArea(row - 1, col);
    }

    private static boolean isNotPassable(int row, int col) {
        return matrix[row][col] == 'V' || matrix[row][col] == '*';
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }


    static class ConnectedArea {
        private final int startRow;
        private final int startCol;
        private int size;

        public ConnectedArea(int startRow, int startCol) {
            this.startRow = startRow;
            this.startCol = startCol;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }


        @Override
        public String toString() {
            return "(" + this.startRow + ", " + this.startCol + "), size: " + this.size;
        }
    }

    private static void fillMatrix(Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().toCharArray();
        }
    }
}
