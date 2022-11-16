package com.example.bookshopsystem.models.dto;

import com.example.bookshopsystem.models.validators.AgeRestriction;
import com.example.bookshopsystem.models.validators.EditionType;

import java.math.BigDecimal;

public interface BookInformationDTO {

    String getTitle();

    String getEditionType();

    String getAgeRestriction();

    BigDecimal getPrice();

    default String getInfo() {
        return getTitle() + " " + getEditionType().toUpperCase() + " " +
                getAgeRestriction().toUpperCase() + " " +
                String.format("%.2f", getPrice());
    }
}
