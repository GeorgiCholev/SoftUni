package While_Loop_5.Extra;

import java.util.Scanner;

public class p02_Report {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fundingTarget = Integer.parseInt(scanner.nextLine());
        String nextFund = scanner.nextLine();
        int transactionRow = 1;
        int txCash = 0;
        int txCard = 0;
        int totalCash = 0;
        int totalCard = 0;
        int totalFunds = 0;
        int price = 0;
        boolean funded = false;
        while (!nextFund.equals("End")) {
            price = Integer.parseInt(nextFund);
            if (transactionRow % 2 != 0) {
                if (price <= 100) {
                    txCash++;
                    totalFunds += price;
                    totalCash += price;
                    System.out.println("Product sold!");
                    if (totalFunds >= fundingTarget) {
                        funded = true;
                        break;
                    }
                } else {
                    System.out.println("Error in transaction!");
                }
            } else {
                if (price > 10) {
                    txCard++;
                    totalFunds += price;
                    totalCard += price;
                    System.out.println("Product sold!");
                    if (totalFunds >= fundingTarget) {
                        funded = true;
                        break;
                    }
                } else {
                    System.out.println("Error in transaction!");
                }
            }
            transactionRow++;
            nextFund = scanner.nextLine();
        }
        double CS = totalCash * 1.0 / txCash;
        double CC = totalCard * 1.0 / txCard;
        if (funded) {
            System.out.printf("Average CS: %.2f%n" + "Average CC: %.2f",CS, CC);
        } else {
            System.out.println("Failed to collect required money for charity.");
        }
    }
}
