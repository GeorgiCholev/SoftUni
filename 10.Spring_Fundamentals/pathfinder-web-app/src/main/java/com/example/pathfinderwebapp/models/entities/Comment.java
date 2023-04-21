package com.example.pathfinderwebapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(name = "text_content", nullable = false, columnDefinition = "TEXT")
    private String textContent;

    @Column(nullable = false)
    private LocalDateTime created;

    private boolean approved;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Route route;

    public Comment() {
    }

    public String getTextContent() {
        return textContent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public boolean isApproved() {
        return approved;
    }

    public User getAuthor() {
        return author;
    }

    public Route getRoute() {
        return route;
    }
}
