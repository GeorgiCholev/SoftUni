package com.example.pathfinderwebapp.services.impl;

import com.example.pathfinderwebapp.models.entities.Picture;
import com.example.pathfinderwebapp.models.entities.Route;
import com.example.pathfinderwebapp.repositories.PictureRepository;
import com.example.pathfinderwebapp.services.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


    @Override
    public List<Picture> getPicturesForRouteId(Route route) {
        return pictureRepository.findAllByRoute(route);
    }
}
