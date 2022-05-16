package Methods_4.Exercise;

import java.util.Scanner;

public class p08_FactorialDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double one = Double.parseDouble(scanner.nextLine());
        double two = Double.parseDouble(scanner.nextLine());
        if (one >= 0 && two >= 0) {
            double factorialOne = calculateFactorial(one);
            double factorialTwo = calculateFactorial(two);
            printFactorialDivision(factorialOne, factorialTwo);
        }
    }

    private static double calculateFactorial(double number) {
        double factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }

    private static void printFactorialDivision(double factorialOne, double factorialTwo) {
        double division = factorialOne / factorialTwo;
        System.out.printf("%.2f", division);
    }
}
