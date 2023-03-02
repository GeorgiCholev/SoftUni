package com.example.restcontrollerdemoserver.repositories;

import com.example.restcontrollerdemoserver.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    Optional<Author> findByName(String name);
}
