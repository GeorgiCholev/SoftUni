package For_Loop_4.Exercise;

import java.util.Scanner;

public class p07_TrekkingMania {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int groups = Integer.parseInt(scanner.nextLine());

        double p1 = 0.0;
        double p2 = 0.0;
        double p3 = 0.0;
        double p4 = 0.0;
        double p5 = 0.0;
        for (int i = 1; i <= groups; i++) {
            int participants = Integer.parseInt(scanner.nextLine());
            if (participants <= 5) {
                p1+= participants;
            } else if (participants <= 12) {
                p2+= participants;
            } else if (participants <= 25) {
                p3+= participants;
            } else if (participants <= 40) {
                p4+= participants;
            } else {
                p5+= participants;
            }
        }
        double allParticipants = p1 + p2 + p3 + p4 + p5;
        System.out.printf("%.2f%%%n", (p1 / allParticipants) * 100 );
        System.out.printf("%.2f%%%n", (p2 / allParticipants) * 100 );
        System.out.printf("%.2f%%%n", (p3 / allParticipants) * 100 );
        System.out.printf("%.2f%%%n", (p4 / allParticipants) * 100 );
        System.out.printf("%.2f%%%n", (p5 / allParticipants) * 100 );
    }
}