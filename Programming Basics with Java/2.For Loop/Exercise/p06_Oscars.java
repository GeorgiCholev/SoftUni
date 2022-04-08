package For_Loop_4.Exercise;

import java.util.Scanner;

public class p06_Oscars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String actorName = scanner.nextLine();
        double academyPoints = Double.parseDouble(scanner.nextLine());
        int numberVoters = Integer.parseInt(scanner.nextLine());
        double totalPoints = academyPoints;

// Точките, които актьора получава се формират от: дължината на името на оценяващия умножено по точките, които дава делено на две.

        for (int i = 1; i <= numberVoters; i++) {
            String voterName = scanner.nextLine();
            double voterPoints = Double.parseDouble(scanner.nextLine());
            int nameLength = voterName.length();
            totalPoints += ((nameLength * voterPoints) / 2);
            if (totalPoints >= 1250.5) {
                System.out.printf("Congratulations, %s got a nominee for leading role with %.1f!", actorName, totalPoints);
                break;
            }
        }
        if (totalPoints < 1250.5) {
            double diff = 1250.5 - totalPoints;
            System.out.printf("Sorry, %s you need %.1f more!", actorName, diff);
        }
    }
}
