package SetsAndMaps_03.Exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class p05_Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        Map<String, String> phonebook = new HashMap<>();
        String input = scanner.nextLine();

        while (!input.equals("search")) {
            String[] phonebookEntry = input.split("-");
            String name = phonebookEntry[0];
            String phoneNumber = phonebookEntry[1];
            phonebook.put(name, phoneNumber);
            input = scanner.nextLine();
        }

        input = scanner.nextLine();
        while (!input.equals("stop")) {
            String name = input;
            String phoneNumber = phonebook.get(name);
            if (phoneNumber != null) {
                System.out.println(name + " -> " + phoneNumber);
            } else {
                System.out.println("Contact " + name + " does not exist.");
            }
            input = scanner.nextLine();
        }
    }
}
