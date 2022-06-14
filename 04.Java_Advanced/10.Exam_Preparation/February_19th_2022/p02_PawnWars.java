package ExamPreparation_10.February_19th_2022;

import java.util.Scanner;

public class p02_PawnWars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] chessBoard = new char[8][8];
        int[] pawnsPositions = placeTwoPawns(scanner, chessBoard);

        Pawn whitePawn = new Pawn(pawnsPositions[0], pawnsPositions[1]);
        Pawn blackPawn = new Pawn(pawnsPositions[2], pawnsPositions[3]);

        while (whitePawn.getRow() != 0 || blackPawn.getRow() != 7) {
            boolean whiteTakes = whiteMove(whitePawn, chessBoard);
            if (whiteTakes) {
                System.out.println("Game over! White capture on " +
                        whitePawn.getChessColumn() + whitePawn.getChessRow() + ".");
                break;
            }
            chessBoard[whitePawn.getRow()][whitePawn.getColumn()] = '-';
            whitePawn.setRow(whitePawn.getRow() - 1);
            chessBoard[whitePawn.getRow()][whitePawn.getColumn()] = 'w';

            if (whitePawn.getRow() == 0) {
                System.out.println("Game over! White pawn is promoted to a queen at " +
                        whitePawn.getChessColumn() + whitePawn.getChessRow() + ".");
                break;
            }

            boolean blackTakes = blackMove(blackPawn, chessBoard);
            if (blackTakes) {
                System.out.println("Game over! Black capture on " +
                        blackPawn.getChessColumn() + blackPawn.getChessRow() + ".");
                break;
            }

            chessBoard[blackPawn.getRow()][blackPawn.getColumn()] = '-';
            blackPawn.setRow(blackPawn.getRow() + 1);
            chessBoard[blackPawn.getRow()][blackPawn.getColumn()] = 'b';

            if (blackPawn.getRow() == 7) {
                System.out.println("Game over! Black pawn is promoted to a queen at " +
                        blackPawn.getChessColumn() + blackPawn.getChessRow() + ".");
                break;
            }
        }
    }

    private static boolean blackMove(Pawn blackPawn, char[][] chessBoard) {
        switch (blackPawn.getBounds()) {
            case -1:
                if (chessBoard[blackPawn.getRow() + 1][blackPawn.getColumn() + 1] == 'w') {
                    blackPawn.setRow(blackPawn.getRow() + 1);
                    blackPawn.setColumn(blackPawn.getColumn() + 1);
                    return true;
                }
                break;
            case 1:
                if (chessBoard[blackPawn.getRow() + 1][blackPawn.getColumn() - 1] == 'w') {
                    blackPawn.setRow(blackPawn.getRow() + 1);
                    blackPawn.setColumn(blackPawn.getColumn() - 1);
                    return true;
                }
                break;
            case 0:
                if (chessBoard[blackPawn.getRow() + 1][blackPawn.getColumn() - 1] == 'w') {
                    blackPawn.setRow(blackPawn.getRow() + 1);
                    blackPawn.setColumn(blackPawn.getColumn() - 1);
                    return true;
                } else if (chessBoard[blackPawn.getRow() + 1][blackPawn.getColumn() + 1] == 'w') {
                    blackPawn.setRow(blackPawn.getRow() + 1);
                    blackPawn.setColumn(blackPawn.getColumn() + 1);
                    return true;
                }
                break;
        }
        return false;
    }

    private static boolean whiteMove(Pawn whitePawn, char[][] chessBoard) {
        switch (whitePawn.getBounds()) {
            case -1:
                if (chessBoard[whitePawn.getRow() - 1][whitePawn.getColumn() + 1] == 'b') {
                    whitePawn.setRow(whitePawn.getRow() - 1);
                    whitePawn.setColumn(whitePawn.getColumn() + 1);
                    return true;
                }
                break;
            case 1:
                if (chessBoard[whitePawn.getRow() - 1][whitePawn.getColumn() - 1] == 'b') {
                    whitePawn.setRow(whitePawn.getRow() - 1);
                    whitePawn.setColumn(whitePawn.getColumn() - 1);
                    return true;
                }
                break;
            case 0:
                if (chessBoard[whitePawn.getRow() - 1][whitePawn.getColumn() - 1] == 'b') {
                    whitePawn.setRow(whitePawn.getRow() - 1);
                    whitePawn.setColumn(whitePawn.getColumn() - 1);
                    return true;
                } else if (chessBoard[whitePawn.getRow() - 1][whitePawn.getColumn() + 1] == 'b') {
                    whitePawn.setRow(whitePawn.getRow() - 1);
                    whitePawn.setColumn(whitePawn.getColumn() + 1);
                    return true;
                }
                break;
        }
        return false;
    }

    static class Pawn {

        private int row;
        private int column;
        private final int bounds;
        private char chessColumn;
        private int chessRow;


        Pawn(int row, int column) {
            this.setRow(row);
            this.setColumn(column);
            this.bounds = checkPawnsBounds(column);
        }

        private int checkPawnsBounds(int column) {
            if (column == 0) {
                return -1;
            } else if (column == 7) {
                return 1;
            } else {
                return 0;
            }
        }

        public int getRow() {
            return row;
        }

        public int getBounds() {
            return bounds;
        }

        public int getColumn() {
            return column;
        }

        public char getChessColumn() {
            return chessColumn;
        }

        public int getChessRow() {
            return chessRow;
        }

        public void setRow(int row) {
            this.row = row;
            this.chessRow = 8 - row;
        }

        public void setColumn(int column) {
            this.column = column;
            this.chessColumn = (char) ('a' + column);
        }
    }


    private static int[] placeTwoPawns(Scanner scanner, char[][] matrix) {
        int[] positions = new int[4];
        for (int i = 0; i < 8; i++) {
            char[] rank = scanner.nextLine().toCharArray();
            for (int j = 0; j < rank.length; j++) {
                if (rank[j] == 'w') {
                    positions[0] = i;
                    positions[1] = j;
                } else if (rank[j] == 'b') {
                    positions[2] = i;
                    positions[3] = j;
                }
                matrix[i][j] = rank[j];
            }
        }
        return positions;
    }
}
