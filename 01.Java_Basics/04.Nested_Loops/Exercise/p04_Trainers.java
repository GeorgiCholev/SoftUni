package Nested_Loops_6.Exercise;

import java.util.Scanner;

public class p04_Trainers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int jury = Integer.parseInt(scanner.nextLine());
        String presentation = scanner.nextLine();

        int numberPresentations = 0;
        double averagePresentationsTotal = 0.0;

        while (!presentation.equals("Finish")) {
            numberPresentations++;
            double gradeTotal = 0.0;
            double averageGrade;
            for (int i = 1; i <= jury; i++) {
                double grade = Double.parseDouble(scanner.nextLine());
                gradeTotal += grade;
            }
            averageGrade = gradeTotal / jury;
            averagePresentationsTotal += averageGrade;
            System.out.printf("%s - %.2f.%n", presentation, averageGrade);
            presentation = scanner.nextLine();
        }
        System.out.printf("Student's final assessment is %.2f.", (averagePresentationsTotal / numberPresentations));
    }
}
