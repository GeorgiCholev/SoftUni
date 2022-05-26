package SetsAndMaps_03.Lab;

import java.util.*;
import java.util.stream.Collectors;

public class p03_NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> firstPlayerDeck = Arrays.stream(scanner.nextLine().split("\\s"))
                .map(Integer::parseInt).collect(Collectors.toCollection(LinkedHashSet::new));

        Set<Integer> secondPlayerDeck = Arrays.stream(scanner.nextLine().split("\\s"))
                .map(Integer::parseInt).collect(Collectors.toCollection(LinkedHashSet::new));
        boolean gameOver = false;
        for (int i = 0; i < 50; i++) {

            int firstPlayerCard = firstPlayerDeck.iterator().next();
            firstPlayerDeck.remove(firstPlayerCard);

            int secondPlayerCard = secondPlayerDeck.iterator().next();
            secondPlayerDeck.remove(secondPlayerCard);

            if (firstPlayerCard > secondPlayerCard) {
                firstPlayerDeck.add(firstPlayerCard);
                firstPlayerDeck.add(secondPlayerCard);
            } else if (firstPlayerCard < secondPlayerCard){
                secondPlayerDeck.add(firstPlayerCard);
                secondPlayerDeck.add(secondPlayerCard);
            }

            if (firstPlayerDeck.isEmpty()) {
                System.out.println("Second player win!");
                gameOver = true;
                break;
            }
            if (secondPlayerDeck.isEmpty()) {
                System.out.println("First player win!");
                gameOver = true;
                break;
            }
        }
        if (!gameOver) {
            if (firstPlayerDeck.size() > secondPlayerDeck.size()) {
                System.out.println("First player win!");
            } else if (firstPlayerDeck.size() < secondPlayerDeck.size()) {
                System.out.println("Second player win!");
            } else {
                System.out.println("Draw!");
            }
        }
    }
}
