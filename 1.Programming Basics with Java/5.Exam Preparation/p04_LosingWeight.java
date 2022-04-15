package PreExam_7;

import java.util.Scanner;

public class p04_LosingWeight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberDays = Integer.parseInt(scanner.nextLine());
        double kmTotal = Double.parseDouble(scanner.nextLine());
        double kmLastDay = kmTotal;


        for (int i = 1; i <= numberDays; i++) {
            double kmThisDay = 0.0;
            int percentIncrease = Integer.parseInt(scanner.nextLine());
            kmThisDay = kmLastDay + kmLastDay * (percentIncrease * 1.0 / 100);
            kmTotal += kmThisDay;
            kmLastDay = kmThisDay;
        }
        double diff = Math.ceil(kmTotal - 1000);
        double diff2 = Math.ceil(1000 - kmTotal);
        if (kmTotal >= 1000) {
            System.out.printf("You've done a great job running %.0f more kilometers!", diff);
        } else {
            System.out.printf("Sorry Mrs. Ivanova, you need to run %.0f more kilometers", diff2);
        }
    }
}
