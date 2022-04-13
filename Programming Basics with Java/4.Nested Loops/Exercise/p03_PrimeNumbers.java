package Nested_Loops_6.Exercise;

import java.util.Scanner;

public class p03_PrimeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        int prime = 0;
        int nonPrime = 0;
        boolean isNotPrime = false;

        while (!input.equals("stop")) {
            int number = Integer.parseInt(input);
            if (number < 0) {
                System.out.println("Number is negative.");
                input = scanner.nextLine();
                continue;
            }
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    isNotPrime = true;
                    break;
                }
                isNotPrime = false;
            }
            if (isNotPrime) {
                nonPrime += number;
            } else {
                prime += number;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Sum of all prime numbers is: %d%n" +
                "Sum of all non prime numbers is: %d", prime, nonPrime);
    }
}
