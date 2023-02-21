package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.OfferAddDto;

public interface OfferService {
    void addOffer(OfferAddDto dto, Long userId);
}
