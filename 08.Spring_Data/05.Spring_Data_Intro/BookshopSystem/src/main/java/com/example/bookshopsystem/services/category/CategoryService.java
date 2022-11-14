package com.example.bookshopsystem.services.category;

import com.example.bookshopsystem.models.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getRandomCategories();

    void save(Category category);
}
