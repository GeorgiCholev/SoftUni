package StackQueue_01.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p01_BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> history = new ArrayDeque<>();
        String direction = scanner.nextLine();
        while (!direction.equals("Home")) {
            if (!direction.equals("back")) {
                history.push(direction);
                System.out.println(history.peek());
            } else {
                if (history.size() == 1 || history.size() == 0) {
                    System.out.println("no previous URLs");
                } else {
                    history.pop();
                    System.out.println(history.peek());
                }
            }
            direction = scanner.nextLine();
        }
    }
}
