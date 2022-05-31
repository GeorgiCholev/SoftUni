package SetsAndMaps_03.Lab;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class p06_ProductShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        String input = scanner.nextLine();
        Map<String, ArrayList<String>> shopWithProducts = new TreeMap<>();
        while (!input.equals("Revision")) {

            String[] data = input.split(",\\s+");
            String shop = data[0];
            shopWithProducts.putIfAbsent(shop, new ArrayList<>());
            String product = data[1];
            double price = Double.parseDouble(data[2]);
            String productWithPrice = String.format("Product: %s, Price: %.1f", product, price);
            shopWithProducts.get(shop).add(productWithPrice);

            input = scanner.nextLine();
        }
        shopWithProducts.forEach((key, value) -> {
            System.out.println(key + "->");
            value.forEach(System.out::println);
        });
    }
}
