package _1_RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p06_EightQueensPuzzle {

    private static final char[][] chessBoard = new char[8][8];
    private static final List<Queen> placedQueens = new ArrayList<>();

    private static void fillMatrix() {
        for (char[] row : chessBoard) {
            Arrays.fill(row, '-');
        }
    }

    public static void main(String[] args) {
        fillMatrix();
        placeQueen(0);
    }

    private static void placeQueen(int row) {
        if (!isInBounds(row)) {
            printMatrix();
            return;
        }
        for (int col = 0; col < chessBoard[row].length; col++) {
            if (canPlaceQueenCandidate(row, col)) {
                chessBoard[row][col] = '*';
                placeQueen(row + 1);
                removeQueen(row, col);
            }
        }
    }

    private static void removeQueen(int row, int col) {
        chessBoard[row][col] = '-';
        placedQueens.remove(placedQueens.size() - 1);
    }


    private static boolean canPlaceQueenCandidate(int row, int col) {
        int leftDiagonal = col - row;
        int rightDiagonal = col + row;
        Queen queenCandidate = new Queen(row, col, leftDiagonal, rightDiagonal);
        for (Queen placedQueen : placedQueens) {
            if (queenCandidate.isContested(placedQueen)) {
                return false;
            }
        }
        placedQueens.add(queenCandidate);
        return true;
    }

    private static boolean isInBounds(int row) {
        return row < chessBoard.length;
    }


    private static void printMatrix() {
        for (char[] row : chessBoard) {
          for (char field : row) {
              System.out.print(field + " ");
          }
            System.out.println();
        }
        System.out.println();
    }

    static class Queen {
        private final int row;
        private final int col;
        private final int leftDiagonal;
        private final int rightDiagonal;

        public Queen(int row, int col, int leftDiagonal, int rightDiagonal) {
            this.row = row;
            this.col = col;
            this.leftDiagonal = leftDiagonal;
            this.rightDiagonal = rightDiagonal;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getLeftDiagonal() {
            return leftDiagonal;
        }

        public int getRightDiagonal() {
            return rightDiagonal;
        }

        public boolean isContested(Queen queen) {
            return this.getRow() == queen.getRow() ||
                    this.getCol() == queen.getCol() ||
                    this.getLeftDiagonal() == queen.getLeftDiagonal() ||
                    this.getRightDiagonal() == queen.getRightDiagonal();
        }
    }
}
