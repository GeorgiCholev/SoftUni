package While_Loop_5.Exercise;

import java.util.Scanner;

public class p03_Trip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double moneyForTrip = Double.parseDouble(scanner.nextLine());
        double availableMoney = Double.parseDouble(scanner.nextLine());
        String action = scanner.nextLine();

        int daysSpending = 0;
        boolean goingOnTrip = false;
        int daysPassed = 0;

        while (action.equals("spend") || action.equals("save")) {
            double moneyInAction = Double.parseDouble(scanner.nextLine());
            daysPassed++;
            if (action.equals("spend")) {
                daysSpending++;
                if (moneyInAction >= availableMoney) {
                    availableMoney = 0;
                } else {
                    availableMoney -= moneyInAction;
                }
                if (daysSpending == 5) {
                    break;
                }
            } else {
                daysSpending = 0;
                availableMoney += moneyInAction;
                if (availableMoney >= moneyForTrip) {
                    goingOnTrip = true;
                    break;
                }
            }
            action = scanner.nextLine();
        }


        if (goingOnTrip) {
            System.out.printf("You saved the money for %d days.",daysPassed);
        } else {
            System.out.println("You can't save the money.");
            System.out.println(daysPassed);
        }

    }
}
