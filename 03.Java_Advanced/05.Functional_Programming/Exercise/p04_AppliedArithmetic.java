package FunctionalProgramming_05.Exercise;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;

public class p04_AppliedArithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        String command = scanner.nextLine();
        IntConsumer printElement = e -> System.out.print(e + " ");

        while (!command.equals("end")) {
            switch (command) {
                case "print":
                    Arrays.stream(numbers).forEach(printElement);
                    System.out.println();
                    break;
                default:
                    IntUnaryOperator function = getFunction(command);
                    numbers = Arrays.stream(numbers).map(function).toArray();
                    break;
            }
            command = scanner.nextLine();
        }
    }

    private static IntUnaryOperator getFunction(String command) {
        switch (command) {
            case "add":
                return n -> n + 1;
            case "multiply":
                return n -> n * 2;
            default:
                return n -> n - 1;
        }
    }
}
