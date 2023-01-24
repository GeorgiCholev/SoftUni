package com.example.pathfinderwebapp.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "text_content", columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne
    private User author;

    @ManyToOne
    private User recipient;

    public String getDateTime() {
        return dateTime.toString();
    }

    public String getTextContent() {
        return textContent;
    }

    public Long getAuthorId() {
        return author.getId();
    }

    public Long getRecipientId() {
        return recipient.getId();
    }
}
