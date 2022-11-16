package com.example.bookshopsystem.services.author;

import com.example.bookshopsystem.models.Author;

import java.util.List;

public interface AuthorService {

    Author getRandomAuthor();

    void save(Author author);

    List<String> findAuthorsWithFirstNameEndingWith(String endingWith);

    List<Author> findAuthorsWithLastNameStartingWith(String startingWith);

    List<String> findAuthorsByTotalNumberOfBookCopiesDesc();
}
