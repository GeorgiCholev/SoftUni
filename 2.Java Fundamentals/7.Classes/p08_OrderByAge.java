package Classes_7.Exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class p07_OrderByAge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Person> people = new ArrayList<>();
        while (!line.equals("End")) {
            String[] components = line.split("\\s+");
            String name = components[0];
            String id = components[1];
            int age = Integer.parseInt(components[2]);
            Person nextPerson = new Person(name, id, age);
            people.add(nextPerson);
            line = scanner.nextLine();
        }
        people.stream().sorted(Comparator.comparingInt(Person::getAge))
                .forEach(person -> System.out.println(person.getInfo()));
    }

    static class Person {
        String name;
        String id;
        int age;

        Person(String name, String id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        String getInfo() {
            return String.format("%s with ID: %s is %d years old.", this.name, this.id, this.age);
        }

        int getAge() {
            return this.age;
        }

    }
}
