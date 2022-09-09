package _6_Exercise_Graph;

import java.util.*;

public class p07_TheMatrix {
    static List<Node> graph = new ArrayList<>();

    static char[][] matrix;

    static char fillChar;

    static char charToFollow;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        fillMatrixFromConsole(scanner);

        fillChar = scanner.nextLine().charAt(0);

        int[] startRowCol = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int startRow = startRowCol[0];
        int startCol = startRowCol[1];

        charToFollow = matrix[startRow][startCol];
        matrix[startRow][startCol] = fillChar;

        addEdges(startRow, startCol);

        printMatrixOnConsole();
    }


    private static void addEdges(int row, int col) {

        Node node = new Node(row, col);
        graph.add(node);

        findLinkedNode(row + 1, col, node);
        findLinkedNode(row - 1, col, node);
        findLinkedNode(row, col + 1, node);
        findLinkedNode(row, col - 1, node);

        for (Node linkedNode : node.getLinkedNodes()) {
            addEdges(linkedNode.getRow(), linkedNode.getCol());
        }
    }

    private static void findLinkedNode(int row, int col, Node prev) {

        if (isOutOfBounds(row, col) || matrix[row][col] != charToFollow) {
            return;
        }

        matrix[row][col] = fillChar;
        prev.addLinkedNode(new Node(row, col));
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }

    private static void printMatrixOnConsole() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            output.append(String.valueOf(matrix[i])).append(System.lineSeparator());
        }
        System.out.print(output.toString().trim());
    }

    private static void fillMatrixFromConsole(Scanner scanner) {
        int[] matrixSize = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int numberOfRows = matrixSize[0];
        int numberOfCols = matrixSize[1];
        matrix = new char[numberOfRows][numberOfCols];

        for (int i = 0; i < numberOfRows; i++) {
            String[] elements = scanner.nextLine().split(" ");
            for (int j = 0; j < numberOfCols; j++) {
                matrix[i][j] = elements[j].charAt(0);
            }
        }
    }

    static class Node {
        private int row;
        private int col;
        private List<Node> linkedNodes;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
            this.linkedNodes = new ArrayList<>();
        }

        public void addLinkedNode(Node n) {
            linkedNodes.add(n);
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public List<Node> getLinkedNodes() {
            return linkedNodes;
        }
    }
}
