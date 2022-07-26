package Generics_08.Exercise.BoxProblems;

import java.util.Scanner;

public class p05_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int numberOfInputs = Integer.parseInt(scanner.nextLine());

        Box<String> box = new Box<>();

        for (int i = 0; i < numberOfInputs; i++) {
            box.add(scanner.nextLine());
        }

        String comparableElement = scanner.nextLine();
        System.out.println(box.countGreater(comparableElement));
    }
}
