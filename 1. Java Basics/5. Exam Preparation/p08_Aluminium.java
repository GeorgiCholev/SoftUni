package ExamPreparation;

import java.util.Scanner;

public class p03_1_Aluminium {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantityJoinery = Integer.parseInt(scanner.nextLine());
        String typeJoinery = scanner.nextLine();
        String delivery = scanner.nextLine();
        double pricePer = 0.0;
        boolean success = true;

        switch (typeJoinery) {
            case "90X130":
                if (quantityJoinery < 10) {
                    System.out.println("Invalid order");
                    success = false;
                } else if (quantityJoinery < 30) {
                    pricePer = 140;
                    pricePer *= quantityJoinery;
                } else if (quantityJoinery < 60) {
                    pricePer = 110 * 0.95;
                    pricePer *= quantityJoinery;
                } else {
                    pricePer = 110 * 0.92;
                    pricePer *= quantityJoinery;
                }
                if (delivery.equals("With delivery")) {
                    pricePer += 60;
                }
                if (quantityJoinery > 99) {
                    pricePer *= 0.96;
                }
                break;
            case "100X150":
                if (quantityJoinery < 10) {
                    System.out.println("Invalid order");
                    success = false;
                } else if (quantityJoinery < 40) {
                    pricePer = 140;
                    pricePer *= quantityJoinery;
                } else if (quantityJoinery < 80) {
                    pricePer = 140 * 0.94;
                    pricePer *= quantityJoinery;
                } else {
                    pricePer = 140 * 0.9;
                    pricePer *= quantityJoinery;
                }
                if (delivery.equals("With delivery")) {
                    pricePer += 60;
                }
                if (quantityJoinery > 99) {
                    pricePer *= 0.96;
                }
                break;
            case "130X180":
                if (quantityJoinery < 10) {
                    System.out.println("Invalid order");
                    success = false;
                } else if (quantityJoinery < 20) {
                    pricePer = 190;
                    pricePer *= quantityJoinery;
                } else if (quantityJoinery < 50) {
                    pricePer = 190 * 0.93;
                    pricePer *= quantityJoinery;
                } else {
                    pricePer = 190 * 0.88;
                    pricePer *= quantityJoinery;
                }
                if (delivery.equals("With delivery")) {
                    pricePer += 60;
                }
                if (quantityJoinery > 99) {
                    pricePer *= 0.96;
                }
                break;
            case "200X300":
                if (quantityJoinery < 10) {
                    System.out.println("Invalid order");
                    success = false;
                } else if (quantityJoinery < 25) {
                    pricePer = 250;
                    pricePer *= quantityJoinery;
                } else if (quantityJoinery < 50) {
                    pricePer = 250 * 0.91;
                    pricePer *= quantityJoinery;
                } else {
                    pricePer = 250 * 0.86;
                    pricePer *= quantityJoinery;
                }
                if (delivery.equals("With delivery")) {
                    pricePer += 60;
                }
                if (quantityJoinery > 99) {
                    pricePer *= 0.96;
                }
                break;
        }
        if (success) {
            System.out.printf("%.2f BGN", pricePer);
        }
    }
}
