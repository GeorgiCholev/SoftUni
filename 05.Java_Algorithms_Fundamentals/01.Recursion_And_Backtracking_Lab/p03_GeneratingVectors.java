package _1_RecursionAndBacktracking;

import java.util.Scanner;

//Algorithm: Generating Simple Combinations

public class p03_GeneratingVectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vectorLength = scanner.nextInt();
        // Using wrapper class to distinct empty spaces - null with value - 0
        Integer[] vector = new Integer[vectorLength];

        generateVector(vector, 0);
    }

    private static void generateVector(Integer[] vector, int index) {
        if (index > vector.length - 1) {
           print(vector);
            return;
        }

        for (int i = 0; i <= 1; i++) {
            vector[index] = i;
            generateVector(vector, index + 1);
        }
    }
    private static void print(Integer[] vector) {
        for (int bit : vector) {
            System.out.print(bit);
        }
        System.out.println();
    }
}
