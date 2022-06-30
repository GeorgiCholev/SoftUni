package _1_Abstraction_and_Enum.Lab.HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        String[] components = scanner.nextLine().split(" ");
        double pricePerDay = Double.parseDouble(components[0]);
        int numberOfDays = Integer.parseInt(components[1]);
        Season season = Season.parse(components[2]);
        DiscountType discountType = DiscountType.parse(components[3]);

        PriceCalculator current = new PriceCalculator(pricePerDay, numberOfDays, season, discountType);

        System.out.println(current.calculatePrice());
    }
}
