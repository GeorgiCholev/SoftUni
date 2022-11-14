package com.example.bookshopsystem.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "author")
    private final Set<Book> books;

    public Author() {
        this.books = new HashSet<>();
    }

    public Author(String firstName) {
        this();
        this.firstName = firstName;
    }

    public Author(String firstName, String lastName) {
        this(firstName);
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(this.books);
    }

    public boolean addBook(Book book) {
        return this.books.add(book);
    }
}
