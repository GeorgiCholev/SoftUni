package com.example.bookshopsystem.models.validators;

public enum AgeRestriction {
    MINOR("minor"),
    TEEN("teen"),
    ADULT("adult");

    private final String ageRestriction;

    AgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }
}
