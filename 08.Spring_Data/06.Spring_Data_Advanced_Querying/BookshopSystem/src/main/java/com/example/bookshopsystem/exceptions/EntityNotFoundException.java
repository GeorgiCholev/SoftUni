package com.example.bookshopsystem.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Could not retrieve from bookshop. Entity does not exist.";

    public EntityNotFoundException() {
        super(MESSAGE);
    }
}
