package ExamPreparation;

import java.util.Scanner;

public class EasterCompetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberFestiveBreads = Integer.parseInt(scanner.nextLine());

        int bestScore = Integer.MIN_VALUE;
        String bestBaker = "";

        for (int i = 1; i <= numberFestiveBreads; i++) {
            int bakerScore = 0;
            String baker = scanner.nextLine();
            String input = scanner.nextLine();
            while (!input.equals("Stop")) {
                int grade = Integer.parseInt(input);
                bakerScore += grade;
                input = scanner.nextLine();
            }
            System.out.printf("%s has %d points.%n", baker, bakerScore);
            if (bakerScore > bestScore) {
                bestScore = bakerScore;
                bestBaker = baker;
                System.out.printf("%s is the new number 1!%n", baker);
            }
        }
        System.out.printf("%s won competition with %d points!", bestBaker, bestScore);
    }
}
