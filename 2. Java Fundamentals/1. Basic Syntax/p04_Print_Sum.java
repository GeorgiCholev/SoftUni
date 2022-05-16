package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p04_Print_Sum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = scanner.nextInt();
        int end = scanner.nextInt();
        int sum = 0;

        for (int i = start; i <= end; i++) {
            System.out.print(i + " ");
            sum += i;
        }
        System.out.printf("%nSum: %d", sum);
    }
}
