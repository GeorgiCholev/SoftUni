package RegEx_10.Exercises;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02_Racer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Map<String, Integer> runOfParticipants = new LinkedHashMap<>();
        Arrays.stream(line.split(", ")).forEach(n -> runOfParticipants.put(n, 0));
        String runner = scanner.nextLine();
        Pattern pattern = Pattern.compile("([A-Za-z\\d])");
        while (!runner.equals("end of race")) {
            StringBuilder name = new StringBuilder();
            int distance = 0;
            Matcher matcher = pattern.matcher(runner);
            while (matcher.find()) {
                if (matcher.group().matches("[A-Za-z]")) {
                    name.append(matcher.group(1));
                } else {
                    distance += Integer.parseInt(matcher.group());
                }
            }
            if (runOfParticipants.containsKey(name.toString())) {
                runOfParticipants.put(name.toString(), distance + runOfParticipants.get(name.toString()));
            }
            runner = scanner.nextLine();
        }
        List<String> names = new ArrayList<>();
        runOfParticipants.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(e -> names.add(e.getKey()));
        System.out.printf("1st place: %s\n" +
                "2nd place: %s\n" +
                "3rd place: %s\n", names.get(0), names.get(1), names.get(2));
    }
}