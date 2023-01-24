package com.example.pathfinderwebapp.models.entities;

import com.example.pathfinderwebapp.utils.enums.CategoryNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Categories extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private CategoryNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public CategoryNameEnum getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categories that = (Categories) o;

        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return name.hashCode() + 31 * this.getId().hashCode();
    }
}
