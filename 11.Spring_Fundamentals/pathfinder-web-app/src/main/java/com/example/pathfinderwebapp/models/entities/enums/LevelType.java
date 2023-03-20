package com.example.pathfinderwebapp.models.entities.enums;

public enum LevelType {
    BEGINNER("Beginner"), INTERMEDIATE("Intermediate"), ADVANCED("Advanced");

    private final String label;

    LevelType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public LevelType getType(String label) {
        return Enum.valueOf(LevelType.class, label.toUpperCase());
    }
}
