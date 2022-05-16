package While_Loop_5.Exercise;

import java.util.Scanner;

public class p04_Walking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int steps;
        int totalSteps = 0;
        boolean success = false;

        while (true) {
            if (input.equals("Going home")) {
                steps = Integer.parseInt(scanner.nextLine());
                totalSteps += steps;
                if (totalSteps >= 10000) {
                    success = true;
                }
                break;
            } else {
                steps = Integer.parseInt(input);
                totalSteps += steps;
                if (totalSteps >= 10000) {
                    success = true;
                    break;
                }
                input = scanner.nextLine();
            }
        }

        if (success) {
            System.out.println("Goal reached! Good job!");
            System.out.printf("%d steps over the goal!", totalSteps - 10000);
        } else {
            System.out.printf("%d more steps to reach goal.", 10000 - totalSteps);
        }

    }
}
