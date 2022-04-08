package For_Loop_4.Lab;

import java.util.Scanner;

public class p05_CharacterSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        for (int n = 0; n <= text.length() - 1; n++) {
            char currentSymbol = text.charAt(n);
            System.out.println(currentSymbol);
        }
    }
}
