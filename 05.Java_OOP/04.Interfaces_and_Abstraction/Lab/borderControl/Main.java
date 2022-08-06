package _4_Abstraction_and_Interfaces.Lab.borderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        List<Identifiable> passengers = new ArrayList<>();

        while (!"End".equals(line)) {

            String[] identityData = line.split("\\s+");

            if (identityData.length == 3) {
                Identifiable human = new Citizen(identityData[0], Integer.parseInt(identityData[1]), identityData[2]);
                passengers.add(human);
            } else {
                Identifiable robot = new Robot(identityData[1], identityData[0]);
                passengers.add(robot);
            }

            line = scanner.nextLine();
        }

        String fakeIdLastDigit = scanner.nextLine();

        passengers.stream().filter(p -> p.getId().endsWith(fakeIdLastDigit))
                .forEach(p -> System.out.println(p.getId()));
    }
}
