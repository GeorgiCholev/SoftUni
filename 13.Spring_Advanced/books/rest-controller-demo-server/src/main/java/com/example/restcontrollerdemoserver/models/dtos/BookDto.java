package com.example.restcontrollerdemoserver.models.dtos;


import com.example.restcontrollerdemoserver.models.entities.Author;

public class BookDto {

    private String id;

    private String title;

    private String isbn;

    private String authorName;

    public BookDto() {
    }

    public BookDto(String id, String title, String isbn, Author author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
