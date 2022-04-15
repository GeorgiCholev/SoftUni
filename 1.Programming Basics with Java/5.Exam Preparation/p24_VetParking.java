package ExamPreparation;

import java.util.Scanner;

public class VetParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        int hours = Integer.parseInt(scanner.nextLine());

        double tax = 0.0;
        int day = 0;

        for (int i = 1; i <= days; i++) {
            day++;
            double dailyTax = 0.0;
            for (int j = 1; j <= hours; j++) {
                if (i % 2 == 0 && j % 2 != 0) {
                    dailyTax += 2.5;
                    tax += 2.5;
                } else if (i % 2 != 0 && j % 2 == 0) {
                    dailyTax += 1.25;
                    tax += 1.25;
                } else {
                    dailyTax++;
                    tax++;
                }
            }
            System.out.printf("Day: %d - %.2f leva%n", day, dailyTax);
        }
        System.out.printf("Total: %.2f leva", tax);
    }
}
