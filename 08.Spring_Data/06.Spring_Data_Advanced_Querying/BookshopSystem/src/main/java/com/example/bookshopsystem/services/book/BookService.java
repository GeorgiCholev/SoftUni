package com.example.bookshopsystem.services.book;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import com.example.bookshopsystem.models.validators.AgeRestriction;
import com.example.bookshopsystem.models.validators.EditionType;

import java.util.Collection;
import java.util.List;

public interface BookService {

    void save(Book book);

    List<String> findTitlesBy(AgeRestriction ageRestriction);

    List<String> findTitlesByAndPricesLessThan(EditionType editionType, int copies);

    List<String> findTitlesAndNamesOutsidePriceRange(double floor, double ceil);

    List<String> findTitlesNotInYear(int year);

    List<String> findTitlesEditionsAndPricesBeforeDate(int year, int month, int dayOfMonth);

    List<String> findTitlesContaining(String containing);

    List<String> findTitlesFrom(Collection<Author> authors);

    long findBookCountWithTitleLongerThan(int length);

    String getBookInformation(String title);

    int addCopiesForBooksAfterDate(int copies, String date);

    int deleteBookWithCopiesLessThan(int copies);
}
