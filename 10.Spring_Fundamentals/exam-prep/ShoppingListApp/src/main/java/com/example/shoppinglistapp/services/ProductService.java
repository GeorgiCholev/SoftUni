package com.example.shoppinglistapp.services;

import com.example.shoppinglistapp.models.dtos.ProductAddDTO;
import com.example.shoppinglistapp.models.entities.Category;
import com.example.shoppinglistapp.models.entities.Product;
import com.example.shoppinglistapp.repositories.CategoryRepository;
import com.example.shoppinglistapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void addProduct(ProductAddDTO dto) {
        Category category = categoryRepository.findByName(dto.getCategory()).orElse(null);

        if (category == null) return;

        productRepository.save(new Product(dto, category));
    }
}
