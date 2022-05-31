package For_Loop_4.Extra;

import java.util.Scanner;

public class EvenOdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        double oddSum = 0.0;
        double oddMin = Double.POSITIVE_INFINITY;
        double oddMax = Double.NEGATIVE_INFINITY;
        double evenSum = 0;
        double evenMin = Double.POSITIVE_INFINITY;
        double evenMax = Double.NEGATIVE_INFINITY;
        boolean even = false;
        boolean odd = false;

        for (int i = 1; i <= n; i++) {
            double number = Double.parseDouble(scanner.nextLine());
            if (i % 2 == 0) {
                even = true;
                evenSum += number;
                if (number > evenMax) {
                    evenMax = number;
                }
                if (number < evenMin) {
                    evenMin = number;
                }
            } else {
                odd = true;
                oddSum += number;
                if (number > oddMax) {
                    oddMax = number;
                }
                if (number < oddMin) {
                    oddMin = number;
                }
            }
        }
        if (even && odd) {
            System.out.printf("OddSum=%.2f,%n" +
                    "OddMin=%.2f,%n" +
                    "OddMax=%.2f,%n" +
                    "EvenSum=%.2f,%n" +
                    "EvenMin=%.2f,%n" +
                    "EvenMax=%.2f", oddSum, oddMin, oddMax, evenSum, evenMin, evenMax);
        } else if (even) {
            System.out.printf("OddSum=0.00,%n" +
                   "OddMin=No,%n" +
                    "OddMax=No,%n" +
                    "EvenSum=%.2f,%n" +
                    "EvenMin=%.2f,%n" +
                    "EvenMax=%.2f", evenSum, evenMin, evenMax);
        } else if (odd) {
            System.out.printf("OddSum=%.2f,%n" +
                    "OddMin=%.2f,%n" +
                    "OddMax=%.2f,%n" +
                    "EvenSum=0.00,%n" +
                    "EvenMin=No,%n" +
                    "EvenMax=No", oddSum, oddMin, oddMax);
        }
        else {
            System.out.printf("OddSum=0.00,%n" +
                    "OddMin=No,%n" +
                    "OddMax=No,%n" +
                    "EvenSum=0.00,%n" +
                    "EvenMin=No,%n" +
                    "EvenMax=No");
        }
    }
}
