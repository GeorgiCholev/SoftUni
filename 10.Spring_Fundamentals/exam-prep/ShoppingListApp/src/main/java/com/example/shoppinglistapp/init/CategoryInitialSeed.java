package com.example.shoppinglistapp.init;

import com.example.shoppinglistapp.models.entities.Category;
import com.example.shoppinglistapp.models.entities.CategoryName;
import com.example.shoppinglistapp.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CategoryInitialSeed implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategoryInitialSeed(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {

        if (categoryRepository.count() != 0) {
            return;
        }

        categoryRepository.saveAll(
                Arrays.stream(CategoryName.values())
                        .map(Category::new)
                        .collect(Collectors.toList())
        );
    }
}
