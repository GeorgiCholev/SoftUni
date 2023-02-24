package com.example.books.initSeed;

import com.example.books.models.entities.Author;
import com.example.books.models.entities.Book;
import com.example.books.repositories.AuthorRepository;
import com.example.books.repositories.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitialSeed {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public InitialSeed(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    private void seedDB() {
        if (authorRepository.count() == 0) {
            this.seedAuthors();
        }

        if (bookRepository.count() == 0) {
            this.seedBooks();
        }
    }

    private void seedAuthors() {
        Arrays.stream(new String[]{
                        "Stephen King",
                        "Yuval Noah Harrari",
                        "Malcolm Gladwell",
                        "Aldous Huxley"})
                .forEach(this::addAuthor);
    }

    private void addAuthor(String name) {
        this.authorRepository.save(new Author(name));
    }

    private void seedBooks() {
        Arrays.stream(new String[]{
                        "The Dark Tower, Stephen King, 1982",
                        "The Shining, Stephen King, 1980",
                        "Rose Madder, Stephen King, 1995",
                        "Brave New World, Aldous Huxley, 1932",
                        "Sapiens, Yuval Noah Harrari, 2011",
                        "Outliers, Malcolm Gladwell, 2008"})
                .forEach(this::addBook);
    }

    private void addBook(String input) {
        String[] split = input.split(", ");
        bookRepository.save(
                new Book(split[0], authorRepository.findByName(split[1]).get(), Integer.parseInt(split[2]))
        );
    }
}
