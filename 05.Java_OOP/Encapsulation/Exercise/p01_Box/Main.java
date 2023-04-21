package _2_Encapsulation.Exercise.p01_Box;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        double length = scanner.nextDouble();
        double width = scanner.nextDouble();
        double height = scanner.nextDouble();

        try {

            Box box = new Box(length, width, height);
            print("Surface Area", box.calculateSurfaceArea());
            print("Lateral Surface Area", box.calculateLateralSurfaceArea());
            print("Volume", box.calculateVolume());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void print(String property, double propertyValue) {
        System.out.println(property + " - " + String.format("%.2f", propertyValue));
    }
}
