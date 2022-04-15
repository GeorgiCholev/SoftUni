package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p12_Cart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = Integer.parseInt(scanner.nextLine());

        int counter = 0;
        String luckyNumber = "";
        boolean found = false;

        for (int a = 1; a <= 9; a++) {
            for (int b = 1; b <= 9; b++) {
                for (int c = 1; c <= 9; c++) {
                    for (int d = 1; d <= 9; d++) {
                        if (a < b && c > d && (a * b) + (c * d) == m) {
                            counter++;
                            System.out.printf("%d%d%d%d ", a, b, c, d);
                            if (counter == 4) {
                                found = true;
                                luckyNumber = String.format("%d%d%d%d ", a, b, c, d);
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        if (!found) {
            System.out.println("No!");
        } else {
            System.out.printf("Password: %s", luckyNumber);
        }
    }
}

