package com.example.pathfinderwebapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    private String title;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private User author;

    @ManyToOne
    private Route route;

    @Override
    public Long getId() {
        return super.id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Long getAuthorId() {
        return author.getId();
    }

    public Long getRouteId() {
        return route.getId();
    }
}
