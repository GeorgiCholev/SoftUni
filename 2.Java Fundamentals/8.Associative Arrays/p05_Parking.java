package Maps_8.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p05_Parking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, String> licensePlateByName = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] components = line.split("\\s+");
            String command = components[0];
            String username = components[1];
            String licensePlate;
            boolean alreadyRegistered = licensePlateByName.containsKey(username);
            switch (command) {
                case "register":
                    licensePlate = components[2];
                    if (alreadyRegistered) {
                        System.out.println("ERROR: already registered with plate number " + licensePlateByName.get(username));
                    } else {
                        licensePlateByName.put(username, licensePlate);
                        System.out.println(username + " registered " + licensePlate + " successfully");
                    }
                    break;
                case "unregister":
                    if (alreadyRegistered) {
                        licensePlateByName.remove(username);
                        System.out.println(username + " unregistered successfully");
                    } else {
                        System.out.println("ERROR: user " + username + " not found");
                    }
                    break;
            }
        }
        licensePlateByName.forEach((key, value) -> System.out.println(key + " => " + value));
    }

}
