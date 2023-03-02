package com.example.restcontrollerdemoserver.repositories;

import com.example.restcontrollerdemoserver.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
