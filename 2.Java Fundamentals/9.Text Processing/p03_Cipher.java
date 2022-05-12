package TextProcessing_9.Exercise;

import java.util.Scanner;

public class p04_Cipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char[] cipherSymbols = new char[line.length()];

        for (int i = 0; i < line.length(); i++) {
            cipherSymbols[i] = (char) (line.charAt(i) + 3);
        }
        String cipher = new String(cipherSymbols);
        System.out.println(cipher);
    }
}

