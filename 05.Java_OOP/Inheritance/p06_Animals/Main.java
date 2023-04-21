package _3_Inheritance.p06_Animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!"Beast!".equals(input)) {
            String animalClass = input;

            input = scanner.nextLine();

            String[] animalProperties = input.split(" ");
            String name = animalProperties[0];
            int age = Integer.parseInt(animalProperties[1]);
            String gender = animalProperties[2];

            createAnInstance(animalClass, name, age, gender);

            input = scanner.nextLine();
        }
    }

    private static void createAnInstance(String animalClass, String name, int age, String gender) {
        try {
            Animal animalInstance;
            switch (animalClass) {
                case "Dog":
                    animalInstance = new Dog(name, age, gender);
                    break;
                case "Frog":
                    animalInstance = new Frog(name, age, gender);
                    break;
                case "Cat":
                    animalInstance = new Cat(name, age, gender);
                    break;
                case "Tomcat":
                    animalInstance = new Tomcat(name, age);
                    break;
                default: // "Kitten"
                    animalInstance = new Kitten(name, age);
                    break;
            }
            printSound(animalInstance);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printSound(Animal animalInstance) {
        System.out.println(animalInstance.toString());
    }
}
