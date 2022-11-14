package com.example.bookshopsystem.services.seed;


import java.io.FileNotFoundException;

public interface SeedService {

    void seedAuthors() throws FileNotFoundException;

    void seedBooks() throws FileNotFoundException;

    void seedCategories() throws FileNotFoundException;

    default void seedDatabase() throws FileNotFoundException {
        seedAuthors();

        seedCategories();

        seedBooks();
    }
}
