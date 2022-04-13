package While_Loop_5.Exercise;

import java.util.Scanner;

public class p02_ExamPreparation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int poorGradesMax = Integer.parseInt(scanner.nextLine());
        String problemName = scanner.nextLine();

        int poorGradeCount = 0;
        boolean failed = false;
        double gradesTotal = 0.0;
        String lastProblem = "";
        int problemCount = 0;

        while (!problemName.equals("Enough")) {
            int grade = Integer.parseInt(scanner.nextLine());
            if (grade <= 4) {
                poorGradeCount++;
                if (poorGradeCount == poorGradesMax) {
                    failed = true;
                    break;
                }
            }
            problemCount++;
            gradesTotal += grade;
            lastProblem = problemName;
            problemName = scanner.nextLine();
        }
        if (failed) {
            System.out.printf("You need a break, %d poor grades.", poorGradeCount);
        } else {
            double average = gradesTotal / problemCount;
            System.out.printf("Average score: %.2f%n" +
                    "Number of problems: %d%n" +
                    "Last problem: %s", average, problemCount, lastProblem);
        }
    }
}
