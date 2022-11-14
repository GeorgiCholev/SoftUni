package com.example.bookshopsystem.services.category;

import com.example.bookshopsystem.models.Category;
import com.example.bookshopsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        List<Category> allCategories = categoryRepository.findAll();

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < allCategories.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        int numberOfCategories = new Random().nextInt(allCategories.size()) + 1;

        Set<Category> randomCategories = new HashSet<>();

        for (int i = 0; i < numberOfCategories; i++) {
            randomCategories.add(allCategories.get(indices.get(i)));
        }

        return randomCategories;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
