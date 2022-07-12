package _4_Abstraction_and_Interfaces.Exercise.birthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;

        List<Birthable> birthableBeings = new ArrayList<>();

        while (!"End".equals(input = scanner.nextLine())) {

            String[] components = input.split("\\s+");

            String name = components[1];
            boolean isBirthable = false;
            String birthDate;
            Birthable being = null;

            switch (components[0]) {
                case "Citizen":
                    isBirthable = true;
                    int age = Integer.parseInt(components[2]);
                    String id = components[3];
                    birthDate = components[4];
                    being = new Citizen(name, age, id, birthDate);
                    break;
                case "Pet":
                    isBirthable = true;
                    birthDate = components[2];
                    being = new Pet(name, birthDate);
                    break;
            }

            if (isBirthable) {
                birthableBeings.add(being);
            }

        }

        String birthYearFilter = scanner.nextLine();

        birthableBeings.stream().map(Birthable::getBirthDate)
                .filter(date -> date.endsWith(birthYearFilter))
                .forEach(System.out::println);
    }
}
