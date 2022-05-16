package Final_Exam;

import java.util.Scanner;

public class p01_StringGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder word = new StringBuilder(scanner.nextLine());
        String input = scanner.nextLine();
        while (!input.equals("Done")) {
            String[] components = input.split(" ");
            String command = components[0];
            switch (command) {
                case "Change":
                    String toRemove = components[1];
                    String replacement = components[2];
                    int index = word.indexOf(toRemove);
                    while (index != -1) {
                        word.replace(index, index + 1, replacement);
                        index = word.indexOf(toRemove, index + replacement.length());
                    }
                    System.out.println(word);
                    break;
                case "Includes":
                    String toCheck = components[1];
                    boolean exists = word.indexOf(toCheck) != -1;
                    if (exists) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "End":
                    String endingToCheck = components[1];
                    int i = word.lastIndexOf(endingToCheck) + endingToCheck.length() - 1;
                    if (i == word.length() - 1) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "Uppercase":
                    for (int j = 0; j < word.length(); j++) {
                        char c = Character.toUpperCase(word.charAt(j));
                        word.setCharAt(j, c);
                    }
                    System.out.println(word);
                    break;
                case "FindIndex":
                    String find = components[1];
                    System.out.println(word.indexOf(find));
                    break;
                case "Cut":
                    int startIndex = Integer.parseInt(components[1]);
                    int count = Integer.parseInt(components[2]);
                    String substr = word.substring(startIndex, startIndex + count);
                    word = new StringBuilder(substr);
                    System.out.println(word);
                    break;
            }
            input = scanner.nextLine();
        }
    }
}
