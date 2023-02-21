package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.models.dtos.BrandImportDto;
import com.example.mobilelewebapp.models.entities.Brand;
import com.example.mobilelewebapp.repositories.BrandRepository;
import com.example.mobilelewebapp.services.BrandService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void saveAll(BrandImportDto[] dtos) {
        List<Brand> brands = new ArrayList<>();

        for (BrandImportDto dto : dtos) {
            if (dto.getName() == null) {
                continue;
            }
            brands.add(new Brand(dto));
        }

        brandRepository.saveAll(brands);

    }

    @Override
    public Brand getByName(String name) {
        return brandRepository.findByName(name).orElse(null);
    }


    @Override
    public boolean repositoryIsEmpty() {
        return brandRepository.count() == 0;
    }
}
