package Maps_8.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p01_CountChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] letters = input.split("");
        Map<Character, Integer> countOfLetters = new LinkedHashMap<>();
        for (String letter : letters) {
            char symbol = letter.charAt(0);
            if (symbol != ' ') {
                if (countOfLetters.containsKey(symbol)) {
                    countOfLetters.put(symbol, countOfLetters.get(symbol) + 1);
                } else {
                    countOfLetters.put(symbol, 1);
                }
            }
        }
        countOfLetters.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
