package If_Continuation_3.Exercise;

import java.util.Scanner;

public class P09_Ski {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysRest = Integer.parseInt(scanner.nextLine());
        String roomType = scanner.nextLine();
        String feedback = scanner.nextLine();

        double pricePerNight = 0.0;
        double totalCost = 0.0;

        switch (roomType) {
            case "room for one person":
                pricePerNight = 18;
                totalCost = pricePerNight * (daysRest - 1);
                break;
            case "apartment":
                pricePerNight = 25;
                if (daysRest < 10) {
                    totalCost = (pricePerNight * (daysRest - 1)) * 0.7;
                } else if (daysRest >= 10 && daysRest <= 15) {
                    totalCost =(pricePerNight * (daysRest - 1)) * 0.65;
                } else if (daysRest > 15) {
                    totalCost = (pricePerNight * (daysRest - 1)) * 0.5;
                }
                break;
            case "president apartment":
                pricePerNight = 35;
                if (daysRest < 10) {
                    totalCost = (pricePerNight * (daysRest - 1)) * 0.9;
                } else if (daysRest >= 10 && daysRest <= 15) {
                    totalCost = (pricePerNight * (daysRest - 1)) * 0.85;
                } else if (daysRest > 15) {
                    totalCost = (pricePerNight * (daysRest - 1)) * 0.8;
                }
                break;
        }
        switch (feedback) {
            case "positive":
                totalCost *= 1.25;
                break;
            case "negative":
                totalCost *= 0.9;
                break;
        }
        System.out.printf("%.2f", totalCost);


    }
}
