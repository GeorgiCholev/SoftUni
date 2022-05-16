package PreExam_7;

import java.util.Scanner;

public class p06_UniquePIN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ceil1 = Integer.parseInt(scanner.nextLine());
        int ceil2 = Integer.parseInt(scanner.nextLine());
        int ceil3 = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= ceil1; i++) {
            for (int j = 2; j <= ceil2; j++) {
                for (int k = 1; k <= ceil3; k++) {
                    if (i % 2 == 0 && k % 2 == 0 && (j == 2 || j == 3 || j == 5 || j == 7)) {
                        System.out.println(i + " " + j + " " + k + " ");
                    }
                }
            }
        }
    }
}
