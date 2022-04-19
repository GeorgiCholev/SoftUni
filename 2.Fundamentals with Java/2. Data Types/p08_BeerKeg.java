package DataTypes_2.Exercise;

import java.util.Scanner;

public class p08_BeerKeg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());
        double biggestKeg = 0.0;
        String modelWinner = "";
        for (int i = 1; i <= lines; i++) {
            String model = scanner.nextLine();
            double radius = Double.parseDouble(scanner.nextLine());
            int height = Integer.parseInt(scanner.nextLine());
            double currentKeg = Math.PI * Math.pow(radius, 2) * height;
            if (currentKeg > biggestKeg) {
                biggestKeg = currentKeg;
                modelWinner = model;
            }
        }
        System.out.println(modelWinner);
    }
}
