package com.example.mobilelewebapp.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "brands")
public class Brand extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
