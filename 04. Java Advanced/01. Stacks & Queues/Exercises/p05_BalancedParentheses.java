package StackQueue_01.Exercise;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p05_BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayDeque<Character> parentheses = new ArrayDeque<>();
        boolean balanced = false;
        if (input.length() % 2 == 0 && input.length() > 0) {
            boolean keepChecking = true;
            for (int i = 0; i < input.length(); i++) {
                char p = input.charAt(i);
                switch (p) {
                    case '}':
                        keepChecking = parenthesesCheck(parentheses, '{');
                        break;
                    case ']':
                        keepChecking = parenthesesCheck(parentheses, '[');
                        break;
                    case ')':
                        keepChecking = parenthesesCheck(parentheses, '(');
                        break;
                    default:
                        parentheses.push(p);
                        break;
                }
                if (!keepChecking) {
                    break;
                }
            }
            if (keepChecking) {
                balanced = true;
            }
        }
        if (balanced) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean parenthesesCheck(ArrayDeque<Character> parentheses, char toCheck) {
        if (parentheses.size() > 0) {
            if (parentheses.peek() == toCheck) {
                parentheses.pop();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
