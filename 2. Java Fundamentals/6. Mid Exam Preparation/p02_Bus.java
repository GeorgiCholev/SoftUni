package MidExam_6.Exam;

import java.util.Scanner;

public class p01_Bus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberCities = Integer.parseInt(scanner.nextLine());
        double totalProfit = 0.0;
        for (int i = 1; i <= numberCities; i++) {
            double profit;
            String cityName = scanner.nextLine();
            double income = Double.parseDouble(scanner.nextLine());
            double expenses = Double.parseDouble(scanner.nextLine());
            if (i % 5 == 0) {
                income *= 0.9;
            } else if (i % 3 == 0) {
                expenses *= 1.5;
            }
            profit = income - expenses;
            totalProfit += profit;
            System.out.printf("In %s Burger Bus earned %.2f leva.%n",cityName, profit);
        }
        System.out.printf("Burger Bus total profit: %.2f leva.", totalProfit);
    }
}
