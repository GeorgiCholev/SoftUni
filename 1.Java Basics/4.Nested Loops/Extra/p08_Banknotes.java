package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p10_Banknotes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int coins1 = Integer.parseInt(scanner.nextLine());
        int coins2 = Integer.parseInt(scanner.nextLine());
        int notes5 = Integer.parseInt(scanner.nextLine());
        int sum = Integer.parseInt(scanner.nextLine());
        int probability;

        for (int i = 0; i <= coins1; i++) {
            for (int j = 0; j<= coins2; j++) {
                for (int k = 0; k<= notes5; k++) {
                    probability = i + (j * 2) + (k * 5);
                    if (probability == sum) {
                        System.out.printf("%d * 1 lv. + %d * 2 lv. + %d * 5 lv. = %d lv.%n", i, j, k, probability);
                    }
                }
            }
        }
    }
}
