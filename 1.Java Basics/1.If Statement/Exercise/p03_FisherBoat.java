package If_Continuation_3.Exercise;

import java.util.Scanner;

public class p04_FisherBoat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine());
        String season = scanner.nextLine();
        int fisherman = Integer.parseInt(scanner.nextLine());

        int priceBoatRent = 0;
        double priceTotal = 0.0;
        double moneyLeft = 0.0;
        double moneyNeeded = 0.0;

        switch (season) {
            case "Spring":
                priceBoatRent = 3000;
                if (fisherman <= 6) {
                    priceTotal = priceBoatRent * 0.9;
                } else if (fisherman >= 7 && fisherman <= 11) {
                    priceTotal = priceBoatRent * 0.85;
                } else if (fisherman >= 12){
                    priceTotal = priceBoatRent * 0.75;
                }
                if (fisherman % 2 == 0){
                    priceTotal = priceTotal * 0.95;
                }
                if (priceTotal <= budget){
                    moneyLeft = budget - priceTotal;
                    System.out.printf("Yes! You have %.2f leva left.", moneyLeft);
                } else {
                    moneyNeeded = priceTotal - budget;
                    System.out.printf("Not enough money! You need %.2f leva.", moneyNeeded);
                }
                break;

            case "Summer":
                priceBoatRent = 4200;
                if (fisherman <= 6) {
                    priceTotal = priceBoatRent * 0.9;
                } else if (fisherman >= 7 && fisherman <= 11) {
                    priceTotal = priceBoatRent * 0.85;
                } else if (fisherman >= 12){
                    priceTotal = priceBoatRent * 0.75;
                }
                if (fisherman % 2 == 0){
                    priceTotal = priceTotal * 0.95;
                }
                if (priceTotal <= budget){
                    moneyLeft = budget - priceTotal;
                    System.out.printf("Yes! You have %.2f leva left.", moneyLeft);
                } else {
                    moneyNeeded = priceTotal - budget;
                    System.out.printf("Not enough money! You need %.2f leva.", moneyNeeded);
                }
                break;

            case "Autumn":
                priceBoatRent = 4200;
                if (fisherman <= 6) {
                    priceTotal = priceBoatRent * 0.9;
                } else if (fisherman >= 7 && fisherman <= 11) {
                    priceTotal = priceBoatRent * 0.85;
                } else if (fisherman >= 12){
                    priceTotal = priceBoatRent * 0.75;
                }
                if (priceTotal <= budget){
                    moneyLeft = budget - priceTotal;
                    System.out.printf("Yes! You have %.2f leva left.", moneyLeft);
                } else {
                    moneyNeeded = priceTotal - budget;
                    System.out.printf("Not enough money! You need %.2f leva.", moneyNeeded);
                }
                break;

            case "Winter":
                priceBoatRent = 2600;
                if (fisherman <= 6) {
                    priceTotal = priceBoatRent * 0.9;
                } else if (fisherman >= 7 && fisherman <= 11) {
                    priceTotal = priceBoatRent * 0.85;
                } else if (fisherman >= 12){
                    priceTotal = priceBoatRent * 0.75;
                }
                if (fisherman % 2 == 0){
                    priceTotal = priceTotal * 0.95;
                }
                if (priceTotal <= budget){
                    moneyLeft = budget - priceTotal;
                    System.out.printf("Yes! You have %.2f leva left.", moneyLeft);
                } else {
                    moneyNeeded = priceTotal - budget;
                    System.out.printf("Not enough money! You need %.2f leva.", moneyNeeded);
                }
                break;
        }
    }
}
