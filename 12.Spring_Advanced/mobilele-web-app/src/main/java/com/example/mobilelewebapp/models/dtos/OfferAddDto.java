package com.example.mobilelewebapp.models.dtos;

import com.example.mobilelewebapp.models.entities.enums.EngineType;
import com.example.mobilelewebapp.models.entities.enums.TransmissionType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class OfferAddDto {

    @NotBlank
    private String modelName;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    private String engineType;

    @NotBlank
    private String transmissionType;

    @NotNull
    @Min(1900)
    private Short year;

    @NotNull
    @Positive
    private Integer kilometres;

    @NotBlank
    @Size(min = 5)
    private String description;

    @NotBlank
    private String imageUrl;

    public OfferAddDto() {
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Integer getKilometres() {
        return kilometres;
    }

    public void setKilometres(Integer kilometres) {
        this.kilometres = kilometres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
