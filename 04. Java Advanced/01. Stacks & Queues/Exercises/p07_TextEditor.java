package StackQueue_01.Exercise;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p07_TextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operations = Integer.parseInt(scanner.nextLine());
        StringBuilder text = new StringBuilder();
        ArrayDeque<String> historyStack = new ArrayDeque<>();
        for (int i = 0; i < operations; i++) {
            String[] components = scanner.nextLine().split("\\s+");
            int command = Integer.parseInt(components[0]);
            switch (command) {
                case 1:
                    historyStack.push(text.toString());
                    appendAtEnd(components[1], text);
                    break;
                case 2:
                    historyStack.push(text.toString());
                    eraseElement(Integer.parseInt(components[1]), text);
                    break;
                case 3:
                    returnElement(Integer.parseInt(components[1]) - 1, text);
                    break;
                case 4:
                    text = undoCommand(historyStack);
                    break;
            }
        }
    }

    private static StringBuilder undoCommand(ArrayDeque<String> historyStack) {
        return new StringBuilder(historyStack.pop());
    }

    private static void returnElement(int index, StringBuilder text) {
        System.out.println(text.charAt(index));
    }

    private static void eraseElement(int count, StringBuilder text) {
        text.delete(text.length() - count, text.length());
    }

    private static void appendAtEnd(String element, StringBuilder text) {
        text.append(element);
    }
}
