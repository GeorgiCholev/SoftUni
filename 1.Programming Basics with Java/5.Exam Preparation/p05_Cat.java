package ExamPreparation;

import java.util.Scanner;

public class p02_2_Cat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int minutesWalk = Integer.parseInt(scanner.nextLine());
        int numberOfWalks = Integer.parseInt(scanner.nextLine());
        int caloriesConsumed = Integer.parseInt(scanner.nextLine());

        int totalWalk = minutesWalk * numberOfWalks;
        int caloriesBurned = totalWalk * 5;
        double enoughWalk = caloriesConsumed * 0.5;
        if (enoughWalk <= caloriesBurned) {
            System.out.printf("Yes, the walk for your cat is enough. Burned calories per day: %d.", caloriesBurned);
        } else {
            System.out.printf("No, the walk for your cat is not enough. Burned calories per day: %d.", caloriesBurned);
        }

    }
}
