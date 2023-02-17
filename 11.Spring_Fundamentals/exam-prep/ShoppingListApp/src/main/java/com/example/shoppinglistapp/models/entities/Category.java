package com.example.shoppinglistapp.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true, nullable = false)
    private CategoryName name;

    @Column
    private String description;

    public Category() {
    }

    public Category(CategoryName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public CategoryName getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
