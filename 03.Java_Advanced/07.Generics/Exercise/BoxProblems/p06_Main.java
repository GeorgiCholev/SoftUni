package Generics_08.Exercise.BoxProblems;

import java.util.Scanner;

public class p06_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfInputs = Integer.parseInt(scanner.nextLine());

        Box<Double> box = new Box<>();

        for (int i = 0; i < numberOfInputs; i++) {
            box.add(Double.parseDouble(scanner.nextLine()));
        }

        Double comparableElement = Double.parseDouble(scanner.nextLine());
        System.out.println(box.countGreater(comparableElement));
    }
}
