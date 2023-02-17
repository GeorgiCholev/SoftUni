package com.example.shoppinglistapp.models.dtos;

import com.example.shoppinglistapp.models.entities.CategoryName;

import java.util.HashMap;
import java.util.HashSet;

public class ProductsByCategoryName {

    public static double totalPrice = 0.0;
    private final HashMap<String, HashSet<ProductViewDTO>> productsByCategoryName;

    public ProductsByCategoryName() {
        this.productsByCategoryName = new HashMap<>();
        productsByCategoryName.put(CategoryName.FOOD.getLabel(), new HashSet<>());
        productsByCategoryName.put(CategoryName.DRINK.getLabel(), new HashSet<>());
        productsByCategoryName.put(CategoryName.HOUSEHOLD.getLabel(), new HashSet<>());
        productsByCategoryName.put(CategoryName.OTHER.getLabel(), new HashSet<>());
        totalPrice = 0.0;
    }

    public void add(String categoryName, ProductViewDTO productViewDTO) {
        productsByCategoryName.get(categoryName).add(productViewDTO);
        totalPrice += productViewDTO.getPrice();
    }

    public HashSet<ProductViewDTO> getFoods() {
        return productsByCategoryName.get(CategoryName.FOOD.getLabel());
    }

    public HashSet<ProductViewDTO> getDrinks() {
        return productsByCategoryName.get(CategoryName.DRINK.getLabel());
    }

    public HashSet<ProductViewDTO> getHouseholds() {
        return productsByCategoryName.get(CategoryName.HOUSEHOLD.getLabel());
    }

    public HashSet<ProductViewDTO> getOthers() {
        return productsByCategoryName.get(CategoryName.OTHER.getLabel());
    }

    public String getTotalPrice() {
        return String.format("%.2f", totalPrice);
    }
}
