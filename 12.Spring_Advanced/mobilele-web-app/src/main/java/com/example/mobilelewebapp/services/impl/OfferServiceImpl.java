package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.models.dtos.OfferAddDto;
import com.example.mobilelewebapp.models.dtos.OfferUpdateFormDto;
import com.example.mobilelewebapp.models.dtos.OfferViewDto;
import com.example.mobilelewebapp.models.entities.Model;
import com.example.mobilelewebapp.models.entities.Offer;
import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.repositories.OfferRepository;
import com.example.mobilelewebapp.services.ModelService;
import com.example.mobilelewebapp.services.OfferService;
import com.example.mobilelewebapp.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final ModelService modelService;

    private final UserService userService;

    private final Map<Long, OfferViewDto> allOffersById = new HashMap<>();

    public OfferServiceImpl(OfferRepository offerRepository, ModelService modelService, UserService userService) {
        this.offerRepository = offerRepository;
        this.modelService = modelService;
        this.userService = userService;
    }


    @Override
    public void addOffer(OfferAddDto dto, Long sellerId) {
        User seller = this.userService.getById(sellerId);

        Model model = this.modelService.getByName(dto.getModelName());


        if (seller == null || model == null) {
            return;
        }

        this.addOffer(dto, model, seller);
    }

    @Override
    public Map<Long, OfferViewDto> getAllOffers() {
        if (this.allOffersById.size() != this.offerRepository.count()) {
            this.addAllMissing();
        }
        return this.allOffersById;
    }

    @Override
    public OfferViewDto getOfferViewById(Long id) {
        return this.getAllOffers().get(id);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        Offer offer = this.offerRepository.findById(id).orElse(null);
        if (offer == null) return;
        if (!(offer.getId().equals(userId))) return;
        this.offerRepository.delete(offer);
        this.removeFromIndex(id);
    }

    @Override
    @Transactional
    public OfferUpdateFormDto getUpdateForm(Long offerId, Long userId) {
        Offer offer = this.offerRepository.findById(offerId).orElse(null);
        if (offer == null) return null;
        if (!(offer.getSeller().getId().equals(userId))) return null;
        OfferViewDto offerViewDto = this.getOfferViewById(offerId);
        if (offerViewDto == null) return null;
        return new OfferUpdateFormDto(offerViewDto);
    }

    @Override
    @Transactional
    public void update(OfferUpdateFormDto dto) {
        Offer offer = offerRepository.findById(Long.parseLong(dto.getId())).orElse(null);
        if (offer == null) return;
        Model model = modelService.getByName(dto.getModelName());
        if (model == null) return;
        User seller = offer.getSeller();
        this.updateOffer(dto, offer, model, seller);
    }

    @Transactional
    private void addAllMissing() {
        this.offerRepository.findAll()
                .stream()
                .filter(o -> !allOffersById.containsKey(o.getId()))
                .forEach(o -> this.addToIndex(o, o.getModel(), o.getSeller()));
    }


    private void addOffer(OfferAddDto dto, Model model, User user) {
        Offer offer = new Offer(dto, user, model);
        this.offerRepository.save(offer);
        this.addToIndex(offer, model, user);
    }

    @Transactional
    private void addToIndex(Offer offer, Model model, User user) {
        this.allOffersById.put(offer.getId(), new OfferViewDto(offer, model.getBrand(), model, user));
    }

    private void removeFromIndex(Long id) {
        this.allOffersById.remove(id);
    }

    private void updateOffer(OfferUpdateFormDto dto, Offer offer, Model model, User seller) {
        offer.updateFromDto(dto, model);
        offerRepository.save(offer);
        this.addToIndex(offer, model, seller);
    }
}
