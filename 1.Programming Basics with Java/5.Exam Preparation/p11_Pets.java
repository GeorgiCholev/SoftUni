package ExamPreparation;

import java.util.Scanner;

public class p04_1_Pets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberDays = Integer.parseInt(scanner.nextLine());
        double totalQuantityFood = Double.parseDouble(scanner.nextLine());

        double totalDogFood = 0.0;
        double totalCatFood = 0.0;
        double totalFood = 0.0;
        double totalBiscuits = 0.0;


        for (int i = 1; i <= numberDays; i++) {
            int dogConsumed = Integer.parseInt(scanner.nextLine());
            int catConsumed = Integer.parseInt(scanner.nextLine());
            totalDogFood += dogConsumed;
            totalCatFood += catConsumed;
            totalFood += catConsumed + dogConsumed;
            if (i % 3 == 0) {
                totalBiscuits += (catConsumed + dogConsumed) * 0.1;
            }
        }
        double percentLeft = (totalFood / totalQuantityFood) * 100;
        double percentLeftDog =(totalDogFood / totalFood) * 100;
        double percentLeftCat =(totalCatFood / totalFood) * 100;
        double totalBiscuitsRounded = Math.round(totalBiscuits);

        System.out.printf("Total eaten biscuits: %.0fgr.%n", totalBiscuitsRounded);
        System.out.printf("%.2f%% of the food has been eaten.%n", percentLeft);
        System.out.printf("%.2f%% eaten from the dog.%n", percentLeftDog);
        System.out.printf("%.2f%% eaten from the cat.%n", percentLeftCat);

    }
}
