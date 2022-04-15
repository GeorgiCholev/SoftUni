package ExamPreparation;

import java.util.Scanner;

public class BasketballTournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String tournamentName = scanner.nextLine();

        int allMatches = 0;
        double lostMatches = 0;
        double wonMatches = 0;

        while (!tournamentName.equals("End of tournaments")) {
            int currentTournament = 0;
            int numberGame = Integer.parseInt(scanner.nextLine());
            for (int i = 1; i <= numberGame; i++) {
                currentTournament++;
                allMatches++;
                int desiTeam = Integer.parseInt(scanner.nextLine());
                int vsTeam = Integer.parseInt(scanner.nextLine());
                if (desiTeam > vsTeam) {
                    wonMatches++;
                    System.out.printf("Game %d of tournament %s: win with %d points.%n", currentTournament, tournamentName, (desiTeam - vsTeam));
                } else {
                    lostMatches++;
                    System.out.printf("Game %d of tournament %s: lost with %d points.%n", currentTournament, tournamentName, (vsTeam - desiTeam));
                }
            }
            tournamentName = scanner.nextLine();
        }
        System.out.printf("%.2f%% matches win%n" +
                "%.2f%% matches lost", (wonMatches / allMatches) * 100, (lostMatches / allMatches) * 100);
    }
}
