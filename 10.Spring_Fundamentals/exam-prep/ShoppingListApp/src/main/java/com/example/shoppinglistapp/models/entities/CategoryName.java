package com.example.shoppinglistapp.models.entities;

public enum CategoryName {
    FOOD("Food"), DRINK("Drink"), HOUSEHOLD("Household"), OTHER("Other");

    private String label;

    CategoryName(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
