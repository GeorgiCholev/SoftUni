package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.OfferAddDto;
import com.example.mobilelewebapp.models.dtos.OfferUpdateFormDto;
import com.example.mobilelewebapp.models.dtos.OfferViewDto;

import java.util.Map;

public interface OfferService {
    void addOffer(OfferAddDto dto, Long userId);

    Map<Long, OfferViewDto> getAllOffers();

    OfferViewDto getOfferViewById(Long id);

    void delete(Long id, Long userId);

    OfferUpdateFormDto getUpdateForm(Long offerId, Long userId);

    void update(OfferUpdateFormDto dto);
}
