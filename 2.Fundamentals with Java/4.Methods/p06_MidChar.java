package Methods_4.Exercise;

import java.util.Scanner;

public class p06_MidChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input  = scanner.nextLine();
        printMiddleChar(input);
    }
    private static void printMiddleChar(String text) {
        String [] textArray = text.split("");
        char symbol = textArray[textArray.length / 2].charAt(0);
        if (textArray.length % 2 != 0) {
            System.out.printf("%c", symbol);
        } else {
            char symbolTwo = textArray[(textArray.length / 2) - 1].charAt(0);
            System.out.printf("%c%c", symbolTwo, symbol);
        }
    }
}
