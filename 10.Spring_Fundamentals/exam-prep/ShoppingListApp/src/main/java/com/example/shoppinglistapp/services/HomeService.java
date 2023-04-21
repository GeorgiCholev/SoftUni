package com.example.shoppinglistapp.services;

import com.example.shoppinglistapp.models.dtos.ProductViewDTO;
import com.example.shoppinglistapp.models.dtos.ProductsByCategoryName;
import com.example.shoppinglistapp.models.entities.Product;
import com.example.shoppinglistapp.repositories.CategoryRepository;
import com.example.shoppinglistapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public HomeService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public ProductsByCategoryName getProductsByCategoryName() {
        ProductsByCategoryName productsByCategoryName = new ProductsByCategoryName();

        productRepository.findAll()
                .stream()
                .map(p -> new ProductViewDTO(p.getCategory().getName(), p))
                .forEach(pv -> productsByCategoryName.add(pv.getCategoryName(), pv));
        return productsByCategoryName;
    }

    public void buyProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) return;

        productRepository.delete(product);
    }

    public void buyAllProducts() {
        productRepository.deleteAll();
    }
}
