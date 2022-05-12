package Classes_7.Exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class p03_Opinion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Person> poll = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] components = line.split(" ");
            String name = components[0];
            int age = Integer.parseInt(components[1]);
            Person person = new Person(name, age);
            poll.add(person);
        }


        poll.stream()
                .sorted(Comparator.comparing(p -> p.name))
                .filter(person -> person.age > 30)
                .forEach(System.out::print);
    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("%s - %d%n", this.name, this.age);
        }
    }
}
