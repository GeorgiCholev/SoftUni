package com.example.mobilelewebapp.models.entities;

import com.example.mobilelewebapp.models.dtos.ModelImportDto;
import com.example.mobilelewebapp.models.entities.enums.CategoryType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType categoryType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "start_year")
    private Short startYear;

    @Column(name = "end_year")
    private Short endYear;

    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToOne(optional = false)
    private Brand brand;

    public Model() {
    }

    public Model(ModelImportDto dto, Brand brand) {
        this.name = dto.getName();
        this.categoryType = dto.getCategoryType();
        this.brand = brand;
    }

    public String getName() {
        return this.name;
    }

    public CategoryType getCategoryType() {
        return this.categoryType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Short getStartYear() {
        return this.startYear;
    }

    public Short getEndYear() {
        return this.endYear;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getModified() {
        return this.modified;
    }

    public Brand getBrand() {
        return this.brand;
    }
}
