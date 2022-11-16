package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import com.example.bookshopsystem.models.dto.BookInformationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAgeRestriction(String ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(String editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findAllByReleaseDateBefore(LocalDate before);

    List<Book> findAllByTitleContaining(String containing);

    List<Book> findAllByAuthorIn(Collection<Author> authors);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :length")
    long findBookCountWithTitleLongerThan(int length);

    Optional<BookInformationDTO> findByTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies + :moreCopies WHERE b.releaseDate > :date")
    int addCopiesForBookAfterDate(int moreCopies, LocalDate date);

    @Modifying
    @Transactional
    int deleteByCopiesLessThan(int copies);
}
