package com.example.bookshopsystem.models.validators;

public enum EditionType {
    NORMAL("normal"),
    PROMO("promo"),
    GOLD("gold");

    private final String editionType;

    EditionType(String editionType) {
        this.editionType = editionType;
    }

    public String getEditionType() {
        return editionType;
    }
}
