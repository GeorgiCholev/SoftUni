package com.example.books.services;

import com.example.books.models.dtos.BookDto;
import com.example.books.models.entities.Author;
import com.example.books.models.entities.Book;
import com.example.books.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .map(b -> new BookDto(b.getId(), b.getTitle(), b.getAuthor()))
                .toList();
    }

    public BookDto getBook(String id) {
        Optional<Book> optBook = bookRepository.findById(id);
        if (optBook.isEmpty()) {
            return null;
        }

        return optBook.map(b -> new BookDto(b.getId(), b.getTitle(), b.getAuthor())).get();
    }

    public String createBook(BookDto bookDto) {
        Author author = authorService.findByName(bookDto.getAuthorName());
        return bookRepository.save(new Book(bookDto.getTitle(), author, 1111)).getId();
    }

    public void delete(String id) {
        Optional<Book> book = bookRepository.findById(id);
        bookRepository.deleteById(id);
    }
}
