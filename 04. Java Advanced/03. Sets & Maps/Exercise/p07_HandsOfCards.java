package SetsAndMaps_03.Exercise;

import java.util.*;

public class p07_HandsOfCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, HashSet<String>> playerWithCards = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("JOKER")) {
            String[] turn = input.split(": ");
            String player = turn[0];
            String[] cards = turn[1].split(", ");
            playerWithCards.putIfAbsent(player, new HashSet<>());
            Collections.addAll(playerWithCards.get(player), cards);
            input = scanner.nextLine();
        }
        for (var player : playerWithCards.entrySet()) {
            int sum = 0;
            for (String card : player.getValue()) {
                String power = card.substring(0, card.length() - 1);
                char type = card.charAt(card.length() - 1);
                sum += (calculatePower(power) * calculateType(type));
            }
            System.out.println(player.getKey() + ": " + sum);
        }
    }

    private static int calculateType(char type) {
        switch (type) {
            case 'S':
                return 4;
            case 'H':
                return 3;
            case 'D':
                return 2;
            case 'C':
                return 1;
            default:
                return 0;
        }
    }

    private static int calculatePower(String power) {
        switch (power) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(power + "");
        }
    }
}
