package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p02_Division {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        int division = 0;
        boolean indivisible = false;

        if (number % 10 == 0) {
            division = 10;
        } else if (number % 7 == 0) {
            division = 7;
        } else if (number % 6 == 0) {
            division = 6;
        } else if (number % 3 == 0) {
            division = 3;
        } else if (number % 2 == 0) {
            division = 2;
        } else {
         indivisible = true;
        }
       if (indivisible) {
           System.out.println("Not divisible");
       } else {
           System.out.printf("The number is divisible by %d", division);
       }
    }
}
