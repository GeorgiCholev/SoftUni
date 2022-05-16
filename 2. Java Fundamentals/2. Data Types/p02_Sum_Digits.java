package DataTypes_2.Exercise;

import java.util.Scanner;

public class p02_Sum_Digits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        int number = Integer.parseInt(input);
        int sum = 0;
        int digit;
        for (int i = 1; i <= input.length(); i++) {
            digit = number % 10;
            sum += digit;
            number /= 10;
        }
        System.out.println(sum);
    }
}
