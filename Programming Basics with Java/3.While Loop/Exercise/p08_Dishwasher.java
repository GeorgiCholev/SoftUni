package While_Loop_5.Extra;

import java.util.Scanner;

public class p01_Dishwasher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bottles = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        int mlDetergent = bottles * 750;
        int row = 1;
        boolean over = false;
        int neededDetergent = 0;
        int diff = 0;
        int dishes = 0;
        int pots = 0;

        while (!input.equals("End")) {
            int numberDishes = Integer.parseInt(input);
            if (row % 3 != 0) {
                neededDetergent = 5 * numberDishes;
                if (neededDetergent <= mlDetergent) {
                    mlDetergent -= neededDetergent;
                    dishes += numberDishes;
                } else {
                    over = true;
                    diff = neededDetergent - mlDetergent;
                    break;
                }
            } else {
                neededDetergent = 15 * numberDishes;
                if (neededDetergent <= mlDetergent) {
                    mlDetergent -= neededDetergent;
                    pots += numberDishes;
                } else {
                    over = true;
                    diff = neededDetergent - mlDetergent;
                    break;
                }
            }

            if (mlDetergent == 0) {
                break;
            }
            row++;
            input = scanner.nextLine();

        }
if (over) {
    System.out.printf("Not enough detergent, %d ml. more necessary!", diff);
} else {
    System.out.println("Detergent was enough!");
    System.out.printf("%d dishes and %d pots were washed.%n", dishes, pots);
    System.out.printf("Leftover detergent %d ml.", mlDetergent);
}

    }
}
