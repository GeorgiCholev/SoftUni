package com.example.shoppinglistapp.models.dtos;

import com.example.shoppinglistapp.models.entities.CategoryName;
import com.example.shoppinglistapp.models.entities.Product;

public class ProductViewDTO {

    private final Long id;

    private final String name;

    private final double price;

    private final String categoryName;

    public ProductViewDTO(CategoryName categoryName, Product product) {
        this.categoryName = categoryName.getLabel();
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice().doubleValue();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductViewDTO that = (ProductViewDTO) o;

        if (!id.equals(that.id)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " Price: " + this.price + " lv";
    }
}
