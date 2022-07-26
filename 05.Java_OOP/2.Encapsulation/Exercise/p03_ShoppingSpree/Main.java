package _2_Encapsulation.Exercise.p03_ShoppingSpree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] customersInfo = scanner.nextLine().split(";");
        Map<String, Person> customersByName = new LinkedHashMap<>();
        for (String customerInfo : customersInfo) {
            String[] components = customerInfo.split("=");
            String name = components[0];
            double money = Double.parseDouble(components[1]);
            try {
                Person person = new Person(name, money);
                customersByName.put(name, person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String[] productsInfo = scanner.nextLine().split(";");
        Map<String, Product> productsByName = new LinkedHashMap<>();
        for (String productInfo : productsInfo) {
            String[] components = productInfo.split("=");
            String name = components[0];
            double cost = Double.parseDouble(components[1]);
            try {
                Product product = new Product(name, cost);
                productsByName.put(name, product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String command = scanner.nextLine();

        while (!"END".equals(command)) {
            String[] buyerProductPair = command.split(" ");

            String customerName = buyerProductPair[0];
            String productName = buyerProductPair[1];

            Person customer = customersByName.get(customerName);
            Product productToBuy = productsByName.get(productName);

            try {
                customer.buyProduct(productToBuy);
                System.out.println(customerName + " bought " + productName);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
  
            }

            command = scanner.nextLine();
        }
        customersByName.values().forEach(System.out::println);
    }

}
