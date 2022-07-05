package _2_Encapsulation.Exercise.p03_ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product) {
        double productCost = product.getCost();
        if (money < productCost) {
            throw new IllegalArgumentException(name + " can't afford " + product.getName());
        }
        money -= productCost;
        products.add(product);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ");

        String productData = products
                .stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
        if(productData.isEmpty()){
            sb.append("Nothing bought");
        } else {
            sb.append(productData);
        }
        return sb.toString();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }
}
