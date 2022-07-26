package SetsAndMaps_03.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p06_FixedEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        Map<String, String> emailsByOwners = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("stop")) {
            String name = input;
            input = scanner.nextLine();
            String email = input;
            if ((!email.toLowerCase().endsWith("com")) &&
                    (!email.toLowerCase().endsWith("us")) && (!email.toLowerCase().endsWith("uk"))) {
                emailsByOwners.put(name, email);
            }
            input = scanner.nextLine();
        }
        emailsByOwners.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
