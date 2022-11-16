package com.example.bookshopsystem.services.seed;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import com.example.bookshopsystem.models.Category;
import com.example.bookshopsystem.models.validators.AgeRestriction;
import com.example.bookshopsystem.models.validators.EditionType;
import com.example.bookshopsystem.services.author.AuthorService;
import com.example.bookshopsystem.services.book.BookService;
import com.example.bookshopsystem.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String SEED_FILES_PATH = "src/main/resources/seedFiles";

    private static final String AUTHORS_FILE_PATH = SEED_FILES_PATH + "/authors.txt";

    private static final String CATEGORIES_FILE_PATH = SEED_FILES_PATH + "/categories.txt";

    private static final String BOOKS_FILE_PATH = SEED_FILES_PATH + "/books.txt";

    private final AuthorService authorService;

    private final BookService bookService;
    private final CategoryService categoryService;

    private Scanner scanner;

    @Autowired
    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws FileNotFoundException {
        scanner = new Scanner(new File(AUTHORS_FILE_PATH));

        while (scanner.hasNext()) {

            String nextLine = scanner.nextLine();

            if (nextLine.isBlank()) {
                continue;
            }

//          Roger Porter

            String[] components = nextLine.split("\\s+");
            String firstName = components[0];
            String lastName = components[1];

            Author author = new Author(firstName, lastName);

            authorService.save(author);
        }
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        scanner = new Scanner(new File(CATEGORIES_FILE_PATH));

        while (scanner.hasNext()) {

            String categoryName = scanner.nextLine();

            if (categoryName.isBlank()) {
                continue;
            }

//          Mystery

            Category category = new Category(categoryName);

            categoryService.save(category);
        }
    }

    @Override
    public void seedBooks() throws FileNotFoundException {
        scanner = new Scanner(new File(BOOKS_FILE_PATH));

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();

            if (nextLine.isBlank()) {
                continue;
            }

//          0 20/07/1990 14166 23.87 0 Clouds of Witness

            Book book = createNewBookInstance(nextLine);

            bookService.save(book);
        }
    }

    private Book createNewBookInstance(String nextLine) {
        String[] components = nextLine.split("\\s+");

        EditionType editionType = EditionType.values()[Integer.parseInt(components[0])];

        LocalDate releaseDate = LocalDate.parse(components[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(components[2]);

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(components[3]));

        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(components[4])];

        String title = String.join(" ", Arrays.copyOfRange(components, 5, components.length));

        Set<Category> categories = categoryService.getRandomCategories();

        Book book = new Book(title, editionType, price, copies, ageRestriction);

        book.setReleaseDate(releaseDate);

        book.setAuthor(authorService.getRandomAuthor());

        for (Category category : categories) {
            book.addCategory(category);
        }

        return book;
    }
}
