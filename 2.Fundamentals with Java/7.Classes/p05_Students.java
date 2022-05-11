package Classes_7.Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p05_Students {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Student> students = new ArrayList<>();
        while (!line.equals("end")) {
            String[] currentStudent = line.split(" ");
            String firstName = currentStudent[0];
            String lastName = currentStudent[1];
            int age = Integer.parseInt(currentStudent[2]);
            String city = currentStudent[3];
            students.add(new Student(firstName, lastName, age, city));
            line = scanner.nextLine();
        }

        String city = scanner.nextLine();
        for (Student student : students) {
            if (student.isFrom(city)) {
               System.out.println(student.getInfo());
            }
        }
    }

    static class Student {
        String firstName;
        String lastName;
        int age;
        String city;

        Student(String firstName, String lastName, int age, String city) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.city = city;
        }

        boolean isFrom(String city) {
            return this.city.equals(city);
        }
        String getInfo() {
            return  String.format("%s %s is %d years old", this.firstName, this.lastName, this.age);
        }

    }
}


