package com.example.mobilelewebapp.models.entities;

import com.example.mobilelewebapp.models.dtos.OfferAddDto;
import com.example.mobilelewebapp.models.dtos.OfferUpdateFormDto;
import com.example.mobilelewebapp.models.entities.enums.EngineType;
import com.example.mobilelewebapp.models.entities.enums.TransmissionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type", nullable = false)
    private EngineType engineType;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer kilometres;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type", nullable = false)
    private TransmissionType transmissionType;

    @Column(nullable = false)
    private Short year;

    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToOne(optional = false)
    private Model model;

    @ManyToOne(optional = false)
    private User seller;

    public Offer() {
    }

    public Offer(OfferAddDto dto, User user, Model model) {
        this.description = dto.getDescription();
        this.engineType = EngineType.getEngineType(dto.getEngineType());
        this.imageUrl = dto.getImageUrl();
        this.kilometres = dto.getKilometres();
        this.price = dto.getPrice();
        this.transmissionType = TransmissionType.getTransmissionType(dto.getTransmissionType());
        this.year = dto.getYear();
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.model = model;
        this.seller = user;
    }

    public void updateFromDto(OfferUpdateFormDto dto, Model model) {
        this.model = model;
        this.price = dto.getPrice();
        this.engineType = EngineType.getEngineType(dto.getEngineTypeLabel());
        this.transmissionType = TransmissionType.getTransmissionType(dto.getTransmissionTypeLabel());
        this.year = dto.getYear();
        this.kilometres = dto.getKilometres();
        this.description = dto.getDescription();
        this.imageUrl = dto.getImageUrl();
    }

    public String getDescription() {
        return this.description;
    }

    public EngineType getEngineType() {
        return this.engineType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Integer getKilometres() {
        return this.kilometres;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public TransmissionType getTransmissionType() {
        return this.transmissionType;
    }

    public Short getYear() {
        return this.year;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public LocalDateTime getModified() {
        return this.modified;
    }

    public Model getModel() {
        return this.model;
    }

    public User getSeller() {
        return this.seller;
    }
}
