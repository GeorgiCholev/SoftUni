package Nested_Loops_6.Exercise;

import java.util.Scanner;

public class p02_EvenOdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());

        int digit;
        int evenSum = 0;
        int oddSum = 0;
        for (int i = start; i <= end; i++) {
            String text = "" + i;
            for (int j = 0; j < text.length(); j++) {
                digit = Integer.parseInt("" + text.charAt(j));
                if (j % 2 != 0) {
                    evenSum += digit;
                } else {
                    oddSum += digit;
                }
            }
            if (oddSum == evenSum) {
                System.out.print(i + " ");

            }
            oddSum = 0;
            evenSum = 0;
        }
    }
}
