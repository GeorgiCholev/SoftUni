package For_Loop_4.Exercise;

import java.util.Scanner;

public class p04_CleverLily {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lilyAge = Integer.parseInt(scanner.nextLine());
        double washingMachinePrice = Double.parseDouble(scanner.nextLine());
        int toyPrice = Integer.parseInt(scanner.nextLine());

        int savedMoney = 0;
        int savedMoneyToys = 0;
        int evenBirthdays = 0;
        int oddBirthdays = 0;
        for (int i = 2; i <= lilyAge; i += 2) {
            savedMoney += 9 + (evenBirthdays * 10);
            evenBirthdays++;
        }
        for (int i = 1; i <= lilyAge; i += 2) {
            savedMoneyToys += toyPrice;
        }
        int totalSavings = savedMoney + savedMoneyToys;
        if (totalSavings >= washingMachinePrice) {
            double difference = totalSavings - washingMachinePrice;
            System.out.printf("Yes! %.2f", difference);
        } else {
            double difference = washingMachinePrice - totalSavings;
            System.out.printf("No! %.2f", difference);
        }
    }

}

