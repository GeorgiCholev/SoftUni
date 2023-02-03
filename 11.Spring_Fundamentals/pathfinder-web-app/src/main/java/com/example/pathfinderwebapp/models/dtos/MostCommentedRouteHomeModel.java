package com.example.pathfinderwebapp.models.dtos;


public class MostCommentedRouteHomeModel {

    private final String name;
    private final String pictureUrl;


    public MostCommentedRouteHomeModel(String name, String pictureUrl) {
        this.pictureUrl = pictureUrl;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
