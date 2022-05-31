package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p11_Rage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int lostGames = Integer.parseInt(scanner.nextLine());
        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());
        int brokenHeadset = 0;
        int brokenMouse = 0;
        int brokenKeyboard = 0;
        int brokenDisplay = 0;

        for (int i = 1; i <= lostGames; i++) {
            if (i % 2 == 0) {
                brokenHeadset++;
            }
            if (i % 3 == 0) {
                brokenMouse++;
            }
            if (i % 2 == 0 && i % 3 == 0) {
                brokenKeyboard++;
                if (brokenKeyboard % 2 == 0 && brokenKeyboard != 0) {
                    brokenDisplay++;
                }
            }

        }
        double totalCost = (brokenHeadset * headsetPrice) + (brokenMouse * mousePrice) + (brokenKeyboard * keyboardPrice)
                + (brokenDisplay * displayPrice);
        System.out.printf("Rage expenses: %.2f lv.", totalCost);
    }
}
