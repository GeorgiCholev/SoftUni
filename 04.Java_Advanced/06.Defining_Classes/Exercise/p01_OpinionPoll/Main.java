package DefiningClasses_06.Exercise.p01_OpinionPoll;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int countOfInputInfo = Integer.parseInt(scanner.nextLine());

        Map<String, Person> people = new TreeMap<>();

        for (int i = 0; i < countOfInputInfo; i++) {
            String[] components = scanner.nextLine().split("\\s+");
            String name = components[0];
            int age = Integer.parseInt(components[1]);
            if (age > 30) {
                Person person = new Person(name, age);
                people.putIfAbsent(name, person);
            }
        }
        people.values().forEach(System.out::println);
    }
}
