package com.example.bookshopsystem.services.author;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        List<Author> allAuthors = authorRepository.findAll();
        int count = allAuthors.size();

        return allAuthors.get(new Random().nextInt(count));
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }
}
