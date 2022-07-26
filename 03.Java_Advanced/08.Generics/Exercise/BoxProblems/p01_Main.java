package Generics_08.Exercise.BoxProblems;

import java.util.Scanner;

public class p01_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int numberOfInputs = Integer.parseInt(scanner.nextLine());

        Box<String> box = new Box<>();
        for (int i = 0; i < numberOfInputs; i++) {
            String line = scanner.nextLine();
            box.add(line);
        }
        System.out.println(box.toString());
    }
}
