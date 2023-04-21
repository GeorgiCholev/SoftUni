package com.example.mobilelewebapp.models.entities.enums;

public enum CategoryType {

    CAR("Car"), BUSS("Buss"), TRUCK("Truck"), MOTORCYCLE("Motorcycle");

    private final String label;

    CategoryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public CategoryType getCategoryType(String label) {
        return Enum.valueOf(CategoryType.class, label.toUpperCase());
    }
}
