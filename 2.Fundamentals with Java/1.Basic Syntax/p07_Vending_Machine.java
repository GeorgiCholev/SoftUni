package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p07_Vending_Machine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        double coin = 0.0;
        double sum = 0.0;

        while (!input.equals("Start")) {
            coin = Double.parseDouble(input);
            if (coin == 0.1 || coin == 0.2 || coin == 0.5 || coin == 1 || coin == 2) {
                sum += coin;
            } else {
                System.out.printf("Cannot accept %.2f%n", coin);
            }
            input = scanner.nextLine();
        }

        input = scanner.nextLine();
        boolean purchased = false;
        boolean invalid = false;
        while (!input.equals("End")) {
            switch (input) {
                case "Nuts":
                    if (sum >= 2.0) {
                        sum -= 2.0;
                        purchased = true;
                    }
                    break;
                case "Water":
                    if (sum >= 0.7) {
                        sum -= 0.7;
                        purchased = true;
                    }
                    break;
                case "Crisps":
                    if (sum >= 1.5) {
                        sum -= 1.5;
                        purchased = true;
                    }
                    break;
                case "Soda":
                    if (sum >= 0.8) {
                        sum -= 0.8;
                        purchased = true;
                    }
                    break;
                case "Coke":
                    if (sum >= 1.0) {
                        sum -= 1.0;
                        purchased = true;
                    }
                    break;
                default:
                    invalid = true;
                    System.out.println("Invalid product");
                    break;
            }
            if (!invalid) {
                if (purchased) {
                    System.out.printf("Purchased %s%n", input);
                } else {
                    System.out.println("Sorry, not enough money");
                }
            }
            invalid = false;
            purchased = false;
            input = scanner.nextLine();
        }
        System.out.printf("Change: %.2f", sum);
    }
}

