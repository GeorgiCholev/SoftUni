package com.example.restcontrollerdemoserver.services;

import com.example.restcontrollerdemoserver.models.entities.Author;
import com.example.restcontrollerdemoserver.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findByName(String name) {
        Optional<Author> optAuthor = authorRepository.findByName(name);
        return optAuthor.orElseGet(() -> authorRepository.save(new Author(name)));
    }
}
