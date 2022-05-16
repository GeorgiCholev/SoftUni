package Fundamentals_ExamPrep_11.August15th2020;

import java.util.Scanner;

public class p01_ImitationGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder message = new StringBuilder(scanner.nextLine());
        String command = scanner.nextLine();
        while (!command.equals("Decode")) {
            String[] components = command.split("\\|");
            String instruction = components[0];
            switch (instruction) {
                case "Move":
                    int numberLetters = Integer.parseInt(components[1]);
                    String movers = message.substring(0, numberLetters);
                    message.delete(0, numberLetters);
                    message.append(movers);
                    break;
                case "Insert":
                    int index = Integer.parseInt(components[1]);
                    String value = components[2];
                    message.insert(index, value);
                    break;
                case "ChangeAll":
                    String substring = components[1];
                    String replacement = components[2];
                    int indexStart = message.indexOf(substring);
                    while (indexStart != -1) {
                        message.replace(indexStart, indexStart + substring.length(), replacement);
                        indexStart = message.indexOf(substring, indexStart + replacement.length());
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.printf("The decrypted message is: %s", message);
    }
}
