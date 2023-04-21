package com.example.mobilelewebapp.models.dtos;

import com.example.mobilelewebapp.models.entities.Brand;
import com.example.mobilelewebapp.models.entities.Model;
import com.example.mobilelewebapp.models.entities.Offer;
import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.models.entities.enums.EngineType;
import com.example.mobilelewebapp.models.entities.enums.TransmissionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OfferViewDto {

    private final Long id;

    private final String description;

    private final String imageUrl;

    private final Short year;

    private final Integer kilometres;

    private final BigDecimal price;

    private final TransmissionType transmissionType;

    private final EngineType engineType;

    private final LocalDateTime created;

    private final LocalDateTime modified;

    private final String brandName;

    private final String modelName;

    private final Long sellerId;

    private final String sellerName;

    public OfferViewDto(Offer offer, Brand brand, Model model, User seller) {
        this.id = offer.getId();
        this.description = offer.getDescription();
        this.imageUrl = offer.getImageUrl();
        this.year = offer.getYear();
        this.kilometres = offer.getKilometres();
        this.price = offer.getPrice();
        this.transmissionType = offer.getTransmissionType();
        this.engineType = offer.getEngineType();
        this.created = offer.getCreated();
        this.modified = offer.getModified();
        this.brandName = brand.getName();
        this.modelName = model.getName();
        this.sellerId = seller.getId();
        this.sellerName = seller.getFirstName() + " " + seller.getLastName();
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Short getYear() {
        return year;
    }

    public Integer getKilometres() {
        return kilometres;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPriceFormat() {
        return String.format("%.2f", this.getPrice());
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public String getTransmissionTypeLabel() {
        return this.transmissionType.getLabel();
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public String getEngineTypeLabel() {
        return this.getEngineType().getLabel();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getCreatedFormatted() {
        return created.format(DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm:ss"));
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public String getModifiedFormatted() {
        return modified.format(DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm:ss"));
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String overview() {
        return this.year + " " + this.brandName + " " + this.modelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferViewDto that = (OfferViewDto) o;

        if (!id.equals(that.id)) return false;
        if (!imageUrl.equals(that.imageUrl)) return false;
        if (!created.equals(that.created)) return false;
        return modified.equals(that.modified);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + imageUrl.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + modified.hashCode();
        return result;
    }
}
