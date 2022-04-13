package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p01_UniquePIN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ceil1 = Integer.parseInt(scanner.nextLine());
        int ceil2 = Integer.parseInt(scanner.nextLine());
        int ceil3 = Integer.parseInt(scanner.nextLine());


        for (int i = 2; i <= ceil1; i += 2) {
            for (int j = 2; j <= ceil2; j++) {
                if (j == 2 || j == 3 || j == 5 || j == 7) {
                    for (int k = 2; k <= ceil3; k += 2) {
                        System.out.printf("%d %d %d%n", i, j, k);
                    }
                }
            }
        }
    }

}


