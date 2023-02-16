package com.likebookapp.model.entity;

public enum MoodName {
    HAPPY("Happy"), SAD("Sad"), INSPIRED("Inspired");

    private final String label;

    MoodName(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
