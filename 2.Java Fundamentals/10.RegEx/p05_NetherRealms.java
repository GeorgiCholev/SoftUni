package RegEx_10.Exercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p05_NetherRealms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] demons = line.split("[,\\s]+");
        for (String demon : demons) {
            StringBuilder demonCharacteristics = new StringBuilder();
            demonCharacteristics.append(demon).append(" - ");
            int health = findHealthMethod(demon);
            demonCharacteristics.append(health).append(" health, ");
            double damageNumber = findDamageNumbers(demon);
            String totalDamage = findTotalDamage(damageNumber, demon);
            demonCharacteristics.append(totalDamage).append(" damage");
            System.out.println(demonCharacteristics);
        }
    }

    private static String findTotalDamage(double damageNumber, String demon) {
        Pattern pattern = Pattern.compile("[/*]");
        Matcher matcher = pattern.matcher(demon);
        while (matcher.find()) {
            if (matcher.group(0).equals("/")) {
                damageNumber /= 2;
            } else {
                damageNumber *= 2;
            }
        }
        return String.format("%.2f", damageNumber);
    }

    private static double findDamageNumbers(String demon) {
        double damageNumber = 0.0;
        Pattern findDamageNumbers = Pattern.compile("-?\\d+\\.?(\\d+)*");
        Matcher matcher = findDamageNumbers.matcher(demon);
        while (matcher.find()) {
            double number = Double.parseDouble(matcher.group(0));
            damageNumber += number;
        }
        return damageNumber;
    }

    private static int findHealthMethod(String demon) {
        Pattern findHealth = Pattern.compile("[^\\d+\\-*/.]");
        Matcher matcher = findHealth.matcher(demon);
        int health = 0;
        while (matcher.find()) {
            health += matcher.group().charAt(0);
        }
        return health;
    }
}
