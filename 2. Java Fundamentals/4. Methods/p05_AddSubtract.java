package Methods_4.Exercise;

import java.util.Scanner;

public class p05_AddSubtract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int x = Integer.parseInt(scanner.nextLine());
        int y = Integer.parseInt(scanner.nextLine());
        int z = Integer.parseInt(scanner.nextLine());
        int result = findResult(x, y, z);
        System.out.println(result);
    }

    private static int findResult(int numberOne, int numberTwo, int numberThree) {
        int sumOfTwo = numberOne + numberTwo;
        return subtract(sumOfTwo, numberThree);
    }

    private static int subtract(int numberOne, int numberTwo) {
        return numberOne - numberTwo;
    }
}

