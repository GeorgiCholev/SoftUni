package _6_Exercise_Graph;

import java.util.*;
import java.util.stream.Collectors;

public class p02_AreasInMatrix {

    static char[][] matrix;
    static boolean[][] visited;
    static Map<Character, Integer> numberOfAreasForSymbol = new TreeMap<>();
    static int totalAreas = 0;

    public static void main(String[] args) {

        fillMatrixFromConsole();

        findAllAreas();

        String lettersWithCount = numberOfAreasForSymbol.entrySet().stream()
                .map(e -> "Letter '" + e.getKey() + "' -> " + e.getValue())
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println("Areas: " + totalAreas);

        System.out.print(lettersWithCount);
    }

    private static void findAllAreas() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!visited[i][j]) {
                    totalAreas++;
                    char symbol = matrix[i][j];
                    numberOfAreasForSymbol.putIfAbsent(symbol, 0);
                    numberOfAreasForSymbol.put(symbol, numberOfAreasForSymbol.get(symbol) + 1);
                    traverseMatrix(i, j, symbol);
                }
            }
        }
    }

    private static void traverseMatrix(int row, int col, char symbol) {
        if (isOutBounds(row, col) || visited[row][col] || matrix[row][col] != symbol) {
            return;
        }
        visited[row][col] = true;

        traverseMatrix(row - 1, col, symbol);
        traverseMatrix(row + 1, col, symbol);
        traverseMatrix(row, col - 1, symbol);
        traverseMatrix(row, col + 1, symbol);
    }

    private static boolean isOutBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }

    static void fillMatrixFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        matrix = new char[rows][];
        visited = new boolean[rows][];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
            visited[i] = new boolean[matrix[i].length];
        }
    }

}
