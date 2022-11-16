package com.example.bookshopsystem.problems;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.validators.AgeRestriction;
import com.example.bookshopsystem.models.validators.EditionType;
import com.example.bookshopsystem.services.author.AuthorService;
import com.example.bookshopsystem.services.book.BookService;
import com.example.bookshopsystem.services.category.CategoryService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class ProblemSolver {

    private static final Scanner scanner = new Scanner(System.in);
    ;
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;


    public ProblemSolver(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    public void navigateToProblem(int problemNumber) {
        problemNumber--;

        try {
            Problem problem = Problem.values()[problemNumber];
            switch (problem) {
                case P01_BOOKS_TITLES_BY_AGE_RESTRICTION -> solveP01();
                case P02_GOLDEN_BOOKS -> solveP02();
                case P03_BOOKS_BY_PRICE -> solveP03();
                case P04_NOT_RELEASED_BOOKS -> solveP04();
                case P05_BOOKS_RELEASED_BEFORE_DATE -> solveP05();
                case P06_AUTHORS_SEARCH -> solveP06();
                case P07_BOOKS_SEARCH -> solveP07();
                case P08_BOOK_TITLES_SEARCH -> solveP08();
                case P09_COUNT_BOOKS -> solveP09();
                case P10_TOTAL_BOOK_COPIES -> solveP10();
                case P11_REDUCED_BOOK -> solveP11();
                case P12_INCREASE_BOOK_COPIES -> solveP12();
                case P13_REMOVE_BOOKS -> solveP13();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("Error: Not a valid problem number.");
        }

    }


    private void solveP01() {
        System.out.print("Age Restriction:  ");
        String type = scanner.nextLine().toUpperCase();

        AgeRestriction enumFieldType = AgeRestriction.valueOf(type);

        bookService.findTitlesBy(enumFieldType)
                .forEach(System.out::println);
    }

    private void solveP02() {
        bookService.findTitlesByAndPricesLessThan(EditionType.GOLD, 5_000)
                .forEach(System.out::println);
    }

    private void solveP03() {
        bookService.findTitlesAndNamesOutsidePriceRange(5.00, 40.00)
                .forEach(System.out::println);
    }

    private void solveP04() {
        System.out.print("Year: ");
        bookService.findTitlesNotInYear(scanner.nextInt())
                .forEach(System.out::println);
    }

    private void solveP05() {
        System.out.print("Date in format {dd-MM-yyyy}   ");
        int[] dateComponents = Arrays.stream(scanner.nextLine().split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();

        bookService.findTitlesEditionsAndPricesBeforeDate(dateComponents[2], dateComponents[1], dateComponents[0])
                .forEach(System.out::println);
    }

    private void solveP06() {
        System.out.print("First name suffix:   ");
        String firstNameSuffix = scanner.nextLine();

        authorService.findAuthorsWithFirstNameEndingWith(firstNameSuffix)
                .forEach(System.out::println);
    }

    private void solveP07() {
        System.out.print("Book title which contains:    ");
        String containing = scanner.nextLine();

        bookService.findTitlesContaining(containing)
                .forEach(System.out::println);
    }

    private void solveP08() {
        System.out.print("Author last name prefix:  ");
        String prefix = scanner.nextLine();

        List<Author> authors = authorService.findAuthorsWithLastNameStartingWith(prefix);

        bookService.findTitlesFrom(authors)
                .forEach(System.out::println);
    }

    private void solveP09() {
        System.out.print("Minimum Title Length: ");
        int minTitleLength = scanner.nextInt();

        System.out.print(bookService.findBookCountWithTitleLongerThan(minTitleLength));
    }

    private void solveP10() {
        authorService.findAuthorsByTotalNumberOfBookCopiesDesc()
                .forEach(System.out::println);
    }

    private void solveP11() {
        System.out.print("Book Title:   ");
        String title = scanner.nextLine();

        System.out.print(bookService.getBookInformation(title));
    }

    private void solveP12() {
        String date = scanner.nextLine();
        int moreCopies = Integer.parseInt(scanner.nextLine());

        int numberOfBooksUpdated = bookService.addCopiesForBooksAfterDate(moreCopies, date);
        int totalNewCopies = moreCopies * numberOfBooksUpdated;

        System.out.println(totalNewCopies);
    }

    private void solveP13() {
        System.out.print("Number of copies: ");
        int copies = scanner.nextInt();
        int deletedCount = bookService.deleteBookWithCopiesLessThan(copies);

        System.out.println(deletedCount);
    }

}
