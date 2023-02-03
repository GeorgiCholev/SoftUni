package com.example.pathfinderwebapp.services;

import com.example.pathfinderwebapp.models.entities.Picture;
import com.example.pathfinderwebapp.models.entities.Route;

import java.util.List;

public interface PictureService {

    List<Picture> getPicturesForRouteId(Route route);
}
