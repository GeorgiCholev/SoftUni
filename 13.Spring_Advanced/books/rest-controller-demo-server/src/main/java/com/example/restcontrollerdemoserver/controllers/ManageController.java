package com.example.restcontrollerdemoserver.controllers;


import com.example.restcontrollerdemoserver.models.dtos.BookDto;
import com.example.restcontrollerdemoserver.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class ManageController {

    private final BookService bookService;

    public ManageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") String bookId) {
        BookDto book = this.bookService.getBook(bookId);

        if (book == null) {
//          404 Not Found
            return ResponseEntity.notFound().build();
        }

//      200 OK
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto,
                                              UriComponentsBuilder uriComponentsBuilder) {

        String bookId = this.bookService.createBook(bookDto);

//      201 Created
        return ResponseEntity.created(uriComponentsBuilder.path("/api/books/{id}").build(bookId)).build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable("id") String bookId) {
        bookService.delete(bookId);

//      204 No Content
        return ResponseEntity.noContent().build();
    }
}
