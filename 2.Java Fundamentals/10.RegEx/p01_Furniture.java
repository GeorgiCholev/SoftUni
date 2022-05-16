package RegEx_10.Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p01_Furniture {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(">>(?<furniture>\\w+)<<(?<price>[\\d.]+)!(?<quantity>[0-9]+)");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<String> furnitures = new ArrayList<>();
        double priceTotal = 0.0;
        while (!line.equals("Purchase")) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                furnitures.add(matcher.group("furniture"));
                double price = Double.parseDouble(matcher.group("price"));
                double quantity = Double.parseDouble(matcher.group("quantity"));
                priceTotal += (price * quantity);
            }
            line = scanner.nextLine();
        }
        System.out.println("Bought furniture:");
        furnitures.forEach(System.out::println);
        System.out.printf("Total money spend: %.2f", priceTotal);
    }

}


