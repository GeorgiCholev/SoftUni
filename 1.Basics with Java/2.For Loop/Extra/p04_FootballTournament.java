package For_Loop_4.Extra;

import java.util.Scanner;

public class FootballTournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stadiumCapacity = Integer.parseInt(scanner.nextLine());
        int fansTotal = Integer.parseInt(scanner.nextLine());
        int fansA = 0;
        int fansB = 0;
        int fansV = 0;
        int fansG = 0;

        for (int i = 1; i <= fansTotal; i++) {
            String sector = scanner.nextLine();
            switch (sector) {
                case "A":
                    fansA++;
                    break;
                case "B":
                    fansB++;
                    break;
                case "V":
                    fansV++;
                    break;
                case "G":
                    fansG++;
                    break;
            }
        }
        System.out.printf("%.2f%%%n%.2f%%%n%.2f%%%n%.2f%%%n%.2f%%%n", (fansA * 1.0 / fansTotal * 100), (fansB * 1.0 / fansTotal * 100), (fansV * 1.0 / fansTotal * 100), (fansG * 1.0 / fansTotal * 100), (fansTotal * 1.0 / stadiumCapacity * 100));
    }
}
