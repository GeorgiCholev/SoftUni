package Generics_08.Exercise.BoxProblems;

import java.util.Arrays;
import java.util.Scanner;

public class p04_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfInputs = Integer.parseInt(scanner.nextLine());

        Box<Integer> box = new Box<>();
        for (int i = 0; i < numberOfInputs; i++) {
            box.add(Integer.parseInt(scanner.nextLine()));
        }
        int[] indices = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int indexOne = indices[0];
        int indexTwo = indices[1];
        box.swap(indexOne, indexTwo);

        System.out.println(box.toString());
    }

}
