package com.resellerapp.model.dtos;

import com.resellerapp.model.entity.enums.ConditionName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferAddDto {

    @NotBlank
    @Size(min = 2, max = 50)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private ConditionName condition;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public void setCondition(ConditionName condition) {
        this.condition = condition;
    }
}
