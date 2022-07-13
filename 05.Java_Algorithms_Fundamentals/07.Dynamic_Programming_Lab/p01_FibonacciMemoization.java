package _7_DynamicProgramming;

import java.util.Scanner;

public class p01_FibonacciMemoization {

    public static long[] fibonacciResults;

    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();

        fibonacciResults = new long[n + 1];
        System.out.println(calcFibRecursively(n));


        fibonacciResults = new long[n + 1];
        System.out.println(calcFibIteratively(n));
    }

    private static long calcFibIteratively(int n) {
        if (n == 0) {
            return 0L;
        }

        fibonacciResults[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibonacciResults[i] = fibonacciResults[i - 1] + fibonacciResults[i - 2];
        }

        return fibonacciResults[n];
    }

    private static long calcFibRecursively(int n) {

        if (n == 0) {
            return 0L;
        }

        if (n == 1) {
            return 1L;
        }

        if (fibonacciResults[n] != 0) {
            return fibonacciResults[n];
        }

        fibonacciResults[n] = calcFibRecursively(n - 1) + calcFibRecursively(n - 2);

        return fibonacciResults[n];
    }
}
