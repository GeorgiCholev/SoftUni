package SetsAndMaps_03.Exercise;

import java.util.*;

public class p10_LogsAggregator {
    public static void main(String[] args) {
        Map<String, Integer> durationForUser = new TreeMap<>();
        Map<String, Set<String>> ipAddressesForUser = new HashMap<>();

        Scanner scanner = new Scanner (System.in);
        int numberOfLogs = Integer.parseInt(scanner.nextLine());
        while (numberOfLogs-- > 0) {
            String[] logInfo = scanner.nextLine().split("\\s");
            String ip = logInfo[0];
            String user = logInfo[1];
            int duration = Integer.parseInt(logInfo[2]);

            durationForUser.putIfAbsent(user, 0);
            durationForUser.put(user, durationForUser.get(user) + duration);

            ipAddressesForUser.putIfAbsent(user, new TreeSet<>());
            ipAddressesForUser.get(user).add(ip);
        }

        StringBuilder output = new StringBuilder();

        for (String user : durationForUser.keySet()) {
            Integer duration = durationForUser.get(user);
            String ipAddresses = String.join(", ", ipAddressesForUser.get(user));

            String entryOutput = user + ": " + duration + " [" + ipAddresses + "]";
            output.append(entryOutput).append(System.lineSeparator());
        }

        System.out.print(output.toString().trim());
    }
}
