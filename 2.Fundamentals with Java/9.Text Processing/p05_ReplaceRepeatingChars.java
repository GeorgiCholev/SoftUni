package TextProcessing_9.Exercise;

import java.util.Scanner;

public class p06_ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String line = scanner.nextLine();
        StringBuilder cutLine = new StringBuilder();
        cutLine.append(line.charAt(0));
        for (int i = 1; i < line.length(); i++) {
            if (cutLine.charAt(cutLine.length() - 1) != line.charAt(i)) {
                cutLine.append(line.charAt(i));
            }
        }
        System.out.println(cutLine);
    }
}
