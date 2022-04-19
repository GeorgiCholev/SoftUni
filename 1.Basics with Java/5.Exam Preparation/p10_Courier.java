package PreExam_7;

import java.util.Scanner;

public class p03_Courier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double weight = Double.parseDouble(scanner.nextLine());
        String service = scanner.nextLine();
        int distance = Integer.parseInt(scanner.nextLine());

        double price = 0.0;
        double extra;

        switch (service) {
            case "standard":
                if (weight < 1) {
                    price = distance * 0.03;
                } else if (weight > 1 && weight < 10) {
                    price = distance * 0.05;
                } else if (weight >= 10 && weight < 40) {
                    price = distance * 0.10;
                }else if (weight >= 40 && weight < 90) {
                    price = distance * 0.15;
                }else if (weight >= 90 && weight < 150) {
                    price = distance * 0.20;
                }
                break;
            case "express":
                if (weight < 1) {
                    price = distance * 0.03;
                    extra = ((0.03 * 0.80) * weight) * distance;
                    price += extra;
                } else if (weight > 1 && weight < 10) {
                    price = distance * 0.05;
                    extra = ((0.05 * 0.40) * weight) * distance;
                    price += extra;
                } else if (weight >= 10 && weight < 40) {
                    price = distance * 0.10;
                    extra = ((0.10 * 0.05) * weight) * distance;
                    price += extra;
                }else if (weight >= 40 && weight < 90) {
                    price = distance * 0.15;
                    extra = ((0.15 * 0.02) * weight) * distance;
                    price += extra;

                }else if (weight >= 90 && weight < 150) {
                    price = distance * 0.20;
                    extra = ((0.20 * 0.01) * weight) * distance;
                    price += extra;
                }
                break;
        }
        System.out.printf("The delivery of your shipment with weight of %.3f kg. would cost %.2f lv.", weight, price);
    }
}
