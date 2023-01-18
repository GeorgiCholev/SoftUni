package com.example.mobilelewebapp.models.entities;

import com.example.mobilelewebapp.models.enumerations.Engine;
import com.example.mobilelewebapp.models.enumerations.Transmission;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Engine engine;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private Short mileage;

    @Column
    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    private Transmission transmission;

    @Column
    private Short year;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @ManyToOne
    private Model model;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Short getMileage() {
        return mileage;
    }

    public void setMileage(Short mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
