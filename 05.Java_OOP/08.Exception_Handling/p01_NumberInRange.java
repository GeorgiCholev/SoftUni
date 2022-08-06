package _8_Exception_Handling;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class p01_NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] range = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int open = range[0];
        int close = range[1];

        System.out.println("Range: [" + open + "..." + close + "]");

        boolean isValidInput = false;
        while (!isValidInput) {
            String input = scanner.nextLine();
            OptionalInt number = OptionalInt.empty();
            try {
                number = OptionalInt.of(Integer.parseInt(input));
                validateRange(number.getAsInt(), open, close);
                isValidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid number: " + input);
            }

            if (isValidInput) {
                System.out.println("Valid number: " + number.getAsInt());
            }
        }
    }

    private static void validateRange(int number, int open, int close) {
        if (number < open || number > close) {
            throw new IllegalArgumentException();
        }
    }
}
