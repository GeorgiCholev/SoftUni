package com.example.pathfinderwebapp.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private boolean approved;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(name = "text_content", nullable = false,  columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne
    private User author;

    public boolean isApproved() {
        return approved;
    }

    public String getCreated() {
        return created.toString();
    }

    public String getTextContent() {
        return textContent;
    }

    public Long getAuthorId() {
        return author.getId();
    }
}
