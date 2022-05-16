package ExamPreparation;

import java.util.Scanner;

public class EasterDecoration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberCustomers = Integer.parseInt(scanner.nextLine());
        double totalBill = 0.0;

        for (int i = 1; i <= numberCustomers; i++) {
            int counterProducts = 0;
            double bill = 0.0;
            String product = scanner.nextLine();
            while (!product.equals("Finish")) {
                counterProducts++;
                switch (product) {
                    case "basket":
                        bill += 1.50;
                        break;
                    case "wreath":
                        bill += 3.80;
                        break;
                    case "chocolate bunny":
                        bill += 7.00;
                        break;
                }
                product = scanner.nextLine();
            }
            if (counterProducts % 2 == 0) {
                bill *= 0.8;
            }
            System.out.printf("You purchased %d items for %.2f leva.%n", counterProducts, bill);
            totalBill += bill;
        }
        System.out.printf("Average bill per client is: %.2f leva.", totalBill / numberCustomers);
    }
}
