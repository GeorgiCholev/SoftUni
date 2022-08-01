package _8_Exception_Handling;

import java.util.Optional;
import java.util.Scanner;

public class p02_SquareRoot {
    public static void main(String[] args) {

        Optional<String> squareRoot = Optional.empty();

        try {
            int number = Integer.parseInt(new Scanner(System.in).nextLine());
            squareRoot = Optional.of(String.format("%.2f", sqrt(number)));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid");
        }

        squareRoot.ifPresent(System.out::println);

        System.out.println("Goodbye");
    }

    static double sqrt(int number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        return Math.sqrt(number);
    }
}
