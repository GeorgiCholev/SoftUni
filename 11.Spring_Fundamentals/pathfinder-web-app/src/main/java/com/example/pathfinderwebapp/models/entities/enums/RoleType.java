package com.example.pathfinderwebapp.models.entities.enums;

public enum RoleType {
    USER("User"), MODERATOR("Moderator"), ADMIN("Admin");

    private final String label;

    RoleType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public RoleType getType(String label) {
        return Enum.valueOf(RoleType.class, label.toUpperCase());
    }
}
