package Methods_4.Exercise;

import java.util.Scanner;

public class p09_Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("END")) {
            boolean palindrome;
            palindrome = checkPalindrome(input);
            System.out.println(palindrome);
            input = scanner.nextLine();
        }
    }

    private static boolean checkPalindrome(String numberText) {
        String[] numberTextArray = numberText.split("");
        int[] numbers = parseArray(numberTextArray);
        boolean palindrome = false;
        for (int i = 0; i <= numbers.length / 2; i++) {
            if (numbers[i] == numbers[numbers.length - 1 - i]) {
                palindrome = true;
            } else {
                palindrome = false;
                break;
            }
        }
            return palindrome;
    }

    private static int[] parseArray(String[] numberTextArray) {
        int[] numbers = new int[numberTextArray.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numberTextArray[i]);
        }
        return numbers;
    }
}
