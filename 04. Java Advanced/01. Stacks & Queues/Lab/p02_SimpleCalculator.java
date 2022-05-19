package StackQueue_01.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p02_SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> dataStack = new ArrayDeque<>();
        String[] input = scanner.nextLine().split("\\s+");
        for (int i = input.length - 1; i >= 0; i--) {
            dataStack.push(input[i]);
        }

        while (dataStack.size() > 1) {
            int num1 = Integer.parseInt(dataStack.pop());
            String operator = dataStack.pop();
            int num2 = Integer.parseInt(dataStack.pop());
            switch (operator) {
                case "+":
                    dataStack.push(String.valueOf(num1 + num2));
                    break;
                case "-":
                    dataStack.push(String.valueOf(num1 - num2));
                    break;
            }
        }
        System.out.println(dataStack.peek());
    }
}
