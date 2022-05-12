package MidExam_6.Pre;

import java.util.Scanner;

public class p03_HeartDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        int[] houses = stringToIntArray(input);
        String command = scanner.nextLine();
        int cupidPosition = 0;
        while (!command.equals("Love!")) {
        String[] commandParts = command.split(" ");
        int length = Integer.parseInt(commandParts[1]);
        cupidPosition = cupidPosition + length;
        if (cupidPosition >= houses.length) {
            cupidPosition = 0;
        }
        if (houses[cupidPosition] == 0) {
            System.out.printf("Place %d already had Valentine's day.%n", cupidPosition);
        } else {
            houses[cupidPosition] -= 2;
            if (houses[cupidPosition] == 0) {
                System.out.printf("Place %d has Valentine's day.%n", cupidPosition);
            }
        }
            command = scanner.nextLine();
        }
        System.out.printf("Cupid's last position was %d.%n", cupidPosition);
        int failedHouse = 0;
        for (int house : houses) {
            if (house != 0) {
                failedHouse++;
            }
        }
        if (failedHouse == 0) {
            System.out.println("Mission was successful.");
        } else {
            System.out.printf("Cupid has failed %d places.", failedHouse);
        }

    }

    private static int[] stringToIntArray(String input) {
        String[] stringSplit = input.split("@");
        int[] numbers = new int[stringSplit.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(stringSplit[i]);
        }
        return numbers;
    }
}
