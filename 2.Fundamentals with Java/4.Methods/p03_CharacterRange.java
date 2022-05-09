package Methods_4.Exercise;

import java.util.Scanner;

public class p03_CharacterRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char one = scanner.nextLine().charAt(0);
        char two = scanner.nextLine().charAt(0);
        printCharInRange(one, two);
    }

    private static void printCharInRange(char one, char two) {
        if (two < one) {
            char temp = one;
            one = two;
            two = temp;

        }
            for (int i = one + 1; i < two; i++) {
                char index = (char) i;
                System.out.print(index + " ");
            }

    }
}

