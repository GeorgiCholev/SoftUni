package SetsAndMaps_03.Lab;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class p01_ParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> carNumbers = new LinkedHashSet<>();

        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String[] data = input.split(",\\s");
            String direction = data[0];
            String carNumber = data[1];
            switch (direction) {
                case "IN":
                    carNumbers.add(carNumber);
                    break;
                case "OUT":
                    carNumbers.remove(carNumber);
                    break;
            }
            input = scanner.nextLine();
        }
        if (carNumbers.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            carNumbers.forEach(System.out::println);
        }
    }
}
