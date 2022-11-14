package com.example.bookshopsystem.models;

import com.example.bookshopsystem.models.validators.AgeRestriction;
import com.example.bookshopsystem.models.validators.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1_000)
    private String description;

    @Column(name = "edition_type", nullable = false)
    private String editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "age_restriction", nullable = false)
    private String ageRestriction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private final Set<Category> categories;

    public Book() {
        categories = new HashSet<>();
    }

    public Book(String title, EditionType editionType, BigDecimal price, int copies, AgeRestriction ageRestriction) {
        this();
        this.title = title;
        this.setEditionType(editionType);
        this.price = price;
        this.copies = copies;
        this.setAgeRestriction(ageRestriction);
    }

    public Book(String title, String description, EditionType editionType,
                BigDecimal price, int copies, LocalDate releaseDate, AgeRestriction ageRestriction) {
        this(title, editionType, price, copies, ageRestriction);
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return EditionType.valueOf(editionType);
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType.getEditionType();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return AgeRestriction.valueOf(ageRestriction);
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction.getAgeRestriction();
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }
}
