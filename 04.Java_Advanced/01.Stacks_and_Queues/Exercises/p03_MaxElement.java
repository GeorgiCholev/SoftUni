package StackQueue_01.Exercise;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p03_MaxElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> numberStack = new ArrayDeque<>();
        int numberCommands = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> maxElement = new ArrayDeque<>();
        maxElement.push(0);
        for (int i = 0; i < numberCommands; i++) {
            String command = scanner.nextLine();
            if (command.charAt(0) == '1') {
                int element = Integer.parseInt(command.substring(2));
                numberStack.push(element);
                if (element > maxElement.peek()) {
                    maxElement.push(element);
                }
            } else if (command.charAt(0) == '2') {
                if (maxElement.peek() == numberStack.pop()) {
                    maxElement.pop();
                }
            } else if (command.charAt(0) == '3') {
                System.out.println(maxElement.peek());
            }
        }
    }
}
