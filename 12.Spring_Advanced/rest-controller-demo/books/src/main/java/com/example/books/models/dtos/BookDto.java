package com.example.books.models.dtos;

import com.example.books.models.entities.Author;

public class BookDto {

    private String id;

    private String title;

    private String authorName;

    public BookDto() {
    }

    public BookDto(String id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.authorName = author.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
