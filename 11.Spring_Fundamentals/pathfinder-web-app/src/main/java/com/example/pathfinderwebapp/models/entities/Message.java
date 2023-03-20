package com.example.pathfinderwebapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @Column(name = "text_content", nullable = false, columnDefinition = "TEXT")
    private String textContent;

    @Column(nullable = false)
    private LocalDateTime created;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private User recipient;

    public Message() {
    }

    public String getTextContent() {
        return textContent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public User getAuthor() {
        return author;
    }

    public User getRecipient() {
        return recipient;
    }
}
