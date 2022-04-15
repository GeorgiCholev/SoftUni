package ExamPreparation;

import java.util.Scanner;

public class p04_1_Balls {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ballsPulled = Integer.parseInt(scanner.nextLine());
        double totalPoints = 0;
        int red = 0;
        int orange = 0;
        int yellow = 0;
        int white = 0;
        int other = 0;
        int black = 0;
        for (int i = 1; i <= ballsPulled; i++) {
            String colour = scanner.nextLine();
            switch (colour) {
                case "red":
                    totalPoints += 5;
                    red++;
                    break;
                case "orange":
                    totalPoints += 10;
                    orange++;
                    break;
                case "yellow":
                    totalPoints += 15;
                    yellow++;
                    break;
                case "white":
                    totalPoints += 20;
                    white++;
                    break;
                case "black":
                    totalPoints = Math.floor(totalPoints / 2);
                    black++;
                    break;
                default:
                    other++;
                    break;
            }
        }
        System.out.printf("Total points: %.0f\n" +
                "Points from red balls: %d\n" +
                "Points from orange balls: %d\n" +
                "Points from yellow balls: %d\n" +
                "Points from white balls: %d\n" +
                "Other colors picked: %d\n" +
                "Divides from black balls: %d", totalPoints, red, orange, yellow, white, other, black);
    }
}
