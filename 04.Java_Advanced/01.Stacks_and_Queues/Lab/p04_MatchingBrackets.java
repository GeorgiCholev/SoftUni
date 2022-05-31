package StackQueue_01.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p04_MatchingBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            switch (symbol) {
                case '(':
                    stack.push(i);
                    break;
                case ')':
                    System.out.println(input.substring(stack.pop(), i + 1));
                    break;
            }
        }
    }
}
