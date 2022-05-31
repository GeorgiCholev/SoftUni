package StackQueue_01.Exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class p02_BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] operations = scanner.nextLine().split("\\s+");
        int N = Integer.parseInt(operations[0]);
        int S = Integer.parseInt(operations[1]);
        int X = Integer.parseInt(operations[2]);
        String[] numbers = scanner.nextLine().split("\\s+");
        Deque<Integer> numberStack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            numberStack.push(Integer.parseInt(numbers[i]));
        }
        for (int i = 0; i < S; i++) {
            numberStack.pop();
        }
        if (numberStack.contains(X)) {
            System.out.println("true");
        } else {
            Integer min = numberStack.stream().min(Integer::compareTo).orElse(0);
            System.out.println(min);
        }

    }
}
