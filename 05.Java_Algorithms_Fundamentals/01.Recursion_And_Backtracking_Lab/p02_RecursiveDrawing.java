package _1_RecursionAndBacktracking;

import java.util.Arrays;
import java.util.Scanner;

public class p02_RecursiveDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sequence = scanner.nextInt();

        drawingFigure(sequence);
    }

    private static void drawingFigure(int sequence) {
        if (sequence == 0) {
            return;
        }
        printing('*', sequence);

        drawingFigure(sequence - 1);

        printing('#', sequence);
    }


    private static void printing(char symbol, int sequence) {
        char[] sequenceOfSymbols = new char[sequence];
        Arrays.fill(sequenceOfSymbols, symbol);
        System.out.println(String.valueOf(sequenceOfSymbols));
    }
}
