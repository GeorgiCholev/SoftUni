package Fundamentals_ExamPrep_11.August9th2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02_Mapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<String> destinations = new ArrayList<>();
        Pattern pattern = Pattern.compile("([=/])(?<destination>[A-Z][a-zA-Z]{2,})\\1");
        Matcher matcher = pattern.matcher(line);
        int travelPoints = 0;
        while (matcher.find()) {
            String destination = matcher.group("destination");
            travelPoints += destination.length();
            destinations.add(destination);
        }
        System.out.print("Destinations: " + String.join(", ", destinations));
        System.out.println();
        System.out.println("Travel Points: " + travelPoints);
    }
}
