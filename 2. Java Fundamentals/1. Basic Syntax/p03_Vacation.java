package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p03_Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        String type = scanner.nextLine();
        String day = scanner.nextLine();
        double total = 0.0;

        switch (day) {
            case "Friday":
                switch (type) {
                    case "Students":
                        if (number >= 30) {
                            total = (number * 8.45) * 0.85;
                        } else {
                            total = number * 8.45;
                        }
                        break;
                    case "Business":
                        if (number >= 100) {
                            total = (number * 10.90) - 109;
                        } else {
                            total = number * 10.90;
                        }
                        break;
                    case "Regular":
                        if (number >= 10 && number <= 20) {
                            total = (number * 15) * 0.95;
                        } else {
                            total = number * 15;
                        }
                        break;
                }
                break;
            case "Saturday":
                switch (type) {
                    case "Students":
                        if (number >= 30) {
                            total = (number * 9.80) * 0.85;
                        } else {
                            total = number * 9.80;
                        }
                        break;
                    case "Business":
                        if (number >= 100) {
                            total = (number * 15.60) - 156;
                        } else {
                            total = number * 15.60;
                        }
                        break;
                    case "Regular":
                        if (number >= 10 && number <= 20) {
                            total = (number * 20) * 0.95;
                        } else {
                            total = number * 20;
                        }
                        break;
                }
                break;
            case "Sunday":
                switch (type) {
                    case "Students":
                        if (number >= 30) {
                            total = (number * 10.46) * 0.85;
                        } else {
                            total = number * 10.46;
                        }
                        break;
                    case "Business":
                        if (number >= 100) {
                            total = (number * 16) - 160;
                        } else {
                            total = number * 16;
                        }
                        break;
                    case "Regular":
                        if (number >= 10 && number <= 20) {
                            total = (number * 22.50) * 0.95;
                        } else {
                            total = number * 22.50;
                        }
                        break;
                }
                break;
        }
        System.out.printf("Total price: %.2f", total);
    }
}
