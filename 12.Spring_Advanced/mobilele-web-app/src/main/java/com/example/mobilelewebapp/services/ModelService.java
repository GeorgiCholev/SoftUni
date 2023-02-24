package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.ModelImportDto;
import com.example.mobilelewebapp.models.dtos.ModelsByBrandName;
import com.example.mobilelewebapp.models.entities.Model;


public interface ModelService {
    void addAll(ModelImportDto[] dtos);

    boolean repositoryIsEmpty();

    ModelsByBrandName getAllModelsByBrandName();

    Model getByName(String name);
}
