package Nested_Loops_6.Exercise;

import java.util.Scanner;

public class p01_Pyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        boolean finished = false;
        int counter = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                counter++;
                System.out.print(counter + " ");
                if (counter == n) {
                    finished = true;
                    break;
                }
            }
            if (finished) {
                break;
            }
            System.out.println();
        }
    }
}
