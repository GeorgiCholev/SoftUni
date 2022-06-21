package _1_RecursionAndBacktracking;

import java.util.Scanner;

public class p04_RecursiveFactorial {
    public static void main(String[] args) {

        Scanner scanner = new Scanner (System.in);
        int number = scanner.nextInt();

        System.out.println(findFactorial(number));
    }

    private static long findFactorial(int number) {
        if (number == 1 || number == 0) {
            return 1;
        }

       return number * findFactorial(number - 1);
    }
}
