package com.example.mobilelewebapp.models.entities.enums;

public enum RoleType {

    MODERATOR("Moderator"), ADMIN("Admin");

    private final String label;

    RoleType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public RoleType getRoleType(String label) {
        return Enum.valueOf(RoleType.class, label.toUpperCase());
    }
}
