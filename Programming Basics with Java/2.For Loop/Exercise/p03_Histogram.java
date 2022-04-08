package For_Loop_4.Exercise;

import java.util.Scanner;

public class p03_Histogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        int p5 = 0;
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            if (number < 200) {
                p1++;
            } else if (number < 400) {
                p2++;
            } else if (number < 600) {
                p3++;
            } else if (number < 800) {
                p4++;
            } else {
                p5++;
            }
        }
        System.out.printf("%.2f%%%n", (p1 * 1.00 / n * 100));
        System.out.printf("%.2f%%%n", (p2 * 1.00 / n * 100));
        System.out.printf("%.2f%%%n", (p3 * 1.00 / n * 100));
        System.out.printf("%.2f%%%n", (p4 * 1.00 / n * 100));
        System.out.printf("%.2f%%%n", (p5 * 1.00 / n * 100));


    }
}