package com.example.bookshopsystem.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private final Set<Book> books;

    public Category() {
        books = new HashSet<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(this.books);
    }

    public boolean addBook(Book book) {
        return this.books.add(book);
    }
}
