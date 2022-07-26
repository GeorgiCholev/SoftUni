package FunctionalProgramming_05.Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p05_FilterByAge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Person> people = new ArrayList<>(n);

        while (n-- > 0) {
            String[] pair = scanner.nextLine().split(", ");
            String name = pair[0];
            int age = Integer.parseInt(pair[1]);
            people.add(new Person(name, age));
        }

        String condition = scanner.nextLine();
        int ageFilter = Integer.parseInt(scanner.nextLine());
        String format = scanner.nextLine();

        Predicate<Person> predicate = getPredicate(condition, ageFilter);
        people = filterPeople(people, predicate);

        Consumer<Person> printer = getConsumer(format);
        people.forEach(printer);
    }

    private static Consumer<Person> getConsumer(String format) {
        switch (format) {
            case "name":
                return p -> System.out.println(p.getName());
            case "age":
                return p -> System.out.println(p.getAge());
            case "name age":
                return p -> System.out.println(p.getName() + " - " + p.getAge());
        }
        return null;
    }

    private static Predicate<Person> getPredicate(String condition, int ageFilter) {
        switch (condition) {
            case "younger":
                return p -> p.getAge() <= ageFilter;
            case "older":
                return p -> p.getAge() >= ageFilter;
        }
        return null;
    }

    private static List<Person> filterPeople(List<Person> people, Predicate<Person> predicate) {
         return people.stream().filter(predicate).collect(Collectors.toList());
    }

    static class Person {
        String name;
        int age;

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
