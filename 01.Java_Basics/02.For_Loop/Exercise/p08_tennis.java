package For_Loop_4.Exercise;

import java.util.Scanner;

public class p08_tennis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberTournament = Integer.parseInt(scanner.nextLine());
        int points = Integer.parseInt(scanner.nextLine());
        int wonTournaments = 0;
        int startingPoints = points;

        for (int i = 1; i <= numberTournament; i++) {
            String position = scanner.nextLine();
            switch (position) {
                case "W":
                    wonTournaments++;
                    points += 2000;
                    break;
                case "F":
                    points += 1200;
                    break;
                case "SF":
                    points += 720;
                    break;
            }
        }
        double averagePoints = Math.floor((points * 1.0 - startingPoints) / numberTournament);
        double percentWon = ((wonTournaments * 1.0) / numberTournament) * 100;
        System.out.printf("Final points: %d%n", points);
        System.out.printf("Average points: %.0f%n", averagePoints);
        System.out.printf("%.2f%%", percentWon);

    }
}
