package Fundamentals_ExamPrep_11.April14th2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02_MirrorWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile("([@#])(?<wordOne>[A-Za-z]{3,})\\1\\1(?<wordTwo>[A-Za-z]{3,})\\1");
        Matcher matcher = pattern.matcher(input);
        List<String> mirrorWords = new ArrayList<>();
        int validPairs = 0;
        while (matcher.find()) {
            validPairs++;
            String wordOne = matcher.group("wordOne");
            String wordTwo = matcher.group("wordTwo");
            boolean different = false;
            if (wordOne.length() == wordTwo.length()) {
                for (int i = 0; i < wordOne.length(); i++) {
                    if (wordOne.charAt(i) != wordTwo.charAt(wordTwo.length() - 1 - i)) {
                        different = true;
                        break;
                    }
                }
                if (!different) {
                    mirrorWords.add(wordOne + " <=> " + wordTwo);
                }
            }
        }
        if (validPairs != 0) {
            System.out.println(validPairs + " word pairs found!");
        } else {
            System.out.println("No word pairs found!");
        }
        if (mirrorWords.size() != 0) {
            System.out.println("The mirror words are:");
            System.out.println(String.join(", ", mirrorWords));
        } else {
            System.out.println("No mirror words!");
        }
    }
}
