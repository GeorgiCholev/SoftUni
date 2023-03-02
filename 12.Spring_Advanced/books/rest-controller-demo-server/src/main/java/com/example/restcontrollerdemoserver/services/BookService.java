package com.example.restcontrollerdemoserver.services;


import com.example.restcontrollerdemoserver.models.dtos.BookDto;
import com.example.restcontrollerdemoserver.models.entities.Author;
import com.example.restcontrollerdemoserver.models.entities.Book;
import com.example.restcontrollerdemoserver.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<BookDto> getAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(b -> new BookDto(b.getId(), b.getTitle(), b.getIsbn(), b.getAuthor()))
                .toList();
    }

    public BookDto getBook(String id) {
        Optional<Book> optBook = bookRepository.findById(id);
        if (optBook.isEmpty()) {
            return null;
        }

        return optBook.map(b -> new BookDto(b.getId(), b.getTitle(), b.getIsbn(), b.getAuthor())).get();
    }

    public String createBook(BookDto bookDto) {
        Author author = authorService.findByName(bookDto.getAuthorName());
        return bookRepository.save(
                        new Book(bookDto.getTitle(), author, ThreadLocalRandom.current().nextInt(0, 100)))
                .getId();
    }

    public void delete(String id) {
        bookRepository.deleteById(id);
    }
}
