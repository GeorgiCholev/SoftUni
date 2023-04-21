package com.example.restcontrollerdemoserver.initSeed;


import com.example.restcontrollerdemoserver.models.entities.Author;
import com.example.restcontrollerdemoserver.models.entities.Book;
import com.example.restcontrollerdemoserver.repositories.AuthorRepository;
import com.example.restcontrollerdemoserver.repositories.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

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
                        "The Dark Tower, Stephen King",
                        "The Shining, Stephen King",
                        "Rose Madder, Stephen King",
                        "Brave New World, Aldous Huxley",
                        "Sapiens, Yuval Noah Harrari",
                        "Outliers, Malcolm Gladwell"})
                .forEach(this::addBook);
    }

    private void addBook(String input) {
        String[] split = input.split(", ");
        bookRepository.save(
                new Book(split[0],
                        authorRepository.findByName(split[1]).get(),
                        ThreadLocalRandom.current().nextInt(0, 100))
        );
    }
}
