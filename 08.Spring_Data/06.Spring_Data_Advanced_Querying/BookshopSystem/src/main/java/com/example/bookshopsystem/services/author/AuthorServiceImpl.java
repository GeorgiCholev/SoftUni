package com.example.bookshopsystem.services.author;

import com.example.bookshopsystem.exceptions.EntityNotFoundException;
import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.dto.AuthorTotalCopiesDTO;
import com.example.bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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

    @Override
    public List<String> findAuthorsWithFirstNameEndingWith(String endingWith) {
        List<Author> found = this.authorRepository.findAllByFirstNameEndingWith(endingWith);

        throwExceptionIfEmpty(found);

        return found
                .stream()
                .map(Author::getFullName)
                .collect(Collectors.toList());

    }

    @Override
    public List<Author> findAuthorsWithLastNameStartingWith(String startingWith) {

        List<Author> found = this.authorRepository.findAllByLastNameStartingWith(startingWith);

        throwExceptionIfEmpty(found);

        return found;
    }

    @Override
    public List<String> findAuthorsByTotalNumberOfBookCopiesDesc() {
        return authorRepository
                .findAllByTotalNumberOfBookCopiesDesc()
                .stream()
                .map(AuthorTotalCopiesDTO::getInfo)
                .collect(Collectors.toList());

    }

    private static void throwExceptionIfEmpty(List<Author> found) {
        if (found.isEmpty()) {
            throw new EntityNotFoundException();
        }
    }
}
