package TextProcessing_9.Exercise;

import java.util.Scanner;

public class p03_ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char extensionChar = '.';
        char forNameChar = '\\';
        String extension = "";
        String name = "";
        int extensionIndex = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) == extensionChar) {
                extension = input.substring(i + 1);
                extensionIndex = i;
            } else if (input.charAt(i) == forNameChar) {
                name = input.substring(i + 1, extensionIndex);
                break;
            }
        }
        System.out.println("File name: " + name);
        System.out.println("File extension: " + extension);
    }
}
