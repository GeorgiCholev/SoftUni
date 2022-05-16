package ExamPreparation;

import java.util.Scanner;

public class p05_1_BestPlayer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String playerName = scanner.nextLine();
        int mostGoals = Integer.MIN_VALUE;
        String bestPlayer = "";
        while (!playerName.equals("END")) {
            int goals = Integer.parseInt(scanner.nextLine());
            if (goals > mostGoals) {
                mostGoals = goals;
                bestPlayer = playerName;
                if (goals >= 10) {
                    break;
                }
            }
            playerName = scanner.nextLine();
        }
        System.out.printf("%s is the best player!%n", bestPlayer);
        if (mostGoals >= 3) {
            System.out.printf("He has scored %d goals and made a hat-trick !!!", mostGoals);
        } else {
            System.out.printf("He has scored %d goals.", mostGoals);
        }

    }
}
