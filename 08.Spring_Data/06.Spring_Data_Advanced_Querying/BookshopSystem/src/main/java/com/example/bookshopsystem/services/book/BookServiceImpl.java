package com.example.bookshopsystem.services.book;

import com.example.bookshopsystem.exceptions.EntityNotFoundException;
import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import com.example.bookshopsystem.models.dto.BookInformationDTO;
import com.example.bookshopsystem.models.validators.AgeRestriction;
import com.example.bookshopsystem.models.validators.EditionType;
import com.example.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<String> findTitlesBy(AgeRestriction enumField) {
        String ageRestriction = enumField.getAgeRestriction();

        List<Book> found = this.bookRepository.findAllByAgeRestriction(ageRestriction);

        throwExceptionIfEmpty(found);

        return bookFormatList(found, Book::getTitle);
    }

    @Override
    public List<String> findTitlesByAndPricesLessThan(EditionType enumField, int copies) {
        String editionType = enumField.getEditionType();

        List<Book> found = this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies);

        throwExceptionIfEmpty(found);

        return bookFormatList(found, Book::getTitle);
    }

    @Override
    public List<String> findTitlesAndNamesOutsidePriceRange(double floor, double ceil) {
        BigDecimal lessThan = BigDecimal.valueOf(floor);
        BigDecimal greaterThan = BigDecimal.valueOf(ceil);

        List<Book> found = this.bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(lessThan, greaterThan);

        throwExceptionIfEmpty(found);

        return bookFormatList(found, Book::getTitleAndPrice);
    }

    @Override
    public List<String> findTitlesNotInYear(int year) {
        LocalDate firstDayOfYear = LocalDate.of(year, Month.JANUARY, 1);
        LocalDate lastDayOfYear = LocalDate.of(year, Month.DECEMBER, 31);

        List<Book> found = this.bookRepository
                .findAllByReleaseDateBeforeOrReleaseDateAfter(firstDayOfYear, lastDayOfYear);

        throwExceptionIfEmpty(found);

        return bookFormatList(found, Book::getTitle);
    }

    @Override
    public List<String> findTitlesEditionsAndPricesBeforeDate(int year, int month, int dayOfMonth) {
        LocalDate ofDate = LocalDate.of(year, month, dayOfMonth);

        List<Book> found = this.bookRepository
                .findAllByReleaseDateBefore(ofDate);

        throwExceptionIfEmpty(found);

        return bookFormatList(found, Book::getTitleEditionTypeAndPrice);
    }

    @Override
    public List<String> findTitlesContaining(String containing) {
        List<Book> found = this.bookRepository
                .findAllByTitleContaining(containing);

        throwExceptionIfEmpty(found);

        return bookFormatList(found, Book::getTitle);
    }

    @Override
    public List<String> findTitlesFrom(Collection<Author> authors) {
        List<Book> found = this.bookRepository.findAllByAuthorIn(authors);

        if (found.isEmpty()) {
            throw new IllegalStateException("Found author does not have any books present.");
        }

        return bookFormatList(found, Book::getTitleAndAuthor);
    }

    @Override
    public long findBookCountWithTitleLongerThan(int length) {
        return this.bookRepository.findBookCountWithTitleLongerThan(length);
    }

    @Override
    public String getBookInformation(String title) {
        BookInformationDTO dto = this.bookRepository
                .findByTitle(title)
                .orElseThrow(EntityNotFoundException::new);

        return dto.getInfo();
    }

    @Override
    public int addCopiesForBooksAfterDate(int copies, String date) {
        LocalDate afterDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM uuuu"));
        return bookRepository.addCopiesForBookAfterDate(copies, afterDate);
    }

    @Override
    public int deleteBookWithCopiesLessThan(int copies) {
        return bookRepository.deleteByCopiesLessThan(copies);
    }

    private List<String> bookFormatList(List<Book> found, Function<Book, String> format) {
        return found.stream()
                .map(format)
                .collect(Collectors.toList());
    }

    private void throwExceptionIfEmpty(List<Book> found) {
        if (found.isEmpty()) {
            throw new EntityNotFoundException();
        }
    }


}
