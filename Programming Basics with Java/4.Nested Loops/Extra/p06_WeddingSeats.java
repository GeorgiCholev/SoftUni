package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p06_WeddingSeats {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String lastSector = scanner.nextLine();
        int numberRowsSector = Integer.parseInt(scanner.nextLine());
        int numberSeatsOddRow = Integer.parseInt(scanner.nextLine());
        char lastSectorChar = lastSector.charAt(0);
        int counter = 0;

        for (char i = 'A'; i <= lastSectorChar; i++) {
            for (int j = 1; j <= numberRowsSector; j++) {
                char kChar = 'a';
                if (j % 2 != 0) {
                    for (int k = 1; k <= numberSeatsOddRow; k++) {
                        System.out.println(i + "" + j + "" + kChar + "");
                        counter++;
                        kChar++;
                    }
                } else {
                    for (int k = 1; k <= numberSeatsOddRow + 2; k++) {
                        System.out.println(i + "" + j + "" + kChar + "");
                        counter++;
                        kChar++;
                    }
                }
            }
            numberRowsSector++;
        }
        System.out.println(counter);
    }
}
