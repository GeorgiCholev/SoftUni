package _2_CombinatorialProblems;

import java.util.Scanner;

public class p06_BinomialCoefficient {
    // N choose K calculator
    public static Long[][] memory;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        memory = new Long[n + 1][k + 1];

        System.out.println(binomial(n, k));
    }

    private static long binomial(int n, int k) {
        if (k > n) {
            return 0;
        }

        if (k == n || k == 0) {
            return 1;
        }

        if (memory[n][k] != null) {
            return memory[n][k];
        }

        memory[n][k] = binomial(n - 1, k - 1) + binomial(n - 1, k);
        return memory[n][k];
    }
}
