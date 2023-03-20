package com.example.pathfinderwebapp.models.entities;

import com.example.pathfinderwebapp.models.entities.enums.CategoryType;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private CategoryType type;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    public Category() {
    }

    public CategoryType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return super.getId().equals(category.getId()) && type == category.type;
    }

    @Override
    public int hashCode() {
        int result = super.getId().hashCode();
        return result * 31 + type.hashCode();
    }
}
