package SetsAndMaps_03.Lab;

import java.util.*;

public class p05_AverageStudentGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, ArrayList<Double>> gradesOfStudents = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String studentName = input[0];
            double grade = Double.parseDouble(input[1]);
            gradesOfStudents.putIfAbsent(studentName, new ArrayList<>());
            gradesOfStudents.get(studentName).add(grade);
        }
        for (var entry : gradesOfStudents.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            double sum = 0.0;
            for (double grade : entry.getValue()) {
                sum += grade;
                System.out.printf("%.2f ", grade);
            }
            System.out.printf("(avg: %.2f)", (sum / entry.getValue().size()));
            System.out.println();
        }
    }
}
