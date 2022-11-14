package com.example.bookshopsystem.services.author;

import com.example.bookshopsystem.models.Author;
import org.springframework.stereotype.Service;

public interface AuthorService {

    Author getRandomAuthor();

    void save(Author author);
}
