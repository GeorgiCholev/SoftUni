package MultidimensionalArrays_02.Lab;

import java.util.Scanner;

public class p03_Intersection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int row = Integer.parseInt(scanner.nextLine());
        int col = Integer.parseInt(scanner.nextLine());

        char[][] A = readMatrix(scanner, row, col);
        char[][] B = readMatrix(scanner, row, col);
        char[][] C = intersectionOfTwoMatrices(A, B);

        printMatrix(C);
    }

    private static void printMatrix(char[][] c) {
        for (int row = 0; row < c.length; row++) {
            for (int col = 0; col < c[row].length; col++) {
                System.out.print(c[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] intersectionOfTwoMatrices(char[][] a, char[][] b) {
        char[][] matrix = new char[a.length][];

        for (int row = 0; row < a.length; row++) {

            for (int col = 0; col < a[row].length; col++) {

                char symbol = a[row][col] == b[row][col] ? a[row][col] : '*';
                matrix[row][col] = symbol;
            }
        }
        return matrix;
    }

    private static char[][] readMatrix(Scanner scanner, int row, int col) {
        char[][] matrixChar = new char[row][col];
        for (int i = 0; i < matrixChar.length; i++) {
            char[] arrChar = new char[col];
            String arrString = scanner.nextLine();
            int index = 0;
            for (int j = 0; j < arrString.length(); j += 2) {
                arrChar[index] = arrString.charAt(j);
                index++;
            }
            matrixChar[i] = arrChar;
        }
        return matrixChar;
    }
}
