package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p04_CarNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int floor = Integer.parseInt(scanner.nextLine());
        int ceil = Integer.parseInt(scanner.nextLine());

        for (int i = floor; i <= ceil; i++) {
            for (int j = floor; j <= ceil; j++) {
                for (int k = floor; k <= ceil; k++) {
                    for (int l = floor; l <= ceil; l++) {
                        if (((i % 2 == 0 && l % 2 != 0) || (i % 2 != 0 && l % 2 == 0)) && i > l && (j + k) % 2 == 0) {
                            System.out.printf("%d%d%d%d" + " ", i, j, k, l);
                        }
                    }
                }
            }
        }
    }
}

