package com.example.pathfinderwebapp.services.impl;

import com.example.pathfinderwebapp.models.dtos.MostCommentedRouteHomeModel;
import com.example.pathfinderwebapp.models.entities.Picture;
import com.example.pathfinderwebapp.models.entities.Route;
import com.example.pathfinderwebapp.repositories.RouteRepository;
import com.example.pathfinderwebapp.services.PictureService;
import com.example.pathfinderwebapp.services.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.pathfinderwebapp.utils.Constants.PLACEHOLDER_PICTURE_URL;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final PictureService pictureService;

    public RouteServiceImpl(RouteRepository routeRepository, PictureService pictureService) {
        this.routeRepository = routeRepository;
        this.pictureService = pictureService;
    }

    @Override
    public MostCommentedRouteHomeModel getMostCommentedRoute() {
        if (routeRepository.count() == 0) {
            return new MostCommentedRouteHomeModel("", PLACEHOLDER_PICTURE_URL);
        }

        Optional<Route> optRoute = routeRepository.findMostCommentedRoute();

        String routeName;
        String pictureUrl;

        if (optRoute.isPresent()) {

            Route mostCommented = optRoute.get();
            routeName = mostCommented.getName();

            List<Picture> picturesForRouteId =
                    pictureService.getPicturesForRouteId(mostCommented);
            int numberOfRoutePictures = picturesForRouteId.size();

            if (numberOfRoutePictures != 0) {
                int random = ThreadLocalRandom.current().nextInt(0, numberOfRoutePictures);
                pictureUrl = picturesForRouteId.get(random).getUrl();
            } else {
                pictureUrl = PLACEHOLDER_PICTURE_URL;
            }

        } else {
            routeName = "";
            pictureUrl = PLACEHOLDER_PICTURE_URL;
        }

        return new MostCommentedRouteHomeModel(routeName, pictureUrl);
    }
}
