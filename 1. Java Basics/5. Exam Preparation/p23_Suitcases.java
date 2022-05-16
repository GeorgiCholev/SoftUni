package ExamPreparation;

import java.util.Scanner;

public class Suitcases {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double capacity = Double.parseDouble(scanner.nextLine());
        String input = scanner.nextLine();

        int counter = 0;
        boolean full = false;

        while (!input.equals("End")) {
            double suitcaseVolume = Double.parseDouble(input);
            counter++;
            if (counter % 3 == 0) {
                suitcaseVolume *= 1.1;
            }
            if (suitcaseVolume <= capacity) {
                capacity -= suitcaseVolume;
            } else {
                counter--;
                full = true;
                break;
            }
            input = scanner.nextLine();
        }
        if (full) {
            System.out.println("No more space!");
        } else {
            System.out.println("Congratulations! All suitcases are loaded!");
        }
        System.out.printf("Statistic: %d suitcases loaded.", counter);
    }
}
