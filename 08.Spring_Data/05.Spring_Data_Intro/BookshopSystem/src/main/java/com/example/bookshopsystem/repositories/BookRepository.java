package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
