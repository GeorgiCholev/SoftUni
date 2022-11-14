package com.example.bookshopsystem;

import com.example.bookshopsystem.services.author.AuthorService;
import com.example.bookshopsystem.services.book.BookService;
import com.example.bookshopsystem.services.category.CategoryService;
import com.example.bookshopsystem.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(
            SeedService seedService,
            AuthorService authorService,
            BookService bookService,
            CategoryService categoryService
    ) {
        this.seedService = seedService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedDatabase();
    }
}
