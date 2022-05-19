package StackQueue_01.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p06_HotPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> childrenQueue = new ArrayDeque<>();
        String[] children = scanner.nextLine().split("\\s+");
        int counter = Integer.parseInt(scanner.nextLine());

        for (String child : children) {
            childrenQueue.offer(child);
        }

        while (childrenQueue.size() > 1) {
            for (int i = 1; i < counter; i++) {
                childrenQueue.offer(childrenQueue.poll());
            }
            System.out.println("Removed " + childrenQueue.poll());
        }
        System.out.println("Last is " + childrenQueue.peek());
    }
}

