package DataTypes_2.Exercise;

import java.util.Scanner;

public class p09_Spices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int yieeld = Integer.parseInt(scanner.nextLine());
        int days = 0;
        int minedSpicePerDay;
        int counterDecrease = 0;
        int totalSpiceMined = 0;
        while ((yieeld - counterDecrease) >= 100) {
            days++;
            minedSpicePerDay = yieeld - counterDecrease;
            totalSpiceMined += minedSpicePerDay;
            totalSpiceMined -= 26;
            counterDecrease += 10;
        }
        if (totalSpiceMined > 0) {
            totalSpiceMined -= 26;
        }
        System.out.println(days + "\n" + totalSpiceMined);
    }
}
