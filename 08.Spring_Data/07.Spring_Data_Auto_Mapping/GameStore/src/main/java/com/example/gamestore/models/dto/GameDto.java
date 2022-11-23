package com.example.gamestore.models.dto;

import com.example.gamestore.exceptions.InvalidStateException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.gamestore.constants.Outputs.*;

public class GameDto {

    private String title;

    private String trailer;

    private String imageThumbnail;

    private Double size;

    private BigDecimal price;

    private String description;

    private LocalDate releaseDate;

    public GameDto(String title, String trailer, String imageThumbnail, Double size,
                   BigDecimal price, String description, LocalDate releaseDate)
            throws InvalidStateException {
        setTitle(title);
        setTrailer(trailer);
        setImageThumbnail(imageThumbnail);
        setSize(size);
        setPrice(price);
        setDescription(description);
        setReleaseDate(releaseDate);
    }

    public String getTitle() {
        return title;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public Double getSize() {
        return size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setTitle(String title) throws InvalidStateException {
        if (Character.isLowerCase(title.charAt(0)) || title.length() < 3 || title.length() > 100) {
            throw new InvalidStateException(TITLE_REQUIREMENTS_FAILED);
        }

        this.title = title;
    }

    public void setTrailer(String trailer) throws InvalidStateException {
        if (trailer.length() != 11) {
            throw new InvalidStateException(TRAILER_REQUIREMENTS_FAILED);
        }
        this.trailer = trailer;
    }

    public void setImageThumbnail(String imageThumbnail) throws InvalidStateException {
        if (!imageThumbnail.startsWith("http://") && !imageThumbnail.startsWith("https://")) {
            throw new InvalidStateException(IMAGE_THUMBNAIL_REQUIREMENTS_FAILED);
        }
        this.imageThumbnail = imageThumbnail;
    }

    public void setSize(Double size) throws InvalidStateException {
        if (size <= 0.00) {
            throw new InvalidStateException(String.format(NUMBER_FIELD_REQUIREMENTS_FAILED_FORMAT, "Size"));
        }
        this.size = size;
    }

    public void setPrice(BigDecimal price) throws InvalidStateException {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidStateException(String.format(NUMBER_FIELD_REQUIREMENTS_FAILED_FORMAT, "Price"));
        }
        this.price = price;
    }

    public void setDescription(String description) throws InvalidStateException {
        if (description.length() < 20) {
            throw new InvalidStateException(DESCRIPTION_REQUIREMENTS_FAILED);
        }
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
