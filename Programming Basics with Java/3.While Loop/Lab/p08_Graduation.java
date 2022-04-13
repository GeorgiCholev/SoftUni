package While_Loop_5.Lab;

import java.util.Scanner;

public class p08_Graduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        double mark = Double.parseDouble(scanner.nextLine());
        int grade = 1;
        double markSum = 0.0;

        while (true) {
            if (mark < 4.00) {
                mark = Double.parseDouble(scanner.nextLine());
                if (mark < 4.00) {
                    System.out.printf("%s has been excluded at %d grade", name, grade);
                    break;
                }
            }
            markSum += mark;
            grade++;
            if (grade > 12) {
                break;
            }
            mark = Double.parseDouble(scanner.nextLine());
        }
        if (grade > 12) {
            double averageMark = markSum / 12;
            System.out.printf("%s graduated. Average grade: %.2f", name, averageMark);
        }
    }
}
