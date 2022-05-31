package StackQueue_01.Exercise;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p01_ReverseStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbers = input.split("\\s+");
        ArrayDeque<Integer> numberStack = new ArrayDeque<>(numbers.length);
        for (String number : numbers) {
            numberStack.push(Integer.parseInt(number));
        }
        while (!numberStack.isEmpty()) {
            if (numberStack.size() > 1) {
                System.out.print(numberStack.pop() + " ");
            } else {
                System.out.print(numberStack.pop());
            }
        }
    }
}
