package PreExam_7;

import java.util.Scanner;

public class p02_DeerSanta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysAway = Integer.parseInt(scanner.nextLine());
        double kgFoodLeft = Integer.parseInt(scanner.nextLine()) * 1.0;
        double kgFoodDeer1 = Double.parseDouble(scanner.nextLine());;
        double kgFoodDeer2 = Double.parseDouble(scanner.nextLine());
        double kgFoodDeer3 = Double.parseDouble(scanner.nextLine());

        kgFoodLeft -= (kgFoodDeer1 * daysAway) + (kgFoodDeer2 * daysAway) + (kgFoodDeer3 * daysAway);

        if (kgFoodLeft >= 0) {
            System.out.printf("%.0f kilos of food left.", Math.floor(kgFoodLeft));
        } else {
            System.out.printf("%.0f more kilos of food are needed.", Math.ceil(Math.abs(kgFoodLeft)));
        }
    }
}
