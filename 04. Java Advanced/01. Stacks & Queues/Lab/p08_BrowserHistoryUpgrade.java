package StackQueue_01.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class p08_BrowserHistoryUpgrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> historyBack = new ArrayDeque<>();
        ArrayDeque<String> historyForward = new ArrayDeque<>();
        String direction = scanner.nextLine();
        while (!direction.equals("Home")) {

            if (direction.equals("back")) {
                if (historyBack.size() == 1 || historyBack.size() == 0) {
                    System.out.println("no previous URLs");
                } else {
                    historyForward.push(historyBack.pop());
                    System.out.println(historyBack.peek());
                }
            } else if (direction.equals("forward")) {
                if (historyForward.size() == 0) {
                    System.out.println("no next URLs");
                } else {
                    historyBack.push(historyForward.pop());
                    System.out.println(historyBack.peek());
                }
            } else {
                historyBack.push(direction);
                historyForward.clear();
                System.out.println(historyBack.peek());
            }
            direction = scanner.nextLine();
        }
    }
}
