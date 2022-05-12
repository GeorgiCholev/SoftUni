package Maps_8.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p04_Order {
    public static void main(String[] args) {
        Map<String, Product> productsByName = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals("buy")) {
            String[] components = line.split(" ");
            String name = components[0];
            double price = Double.parseDouble(components[1]);
            int quantity = Integer.parseInt(components[2]);
            Product current;
            if (productsByName.containsKey(name)) {
                current = new Product(name, price, productsByName.get(name).getQuantity() + quantity);
            } else {
                current = new Product(name, price, quantity);
            }
            productsByName.put(name, current);
            line = scanner.nextLine();
        }
        productsByName.forEach((key, value) -> System.out.println(value.getValue()));
    }

    static class Product {
        String name;
        double price;
        int quantity;

        public int getQuantity() {
            return quantity;
        }

        Product(String name, double price, int quantity) {
            String formatPrice = String.format("%.2f", price);
            this.name = name;
            this.price = Double.parseDouble(formatPrice);
            this.quantity = quantity;
        }

        double cost() {
            return this.price * this.quantity;
        }

        String getValue() {
            return String.format("%s -> %.2f", this.name, this.cost());
        }
    }
}
