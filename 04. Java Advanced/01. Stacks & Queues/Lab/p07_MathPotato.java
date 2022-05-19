package StackQueue_01.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p07_MathPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> childrenQueue = new ArrayDeque<>();
        String[] children = scanner.nextLine().split("\\s+");
        int counter = Integer.parseInt(scanner.nextLine());

        for (String child : children) {
            childrenQueue.offer(child);
        }

        int cycles = 1;
        while (childrenQueue.size() > 1) {
            for (int i = 1; i < counter; i++) {
                childrenQueue.offer(childrenQueue.poll());
            }
            if (!isPrime(cycles)) {
                System.out.println("Removed " + childrenQueue.poll());
            } else {
                System.out.println("Prime " + childrenQueue.peek());
            }
            cycles++;
        }
        System.out.println("Last is " + childrenQueue.peek());
    }

    private static boolean isPrime(int cycles) {
        boolean isPrime = true;
        if (cycles == 0 || cycles == 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= cycles / 2; i++) {
                if (cycles % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;
    }

}

