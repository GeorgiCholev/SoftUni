package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
