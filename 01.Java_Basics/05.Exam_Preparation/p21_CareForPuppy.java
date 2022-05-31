package ExamPreparation;

import java.util.Scanner;

public class CareForPuppy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalFood = (Integer.parseInt(scanner.nextLine()) * 1000);
        String input = scanner.nextLine();

        boolean enough = true;

        while (!input.equals("Adopted")) {
            int foodRation = Integer.parseInt(input);
            if (foodRation > totalFood) {
                enough = false;
            }
            totalFood -= foodRation;

            input = scanner.nextLine();
        }
        int diff = Math.abs(totalFood);
        if (!enough) {
            System.out.printf("Food is not enough. You need %d grams more.", diff);
        } else {
            System.out.printf("Food is enough! Leftovers: %d grams.", totalFood);
        }
    }
}
