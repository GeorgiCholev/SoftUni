package Methods_4.Exercise;

import java.util.Scanner;

public class p10_TopNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int n = Integer.parseInt(scanner.nextLine());
        printTopNumber(n);
    }
    private static void printTopNumber(int range) {
        for (int i = 1; i <= range; i++) {
            String number = i + "";
            int sumDigits = 0;
            boolean odd = false;
            for (int j = 0; j < number.length(); j++) {
                char digit = number.charAt(j);
                int digitInt = digit - '0';
                if (digitInt % 2 != 0) {
                    odd = true;
                }
                sumDigits += digitInt;
                if (j == number.length() - 1 && sumDigits % 8 == 0 && odd) {
                    System.out.println(i);
                }
            }
        }
    }
}
