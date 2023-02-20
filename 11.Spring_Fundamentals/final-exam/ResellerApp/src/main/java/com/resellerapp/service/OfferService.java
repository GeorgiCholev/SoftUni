package com.resellerapp.service;

import com.resellerapp.model.dtos.OfferAddDto;
import com.resellerapp.model.dtos.OfferViewDto;
import com.resellerapp.model.dtos.OffersForUser;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.OfferRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ConditionService conditionService;

    private final UserService userService;

    public OfferService(OfferRepository offerRepository, ConditionService conditionService, UserService userService) {
        this.offerRepository = offerRepository;
        this.conditionService = conditionService;
        this.userService = userService;
    }

    @Transactional
    public void addOffer(Long userId, OfferAddDto dto) {
        Condition condition = conditionService.getConditionWithName(dto.getCondition());

        User user = userService.getUserWith(userId);

        Offer offer = new Offer(dto, condition, user);
        offerRepository.save(offer);

    }

    public Set<OfferViewDto> getAllOffersExcept(OffersForUser offersForUser) {
        Set<OfferViewDto> allOffers = offerRepository.findAll().stream()
                .map(o -> new OfferViewDto(o, o.getSeller().getUsername()))
                        .collect(Collectors.toSet());

        allOffers.removeAll(offersForUser.getOffers());
        allOffers.removeAll(offersForUser.getBoughtOffers());

        return allOffers;
    }


    @Transactional
    public void remove(Long id) {
        Offer offer = offerRepository.findById(id).orElse(null);
        if (offer == null) return;

        offerRepository.delete(offer);
    }

    public Offer find(Long id) {
        return offerRepository.findById(id).orElse(null);
    }

}
