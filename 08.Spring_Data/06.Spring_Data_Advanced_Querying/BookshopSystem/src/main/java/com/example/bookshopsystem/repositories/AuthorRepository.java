package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.dto.AuthorTotalCopiesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllByFirstNameEndingWith(String endingWith);

    List<Author> findAllByLastNameStartingWith(String endingWith);

    @Query("SELECT new com.example.bookshopsystem.models.dto.AuthorTotalCopiesDTO" +
            "(a.firstName, a.lastName, SUM(b.copies)) FROM Author a " +
            "JOIN a.books b GROUP BY a ORDER BY SUM(b.copies) DESC")
    List<AuthorTotalCopiesDTO> findAllByTotalNumberOfBookCopiesDesc();
}
