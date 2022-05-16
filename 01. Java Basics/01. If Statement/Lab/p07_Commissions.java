package If_Continuation_3.Lab;

import java.util.Scanner;

public class p12_Commissions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String city = scanner.nextLine();
        double profit = Double.parseDouble(scanner.nextLine());

        double commission = 0.0;
        boolean error = false;

        switch (city) {
            case"Sofia":
                if (profit >= 0 && profit <= 500) {
                    commission = profit * 0.05;
                } else   if (profit > 500 && profit <= 1000) {
                    commission = profit * 0.07;
                }else   if (profit > 1000 && profit <= 10000) {
                    commission = profit * 0.08;
                }else   if (profit > 10000) {
                    commission = profit * 0.12;
                } else {
                    error = true;
                }
                break;
            case"Varna":
                if (profit >= 0 && profit <= 500) {
                    commission = profit * 0.045;
                } else   if (profit > 500 && profit <= 1000) {
                    commission = profit * 0.075;
                }else   if (profit > 1000 && profit <= 10000) {
                    commission = profit * 0.10;
                }else   if (profit > 10000) {
                    commission = profit * 0.13;
                } else {
                    error = true;
                }
                break;
            case"Plovdiv":
                if (profit >= 0 && profit <= 500) {
                    commission = profit * 0.055;
                } else   if (profit > 500 && profit <= 1000) {
                    commission = profit * 0.08;
                }else   if (profit > 1000 && profit <= 10000) {
                    commission = profit * 0.12;
                }else   if (profit > 10000) {
                    commission = profit * 0.145;
                } else {
                    error = true;
                }
                break;
            default:
                error = true;
                break;

        }
        if (error) {
            System.out.println("error");
        } else {
            System.out.printf("%.2f", commission);
        }
    }
}
