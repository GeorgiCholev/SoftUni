package com.resellerapp.model.entity.enums;

public enum ConditionName {
    EXCELLENT("Excellent", "In perfect condition"),
    GOOD("Good", "Some signs of wear and tear or minor defects"),
    ACCEPTABLE("Acceptable", "The item is fairly worn but continues to function properly");

    private final String label;
    private final String description;

    ConditionName(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}
