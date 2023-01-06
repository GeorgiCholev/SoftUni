package implementations;

import java.util.ArrayDeque;
public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {

        Cell first = new Cell(this.startRow, this.startCol);
        ArrayDeque<Cell> toBeReplacedStack = new ArrayDeque<>();
        toBeReplacedStack.push(first);

        while (!toBeReplacedStack.isEmpty()) {

            Cell current = toBeReplacedStack.pop();
            int row = current.getRow();
            int col = current.getCol();
            this.matrix[row][col] = fillChar;

            if (isCellToBeReplaced(row - 1, col)) {
                toBeReplacedStack.push(new Cell(row - 1, col));
            }

            if (isCellToBeReplaced(row, col + 1)) {
                toBeReplacedStack.push(new Cell(row, col + 1));
            }

            if (isCellToBeReplaced(row + 1, col)) {
                toBeReplacedStack.push(new Cell(row + 1, col));
            }

            if (isCellToBeReplaced(row, col - 1)) {
                toBeReplacedStack.push(new Cell(row, col - 1));
            }
        }

    }

    private boolean isCellToBeReplaced(int row, int col) {
        return row >= 0 && row < matrix.length
                && col >= 0 && col < matrix[row].length
                && matrix[row][col] == toBeReplaced;
    }

    public String toOutputString() {
       StringBuilder output = new StringBuilder();

        for (int row = 0; row < matrix.length; row++) {
            output.append(String.valueOf(matrix[row]));

            if (row != matrix.length - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    private static class Cell {

        private final int row;
        private final int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
