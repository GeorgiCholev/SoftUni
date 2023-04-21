package com.example.mobilelewebapp.models.entities;

import com.example.mobilelewebapp.models.dtos.BrandImportDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private LocalDateTime created;

    private LocalDateTime modified;

    public Brand() {
    }

    public Brand(BrandImportDto dto) {
        this.name = dto.getName();
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getModified() {
        return this.modified;
    }
}
