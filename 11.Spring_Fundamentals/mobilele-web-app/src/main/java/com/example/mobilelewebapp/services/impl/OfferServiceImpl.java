package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.models.dtos.OfferAddDto;
import com.example.mobilelewebapp.models.entities.Model;
import com.example.mobilelewebapp.models.entities.Offer;
import com.example.mobilelewebapp.models.entities.User;
import com.example.mobilelewebapp.repositories.OfferRepository;
import com.example.mobilelewebapp.services.ModelService;
import com.example.mobilelewebapp.services.OfferService;
import com.example.mobilelewebapp.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final ModelService modelService;

    private final UserService userService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelService modelService, UserService userService) {
        this.offerRepository = offerRepository;
        this.modelService = modelService;
        this.userService = userService;
    }

    @Override
    public void addOffer(OfferAddDto dto, Long userId) {
        User user = this.userService.getById(userId);

        Model model = this.modelService.getByName(dto.getModelName());

        if (user == null || model == null) {
            return;
        }

        this.addOffer(dto, user, model);
    }

    private void addOffer(OfferAddDto dto, User user, Model model) {
        this.offerRepository.save(new Offer(dto, user, model));
    }
}
