package TextProcessing_9.More_Exercise;

import java.util.Scanner;

public class p03_TreasureFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int[] keys = fillArray(line);
        line = scanner.nextLine();
        while (!line.equals("find")) {
            String deciphered = decipherLine(line, keys);
            String treasury = findTreasury(deciphered);
            System.out.println(treasury);
            line = scanner.nextLine();
        }
    }

    private static String findTreasury(String deciphered) {
        StringBuilder current = new StringBuilder("Found ");
        fillTreasuryInfo(current, '&', '&', deciphered, " at ");
        fillTreasuryInfo(current, '<', '>', deciphered, "");
        return current.toString();
    }

    private static void fillTreasuryInfo(StringBuilder current, char start, char end, String deciphered, String append) {
        int index = deciphered.indexOf(start);
        for (int i = index + 1; i < deciphered.length(); i++) {
            if (deciphered.charAt(i) != end) {
                current.append(deciphered.charAt(i));
            } else {
                current.append(append);
                break;
            }
        }
    }

    private static String decipherLine(String line, int[] keys) {
        StringBuilder current = new StringBuilder(line);
        for (int i = 0; i < current.length(); i++) {
            char symbol = (char) (current.charAt(i) - keys[i % keys.length]);
            current.deleteCharAt(i);
            current.insert(i, symbol);
        }
        return current.toString();
    }

    private static int[] fillArray(String line) {
        String[] components = line.split("\\s+");
        int[] keys = new int[components.length];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = Integer.parseInt(components[i]);
        }
        return keys;
    }
}
