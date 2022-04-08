package For_Loop_4.Lab;

import java.util.Scanner;

public class p10_OddAndEven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int odd = 0;
        int even = 0;

        for (int i = 1; i <= n; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            if (i % 2 == 0) {
                even += number;
            } else {
                odd += number;
            }
        }
        if (odd == even) {
            System.out.printf("Yes%n");
            System.out.printf("Sum = %d", odd);
        } else {
            int diff = Math.abs(even - odd);
            System.out.printf("No%n");
            System.out.printf("Diff = %d", diff);
        }
    }
}
