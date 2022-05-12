package ExamPreparation;

import java.util.Scanner;

public class p03_2_Gel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fruit = scanner.nextLine();
        String size = scanner.nextLine();
        int numberSets = Integer.parseInt(scanner.nextLine());

        double price = 0.0;

        switch (fruit) {
            case "Watermelon":
                switch (size) {
                    case "small":
                        price = numberSets * 2 * 56;
                        if (price >= 400 && price <= 1000) {
                            price *= 0.85;
                        } else if (price > 1000) {
                            price *= 0.5;
                        }
                        break;
                    case "big":
                        price = numberSets * 5 * 28.7;
                        if (price >= 400 && price <= 1000) {
                            price *= 0.85;
                        } else if (price > 1000) {
                            price *= 0.5;
                        }
                        break;
                }
                break;
            case "Mango":
                switch (size) {
                    case "small":
                        price = numberSets * 2 * 36.66;
                        if (price >= 400 && price <= 1000) {
                            price *= 0.85;
                        } else if (price > 1000) {
                            price *= 0.5;
                        }
                        break;
                    case "big":
                        price = numberSets * 5 * 19.60;
                        if (price >= 400 && price <= 1000) {
                            price *= 0.85;
                        } else if (price > 1000) {
                            price *= 0.5;
                        }
                        break;
                }
                break;
            case "Pineapple":
                switch (size) {
                    case "small":
                        price = numberSets * 2 * 42.10;
                        if (price >= 400 && price <= 1000) {
                            price *= 0.85;
                        } else if (price > 1000) {
                            price *= 0.5;
                        }
                        break;
                    case "big":
                        price = numberSets * 5 * 24.80;
                        if (price >= 400 && price <= 1000) {
                            price *= 0.85;
                        } else if (price > 1000) {
                            price *= 0.5;
                        }
                        break;
                }
                break;
            case "Raspberry":
                switch (size) {
                    case "small":
                        price = numberSets * 2 * 20;
                        if (price >= 400 && price <= 1000) {
                            price *= 0.85;
                        } else if (price > 1000) {
                            price *= 0.5;
                        }
                        break;
                    case "big":
                        price = numberSets * 5 * 15.20;
                        if (price >= 400 && price <= 1000) {
                            price *= 0.85;
                        } else if (price > 1000) {
                            price *= 0.5;
                        }
                        break;
                }
                break;
        }
        System.out.printf("%.2f lv.", price);
    }
}
