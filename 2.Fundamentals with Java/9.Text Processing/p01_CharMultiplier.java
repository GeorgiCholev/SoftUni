package TextProcessing_9.Exercise;

import java.util.Scanner;

public class p02_CharMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String line = scanner.nextLine();
        int sum = sumStringsChars(line);
        System.out.println(sum);
    }

    private static int sumStringsChars(String line) {
        String[] lines = line.split("\\s+");
        int sum = 0;
        int counter = 0;
        while (counter < lines[0].length() && counter < lines[1].length()) {
            sum += lines[0].charAt(counter) * lines[1].charAt(counter);
            counter++;
        }
        if (counter == lines[0].length()) {
            for (int i = counter; i < lines[1].length(); i++) {
                sum += lines[1].charAt(i);
            }
        } else {
            for (int i = counter; i < lines[0].length(); i++) {
                sum += lines[0].charAt(i);
            }
        }
        return sum;
    }
}
