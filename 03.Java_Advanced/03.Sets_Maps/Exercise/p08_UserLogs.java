package SetsAndMaps_03.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class p08_UserLogs {
    public static void main(String[] args) {

        Map<String, Map<String, Integer>> ipCountByUser = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"end".equals(input = scanner.nextLine())) {
            String[] userLogData = input.split("\\s");
            String ip = userLogData[0].substring(3);
            String userName = userLogData[2].substring(5);

            ipCountByUser.putIfAbsent(userName, new LinkedHashMap<>());
            Map<String, Integer> countOfIps = ipCountByUser.get(userName);
            Integer ipOccurrences = countOfIps.get(ip);
            if (ipOccurrences != null) {
                countOfIps.put(ip, ++ipOccurrences);
            } else {
                countOfIps.put(ip, 1);
            }
        }

        ipCountByUser.entrySet()
                .stream().map(e -> e.getKey() + ": " + System.lineSeparator() +
                        e.getValue().entrySet().stream()
                                .map(valueEntry -> valueEntry.getKey() + " => " + valueEntry.getValue())
                                .collect(Collectors.joining(", ")))
                .forEach(user -> System.out.println(user + "."));
    }
}
