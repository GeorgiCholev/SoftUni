package Classes_7.Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p05_Students {

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] components = line.split(" ");
            String firstName = components[0];
            String lastName = components[1];
            double grade = Double.parseDouble(components[2]);
            Student student = new Student(firstName, lastName, grade);
            students.add(student);
        }
        students.stream().sorted((s1, s2) -> Double.compare(s2.getGrade(), s1.getGrade()))
                .forEach(s -> System.out.println(s.getInfo()));

    }
    static class Student {
        String firstName;
        String lastName;
        double grade;

        Student(String firstName, String lastName, double grade) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.grade = grade;
        }

        double getGrade() {
            return this.grade;
        }
        String getInfo() {
            return  String.format("%s %s: %.2f", this.firstName, this.lastName, this.grade);
        }
    }
}
