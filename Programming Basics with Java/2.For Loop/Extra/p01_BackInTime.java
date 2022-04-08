package For_Loop_4.Extra;

import java.util.Scanner;

public class p01_BackInTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double inheritance = Double.parseDouble(scanner.nextLine());
        int yearFinal = Integer.parseInt(scanner.nextLine());

        int Years = 18;
        int cost = 0;

        for (int i = 1800; i <= yearFinal; i++) {
             if (Years % 2 ==0) {
                 cost += 12000;
             } else {
                 cost += 12000 + (50 * Years);
             }
            Years++;
        }
        double diff = 0.0;
        if (inheritance >= cost) {
            diff = inheritance - cost;
            System.out.printf("Yes! He will live a carefree life and will have %.2f dollars left.", diff);
        } else {
            diff = cost - inheritance;
            System.out.printf("He will need %.2f dollars to survive.", diff);
        }
    }
}
