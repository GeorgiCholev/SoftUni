package TextProcessing_9.Exercise;

import java.util.Scanner;

public class p08_LettersChangeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] compositions = line.split("\\s+");
        double sum = 0.0;
        for (String composition : compositions) {
            sum += addToSum(composition);
        }
        System.out.printf("%.2f", sum);
    }

    private static double addToSum(String composition) {
        String numberStr = composition.substring(1, composition.length() - 1);
        char startingLetter = composition.charAt(0);
        char endingLetter = composition.charAt(composition.length() - 1);
        return operateOnComposition(numberStr, startingLetter, endingLetter);
    }

    private static double operateOnComposition(String numberStr, char startingLetter, char endingLetter) {
        int position;
        double number;
        if (Character.isLowerCase(startingLetter)) {
            position = startingLetter - 96;
            number = Double.parseDouble(numberStr) * position;
        } else {
            position = startingLetter - 64;
            number = Double.parseDouble(numberStr) / position;
        }

        if (Character.isLowerCase(endingLetter)) {
            position = endingLetter - 96;
            number += position;
        } else {
            position = endingLetter - 64;
            number -= position;
        }
        return number;
    }
}
