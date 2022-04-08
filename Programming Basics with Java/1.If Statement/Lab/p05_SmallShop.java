package If_Continuation_3.Lab;

import java.util.Scanner;

public class p05_SmallShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String product = scanner.nextLine();
        String city = scanner.nextLine();
        double quantityProduct = Double.parseDouble(scanner.nextLine());
        double priceProduct = 0.0;
        double coffee = 0.0;
        double water = 0.0;
        double beer = 0.0;
        double sweets = 0.0;
        double peanuts = 0.0;
        if (city.equals("Sofia")) {
            coffee = 0.5; water = 0.80; beer = 1.20; sweets = 1.45 ;peanuts = 1.60 ;
            if (product.equals("coffee")) {
                priceProduct = coffee * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("water")) {
                priceProduct = water * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("beer")) {
                priceProduct = beer * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("sweets")) {
                priceProduct = sweets * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("peanuts")) {
                priceProduct = peanuts * quantityProduct;
                System.out.println(priceProduct);
            }
        } else if (city.equals("Plovdiv")) {
            coffee = 0.4; water = 0.70; beer = 1.15; sweets = 1.30 ;peanuts = 1.50 ;
            if (product.equals("coffee")) {
                priceProduct = coffee * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("water")) {
                priceProduct = water * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("beer")) {
                priceProduct = beer * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("sweets")) {
                priceProduct = sweets * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("peanuts")) {
                priceProduct = peanuts * quantityProduct;
                System.out.println(priceProduct);
            }
        } else if (city.equals("Varna")) {
            coffee = 0.45; water = 0.70; beer = 1.10; sweets = 1.35 ;peanuts = 1.55 ;
            if (product.equals("coffee")) {
                priceProduct = coffee * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("water")) {
                priceProduct = water * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("beer")) {
                priceProduct = beer * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("sweets")) {
                priceProduct = sweets * quantityProduct;
                System.out.println(priceProduct);
            } else if (product.equals("peanuts")) {
                priceProduct = peanuts * quantityProduct;
                System.out.println(priceProduct);
            }
        }
    }
}
