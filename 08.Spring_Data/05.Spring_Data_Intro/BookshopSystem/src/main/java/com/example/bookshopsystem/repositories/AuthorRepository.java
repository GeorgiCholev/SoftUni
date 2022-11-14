package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
