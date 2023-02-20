package com.resellerapp.model.dtos;

import java.util.Set;

public class OffersForUser {

    private final Set<OfferViewDto> offers;
    private final Set<OfferViewDto> boughtOffers;

    public OffersForUser(Set<OfferViewDto> offers, Set<OfferViewDto> boughtOffers) {
        this.offers = offers;
        this.boughtOffers = boughtOffers;
    }

    public Set<OfferViewDto> getOffers() {
        return offers;
    }

    public Set<OfferViewDto> getBoughtOffers() {
        return boughtOffers;
    }
}
