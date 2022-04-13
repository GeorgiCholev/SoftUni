package Nested_Loops_6.Lab;

import java.util.Scanner;

public class p05_Travelling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String destination = scanner.nextLine();
        double addition;
        double saved = 0;

        while (!destination.equals("End")) {
            double minBudget = Double.parseDouble(scanner.nextLine());
            while (saved < minBudget) {
                addition = Double.parseDouble(scanner.nextLine());
                saved += addition;
            }
            System.out.printf("Going to %s!%n", destination);
            saved = 0;
         destination = scanner.nextLine();
        }

    }
}
