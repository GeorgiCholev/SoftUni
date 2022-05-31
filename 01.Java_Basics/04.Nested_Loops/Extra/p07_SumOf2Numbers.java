package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p09_SumOf2Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());
        int magic = Integer.parseInt(scanner.nextLine());
        String answer = "";

        boolean found = false;
        int counter = 0;

        for (int i = start; i<= end; i++) {
            for (int j = start; j<= end; j++) {
                counter++;
                if (i + j == magic) {
                    found = true;
                    answer = String.format("Combination N:%d (%d + %d = %d)", counter, i, j, magic);
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (found) {
            System.out.println(answer);
        } else {
            System.out.printf("%d combinations - neither equals %d", counter, magic);
        }
    }
}
