package DataTypes_2.Exercise;

import java.util.Scanner;

public class p07_WaterOverflow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lines = Integer.parseInt(scanner.nextLine());
        int tank = 255;
        int litresPoured = 0;
        for (int i = 1; i <= lines; i++) {
            int litres = Integer.parseInt(scanner.nextLine());
            if (litres > tank) {
                System.out.println("Insufficient capacity!");
            } else {
                tank -= litres;
                litresPoured += litres;
            }
        }
        System.out.println(litresPoured);

    }
}
