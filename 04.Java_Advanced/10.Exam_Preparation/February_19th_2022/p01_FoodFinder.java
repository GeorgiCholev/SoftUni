package ExamPreparation_10.February_19th_2022;

import java.util.*;
import java.util.stream.Collectors;

public class p01_FoodFinder {
    public static void main(String[] args) {

        Map<Character, Boolean> pearLetters = new LinkedHashMap<>(4);
        fillMap(pearLetters, 'p', 'e', 'a', 'r', '/');

        Map<Character, Boolean> flourLetters = new LinkedHashMap<>(5);
        fillMap(flourLetters, 'f', 'l', 'o', 'u', 'r');

        Map<Character, Boolean> porkLetters = new LinkedHashMap<>(4);
        fillMap(porkLetters, 'p', 'o', 'r', 'k', '/');

        Map<Character, Boolean> oliveLetters = new LinkedHashMap<>(5);
        fillMap(oliveLetters, 'o', 'l', 'i', 'v', 'e');

        List<Map<Character, Boolean>> wordsFound = new ArrayList<>(Arrays.asList(
               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>()));

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Character> vowelsQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" ")).forEach(vowel -> vowelsQueue.offer(vowel.charAt(0)));
        ArrayDeque<Character> consonantStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" ")).forEach(consonant -> consonantStack.push(consonant.charAt(0)));

        while (!consonantStack.isEmpty()) {
            @SuppressWarnings("ConstantConditions")
            char vowel = vowelsQueue.poll();
            char consonant = consonantStack.pop();
            checkLetter(vowel, consonant, pearLetters, wordsFound, 0);
            checkLetter(vowel, consonant, flourLetters, wordsFound, 1);
            checkLetter(vowel, consonant, porkLetters, wordsFound, 2);
            checkLetter(vowel, consonant, oliveLetters, wordsFound, 3);

            vowelsQueue.offer(vowel);
        }

        wordsFound = wordsFound.stream().filter(m -> !m.isEmpty()).collect(Collectors.toList());
        System.out.println("Words found: " + wordsFound.size());
        wordsFound.forEach(m -> {
            m.forEach((key, value) -> System.out.print(key));
            System.out.println();
        });
    }

    private static void checkLetter(char vowel, char consonant, Map<Character, Boolean> map,
                                    List<Map<Character, Boolean>> wordsFound, int indexOfList) {
        int indexOfMap = wordsFound.indexOf(map);
        if (indexOfMap == -1) {
            if (map.containsKey(vowel)) {
                map.put(vowel, Boolean.TRUE);
            }
            if (map.containsKey(consonant)) {
                map.put(consonant, Boolean.TRUE);
            }
            boolean isWordFound = map.entrySet().stream().allMatch(Map.Entry::getValue);
            if (isWordFound) {
                wordsFound.set(indexOfList, map);
            }
        }
    }

    private static void fillMap(Map<Character, Boolean> map,
                                char one, char two, char three, char four, char five) {
        map.put(one, Boolean.FALSE);
        map.put(two, Boolean.FALSE);
        map.put(three, Boolean.FALSE);
        map.put(four, Boolean.FALSE);
        if (five != '/') {
            map.put(five, Boolean.FALSE);
        }
    }
}
