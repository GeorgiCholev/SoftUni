package PreExam_7;

import java.util.Scanner;

public class p01_CatDiet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double fatPercent = Integer.parseInt(scanner.nextLine()) * 1.0 / 100;
        double proteinPercent = Integer.parseInt(scanner.nextLine()) * 1.0 / 100;
        double carbPercent = Integer.parseInt(scanner.nextLine()) * 1.0 / 100;
        int caloriesTotal = Integer.parseInt(scanner.nextLine());
        double waterPercent = Integer.parseInt(scanner.nextLine()) * 1.0 /100;

        double fatGrams = (caloriesTotal * fatPercent) / 9;
        double proteinGrams = (caloriesTotal * proteinPercent) / 4;
        double carbGrams = (caloriesTotal * carbPercent) / 4;
        double totalGrams = fatGrams + proteinGrams + carbGrams;
        double caloriesPerGram = caloriesTotal / totalGrams;
        caloriesPerGram = caloriesPerGram * (1.00 - waterPercent);
        System.out.printf("%.4f", caloriesPerGram);
    }
}
