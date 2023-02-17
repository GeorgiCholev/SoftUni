package com.example.shoppinglistapp.models.entities;

import com.example.shoppinglistapp.models.dtos.ProductAddDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private BigDecimal price;

    @Column(name = "needed_before")
    private LocalDateTime neededBefore;

    @ManyToOne(optional = false)
    private Category category;

    public Product() {
    }

    public Product(ProductAddDTO dto, Category category) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.neededBefore = dto.getBefore();
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public Category getCategory() {
        return category;
    }
}
