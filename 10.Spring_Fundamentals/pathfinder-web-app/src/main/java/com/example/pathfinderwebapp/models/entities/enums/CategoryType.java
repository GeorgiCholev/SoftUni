package com.example.pathfinderwebapp.models.entities.enums;

public enum CategoryType {
    PEDESTRIAN("Pedestrian"), BICYCLE("Bicycle"), MOTORCYCLE("Motorcycle"), CAR("Car");

    private final String label;

    CategoryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public CategoryType getType(String label) {
        return Enum.valueOf(CategoryType.class, label.toUpperCase());
    }
}
