package While_Loop_5.Lab;

import java.util.Scanner;

public class p05_Account {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String addedMoney = scanner.nextLine();

        double total = 0;

        while (!addedMoney.equals("NoMoreMoney")) {
            double addedMoneyNumber = Double.parseDouble(addedMoney);
            if (addedMoneyNumber < 0) {
                System.out.printf("Invalid operation!%n");
                break;
            }
            total += addedMoneyNumber;
            System.out.printf("Increase: %.2f%n", addedMoneyNumber);
            addedMoney = scanner.nextLine();
        }
        System.out.printf("Total: %.2f", total);
    }
}
