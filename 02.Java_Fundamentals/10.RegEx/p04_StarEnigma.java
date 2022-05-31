package RegEx_10.Exercises;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p04_StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Pattern star = Pattern.compile("[starSTAR]");
        Map<String, List<String>> planetStatus = new LinkedHashMap<>(2);
        planetStatus.put("Attacked", new ArrayList<>());
        planetStatus.put("Destroyed", new ArrayList<>());
        for (int i = 0; i < n; i++) {
            StringBuilder line = new StringBuilder(scanner.nextLine());
            int countStars = getCountStars(star, line.toString());
            decrypt(line, countStars);
            Pattern planetInfo = Pattern.compile("@(?<name>[A-Za-z]+)[^@!:>-]*:(?<population>\\d+)[^@!:>-]*" +
                    "!(?<attackType>[AD])![^@!:>-]*->(?<soldiers>\\d+)");
            determinePlanetStatus(planetStatus, planetInfo, line.toString());
        }
        planetStatus.forEach((key, value) -> {
            System.out.println(key + " planets: " + value.size());
            value.stream().sorted(String::compareTo).forEach(p -> System.out.println("-> " + p));
        });
    }

    private static void determinePlanetStatus(Map<String, List<String>> planetStatus, Pattern planetInfo, String message) {
        Matcher matcher = planetInfo.matcher(message);
        if (matcher.find()) {
            String name = matcher.group("name");
            char status = matcher.group("attackType").charAt(0);
            switch (status) {
                case 'A':
                    planetStatus.get("Attacked").add(name);
                    break;
                case 'D':
                    planetStatus.get("Destroyed").add(name);
                    break;
            }
        }
    }

    private static void decrypt(StringBuilder encryptedMessage, int encryption) {
        for (int j = 0; j < encryptedMessage.length(); j++) {
            char replacement = (char) (encryptedMessage.charAt(j) - encryption);
            encryptedMessage.setCharAt(j, replacement);
        }
    }

    private static int getCountStars(Pattern star, String line) {
        int countStars = 0;
        Matcher matchStar = star.matcher(line);
        while (matchStar.find()) {
            countStars++;
        }
        return countStars;
    }
}
