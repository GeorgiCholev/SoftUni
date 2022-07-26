package _2_CombinatorialProblems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p02_PermutationUnique {

    // Swap Algorithm for Permutations with repeating elements  {A, B, B}

    public static String[] elements;

    public static void main(String[] args) {

        Scanner scanner = new Scanner (System.in);
        elements = scanner.nextLine().split(" ");

        permute(0);
    }

    private static void permute(int index) {
        if (index == elements.length) {
            print();
            return;
        }
        Set<String> permutation = new HashSet<>(elements.length);
        permutation.add(elements[index]);
        permute(index + 1);
        for (int i = index + 1; i < elements.length; i++) {
            if (!permutation.contains(elements[i])){
                swap(index, i);
                permute(index + 1);
                swap(i, index);
                permutation.add(elements[i]);
            }
        }
    }

    private static void swap(int first, int second) {
        String temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
    }

    private static void print() {
        System.out.println(String.join(" ", elements));
    }
}
