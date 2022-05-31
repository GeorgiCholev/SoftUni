package StackQueue_01.Exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class p04_BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] operations = scanner.nextLine().split("\\s+");
        int N = Integer.parseInt(operations[0]);
        int S = Integer.parseInt(operations[1]);
        int X = Integer.parseInt(operations[2]);
        String[] numbers = scanner.nextLine().split("\\s+");
        Deque<Integer> numberQueue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            numberQueue.offer(Integer.parseInt(numbers[i]));
        }
        for (int i = 0; i < S; i++) {
            numberQueue.poll();
        }
        if (numberQueue.contains(X)) {
            System.out.println("true");
        } else {
            Integer min = numberQueue.stream().min(Integer::compareTo).orElse(0);
            System.out.println(min);
        }
    }
}
