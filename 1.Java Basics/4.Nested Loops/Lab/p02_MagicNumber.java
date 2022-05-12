package Nested_Loops_6.Lab;

import java.util.Scanner;

public class p04_MagicNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());
        int magicNumber = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        int counter = 0;
        boolean found = false;
        int i;
        int j = 0;

        for (i = start; i <= end; i++) {
            for (j = start; j <= end; j++) {
                counter++;
                sum = i + j;
                if (sum == magicNumber) {
                    found = true;
                    break;
                }
            }
            if (sum == magicNumber) {
                break;
            }
        }
        if (found) {
            System.out.printf("Combination N:%d (%d + %d = %d)", counter, i, j, magicNumber);
        } else {
            System.out.printf("%d combinations - neither equals %d", counter, magicNumber);
        }


    }
}
