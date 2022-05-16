package Final_Exam;

import java.util.*;

public class p03_Dictionary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> dictionary = new LinkedHashMap<>();
        String input = scanner.nextLine();
        resolveInput(input, dictionary);
        input = scanner.nextLine();
        String command = scanner.nextLine();
        switch (command) {
            case "Test":
                String[] testWords = input.split(" \\| ");
                handleTheTest(dictionary, testWords);
                break;
            case "Hand Over":
                handleHandOver(dictionary);
                break;
        }
    }

    private static void handleHandOver(Map<String, List<String>> dictionary) {
        System.out.println(String.join(" ", dictionary.keySet()));
    }

    private static void handleTheTest(Map<String, List<String>> dictionary, String[] testWords) {
        for (String testWord : testWords) {
            if (dictionary.containsKey(testWord)) {
                System.out.println(testWord + ":");
                dictionary.get(testWord).forEach(d -> System.out.println(" -" + d));
            }
        }

    }

    private static void resolveInput(String input, Map<String, List<String>> dictionary) {
        String[] words = input.split(" \\| ");
        for (String word : words) {
            String[] wordSplit = word.split(": ");
            String wordToAdd = wordSplit[0];
            String definition = wordSplit[1];
            dictionary.putIfAbsent(wordToAdd, new ArrayList<>());
            dictionary.get(wordToAdd).add(definition);
        }

    }
}
