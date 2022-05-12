package Maps_8.Exercise;

import java.util.*;

public class p09_ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, List<String>> usersOnSide = new LinkedHashMap<>();
        while (!input.equals("Lumpawaroo")) {
            String[] components = splitToArray(input);
            String forceSide;
            String forceUser;
            if (input.contains("|")) {
                forceSide = components[0];
                forceUser = components[1];
                usersOnSide.putIfAbsent(forceSide, new ArrayList<>());
                boolean existsForceUser = usersOnSide.entrySet().stream()
                        .anyMatch(entry -> entry.getValue().stream().anyMatch(v -> v.equals(forceUser)));
                if (!existsForceUser) {
                    usersOnSide.get(forceSide).add(forceUser);

                }
            } else if (input.contains("->")) {
                forceSide = components[1];
                forceUser = components[0];
                usersOnSide.forEach((k, v) -> v.remove(forceUser));
                usersOnSide.putIfAbsent(forceSide, new ArrayList<>());
                usersOnSide.get(forceSide).add(forceUser);
                System.out.println(forceUser + " joins the " + forceSide + " side!");
            }
            input = scanner.nextLine();
        }

        usersOnSide.forEach((k, v) -> {
            if (v.size() != 0) {
                System.out.printf("Side: %s, Members: %d%n", k, v.size());
                v.forEach(user -> System.out.printf("! %s%n", user));
            }
        });
    }

    private static String[] splitToArray(String input) {
        String[] components;
        if (input.contains("->")) {
            components = input.split("\\s+->\\s+");
        } else {
            components = input.split("\\s+\\|\\s+");
        }
        return components;
    }
}
