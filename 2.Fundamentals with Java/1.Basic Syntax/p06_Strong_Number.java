package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p06_Strong_Number {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        int startingNumber = number;
        String input = number + "";
        int digit;
        int factorial = 1;
        int totalFact = 0;

        for (int i = input.length(); i >= 1; i--) {
            digit = number % 10;
            number /= 10;
            for (int j = 1; j <= digit; j++) {
                factorial *= j;
            }
            totalFact += factorial;
            factorial = 1;
        }
        if (totalFact == startingNumber) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
