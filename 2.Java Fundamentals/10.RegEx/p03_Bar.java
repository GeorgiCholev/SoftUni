package RegEx_10.Exercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p03_Bar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String line = scanner.nextLine();
        String regEx = "%(?<customer>[A-Z][a-z]+)%[^|$.%]*" +
                       "<(?<product>\\w+)>[^|$.%]*" +
                       "\\|(?<count>[0-9]+)\\|[^|$.%0-9]*" +
                       "(?<price>[0-9]+\\.?[0-9]+)\\$$";
        Pattern pattern = Pattern.compile(regEx);
        double totalMoney = 0.0;
        while (!line.equals("end of shift")) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String name = matcher.group("customer");
                String product = matcher.group("product");
                int count = Integer.parseInt(matcher.group("count"));
                double price = Double.parseDouble(matcher.group("price"));
                double cost = price * count;
                totalMoney += cost;
                System.out.printf("%s: %s - %.2f\n", name, product, cost);
            }
            line = scanner.nextLine();
        }
        System.out.printf("Total income: %.2f", totalMoney);
    }
}
