package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.BrandImportDto;
import com.example.mobilelewebapp.models.entities.Brand;

public interface BrandService {
    void saveAll(BrandImportDto[] dtos);

    Brand getByName(String brandName);

    boolean repositoryIsEmpty();

}
