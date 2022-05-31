package ExamPreparation;

import java.util.Scanner;

public class Darts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String player = scanner.nextLine();
        String field = scanner.nextLine();

        int startingPoints = 301;
        int successfulShots = 0;
        int unsuccessfulShots = 0;
        boolean win = false;

        while (!field.equals("Retire")) {
            int points = Integer.parseInt(scanner.nextLine());
            switch (field) {

                case "Single":
                    if (startingPoints >= points) {
                        successfulShots++;
                        startingPoints -= points;
                    } else {
                        unsuccessfulShots++;
                    }
                    break;
                case "Double":
                    if (startingPoints >= points * 2) {
                        successfulShots++;
                        startingPoints -= (points * 2);
                    } else {
                        unsuccessfulShots++;
                    }
                    break;
                case "Triple":
                    if (startingPoints >= points * 3) {
                        successfulShots++;
                        startingPoints -= (points * 3);
                    } else {
                        unsuccessfulShots++;
                    }
                    break;
            }
            if (startingPoints == 0) {
                win = true;
                break;
            }
            field = scanner.nextLine();
        }
        if (win) {
            System.out.printf("%s won the leg with %d shots.", player, successfulShots);
        } else {
            System.out.printf("%s retired after %d unsuccessful shots.", player, unsuccessfulShots);
        }
    }
}
