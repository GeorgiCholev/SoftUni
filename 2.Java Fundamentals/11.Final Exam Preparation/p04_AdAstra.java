package Fundamentals_ExamPrep_11.August15th2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02_AdAstra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int totalCals = 0;
        List<String> items = new ArrayList<>();
        totalCals = getTotalCals(line, totalCals, items);
        System.out.printf("You have food to last you for: %d days!\n", (totalCals / 2000));
        items.forEach(System.out::println);
    }

    private static int getTotalCals(String line, int totalCals, List<String> items) {
        Pattern pattern = Pattern.compile("([#|])(?<item>[\\sa-zA-Z]+)\\1" +
                "(?<date>[0-9]{2}/[0-9]{2}/[0-9]{2})\\1(?<calories>\\d{1,5})\\1");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String item = matcher.group("item");
            String date = matcher.group("date");
            int calories = Integer.parseInt(matcher.group("calories"));
            totalCals += calories;
            String info = "Item: " + item + ", Best before: " + date + ", Nutrition: " + calories;
            items.add(info);
        }
        return totalCals;
    }
}
