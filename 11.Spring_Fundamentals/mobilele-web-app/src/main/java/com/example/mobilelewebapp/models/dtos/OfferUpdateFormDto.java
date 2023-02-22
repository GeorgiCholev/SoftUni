package com.example.mobilelewebapp.models.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class OfferUpdateFormDto {

    private Long id;

    @NotBlank
    private String modelName;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    private String engineTypeLabel;

    @NotBlank
    private String transmissionTypeLabel;

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


    public OfferUpdateFormDto() {
    }

    public OfferUpdateFormDto(OfferViewDto offerViewDto) {
        this.id = Long.parseLong(offerViewDto.getId());
        this.modelName = offerViewDto.getModelName();
        this.price = offerViewDto.getPrice();
        this.engineTypeLabel = offerViewDto.getEngineType().getLabel();
        this.transmissionTypeLabel = offerViewDto.getTransmissionType().getLabel();
        this.year = offerViewDto.getYear();
        this.kilometres = offerViewDto.getKilometres();
        this.description = offerViewDto.getDescription();
        this.imageUrl = offerViewDto.getImageUrl();
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEngineTypeLabel() {
        return engineTypeLabel;
    }

    public void setEngineTypeLabel(String engineTypeLabel) {
        this.engineTypeLabel = engineTypeLabel;
    }

    public String getTransmissionTypeLabel() {
        return transmissionTypeLabel;
    }

    public void setTransmissionTypeLabel(String transmissionTypeLabel) {
        this.transmissionTypeLabel = transmissionTypeLabel;
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
