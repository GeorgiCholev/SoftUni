package com.resellerapp.model.dtos;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.enums.ConditionName;

public class OfferViewDto {

    private final Long id;

    private String sellerUsername;

    private final ConditionName condition;

    private final String price;

    private final String description;

    public OfferViewDto(Offer offer) {
        this.id = offer.getId();
        this.condition = offer.getCondition().getConditionName();
        this.price = String.format("%.2f", offer.getPrice());
        this.description = offer.getDescription();
    }

    public OfferViewDto(Offer offer, String sellerUsername) {
        this(offer);
        this.sellerUsername = sellerUsername;
    }

    public Long getId() {
        return id;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferViewDto that = (OfferViewDto) o;

        if (!id.equals(that.id)) return false;
        if (!condition.equals(that.condition)) return false;
        if (!price.equals(that.price)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + condition.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
