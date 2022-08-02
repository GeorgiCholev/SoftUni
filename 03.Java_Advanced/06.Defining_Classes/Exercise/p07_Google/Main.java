package DefiningClasses_06.Exercise.p07_Google;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Person> peopleByName = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"End".equals(input = scanner.nextLine())) {
            String[] components = input.split("\\s");

            peopleByName.putIfAbsent(components[0], new Person(components[0]));
            Person person = peopleByName.get(components[0]);

            String field = components[1];
            switch (field) {
                case "company":
                    setCompanyTo(person, components);
                    break;
                case "pokemon":
                    addPokemon(person, components);
                    break;
                case "parents":
                    addParent(person, components);
                    break;
                case "children":
                    addChild(person, components);
                    break;
                case "car":
                    setCarTo(person, components);
                    break;
            }
        }

        String name = scanner.nextLine();
        System.out.println(peopleByName.get(name).getInfo());
    }

    private static void setCarTo(Person person, String[] components) {
        String model = components[2];
        int speed = Integer.parseInt(components[3]);
        person.setCar(model, speed);
    }

    private static void addChild(Person person, String[] components) {
        String name = components[2];
        String birthday = components[3];
        person.addChild(name, birthday);
    }

    private static void addParent(Person person, String[] components) {
        String name = components[2];
        String birthday = components[3];
        person.addParent(name, birthday);
    }

    private static void addPokemon(Person person, String[] components) {
        String name = components[2];
        String type = components[3];
        person.addPokemon(name, type);
    }

    private static void setCompanyTo(Person person, String[] components) {
        String name = components[2];
        String department = components[3];
        double salary = Double.parseDouble(components[4]);
        person.setCompany(name, department, salary);
    }

}
