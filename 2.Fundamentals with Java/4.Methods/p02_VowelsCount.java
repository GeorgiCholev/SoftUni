package Methods_4.Exercise;

import java.util.Scanner;

public class p02_VowelsCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        printVowelCount(input);
    }

    private static void printVowelCount(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
           char symbol = text.toLowerCase().charAt(i);
            if (symbol == 97 || symbol == 101 || symbol == 105 || symbol == 111 || symbol == 117 || symbol == 121) {
                count++;
            }
        }
        System.out.println(count);
    }
}
