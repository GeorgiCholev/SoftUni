package ExamPreparation;

import java.util.Scanner;

public class p06_3_Christmas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysTournament = Integer.parseInt(scanner.nextLine());

        double totalPrize = 0;
        int totalWins = 0;
        int totalLoses = 0;

        for (int i = 1; i <= daysTournament; i++) {
            int win = 0;
            int lose = 0;
            int dailyPrizes = 0;
            String sportName = scanner.nextLine();
            while (!sportName.equals("Finish")) {
                String result = scanner.nextLine();
                switch (result) {
                    case "win":
                        dailyPrizes += 20;
                        win++;
                        break;
                    case "lose":
                        lose++;
                        break;
                }
                sportName = scanner.nextLine();
            }
            if (win > lose) {
                totalWins++;
                totalPrize += dailyPrizes * 1.1;
            } else {
                totalLoses++;
                totalPrize += dailyPrizes;
            }
        }
        if (totalWins > totalLoses) {
            totalPrize *= 1.2;
            System.out.printf("You won the tournament! Total raised money: %.2f", totalPrize);
        } else {
            System.out.printf("You lost the tournament! Total raised money: %.2f", totalPrize);
        }
    }

}

