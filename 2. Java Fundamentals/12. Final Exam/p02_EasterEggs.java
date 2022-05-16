package Final_Exam;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02_EasterEggs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile("[@#]+(?<colour>[a-z]{3,})[@#]+[^A-Za-z\\d]*/+(?<amount>\\d+)/+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String colour = matcher.group("colour");
            String amount = matcher.group("amount");
            System.out.println("You found " + amount + " " + colour + " eggs!");
        }
    }
}
