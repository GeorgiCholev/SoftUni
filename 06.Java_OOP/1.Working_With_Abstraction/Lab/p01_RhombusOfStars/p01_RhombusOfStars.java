package _1_Abstraction_and_Enum.Lab;

import java.util.Scanner;

public class p01_RhombusOfStars {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();

        printRhombus(size);
    }

    private static void printRhombus(int size) {
        for (int i = 1; i <= size; i++) {
            printRhombusHalf(size - i, i);
        }
        for (int i = 1; i <= size - 1; i++) {
            printRhombusHalf(i, size - i);
        }
    }

    private static void printRhombusHalf(int spaceCount, int starCount) {
        for (int j = 1; j <= spaceCount; j++) {
            System.out.print(" ");
        }
        for (int k = 1; k <= starCount; k++) {
            System.out.print("* ");
        }
        System.out.println();
    }
}
