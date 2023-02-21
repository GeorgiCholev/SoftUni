package com.example.mobilelewebapp.services.impl;

import com.example.mobilelewebapp.models.dtos.ModelImportDto;
import com.example.mobilelewebapp.models.dtos.ModelViewDto;
import com.example.mobilelewebapp.models.dtos.ModelsByBrandName;
import com.example.mobilelewebapp.models.entities.Brand;
import com.example.mobilelewebapp.models.entities.Model;
import com.example.mobilelewebapp.repositories.ModelRepository;
import com.example.mobilelewebapp.services.BrandService;
import com.example.mobilelewebapp.services.ModelService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final BrandService brandService;

    private final ModelRepository modelRepository;

    private final ModelsByBrandName modelsByBrandName;

    public ModelServiceImpl(BrandService brandService, ModelRepository modelRepository) {
        this.brandService = brandService;
        this.modelRepository = modelRepository;
        this.modelsByBrandName = new ModelsByBrandName();
    }

    @Override
    public void addAll(ModelImportDto[] dtos) {
        List<Model> models = new ArrayList<>();

        for (ModelImportDto dto : dtos) {
            String brandName = dto.getBrandName();
            Brand brand = brandService.getByName(brandName);
            if (brand == null || dto.getName() == null || dto.getCategoryType() == null) {
                continue;
            }
            models.add(this.addModel(dto, brand));
        }

        modelRepository.saveAll(models);
    }

    @Override
    public boolean repositoryIsEmpty() {
        return modelRepository.count() == 0;
    }

    public ModelsByBrandName getAllModelsByBrandName() {
        if (modelsByBrandName.modelCount() != modelRepository.count()) {
            this.addAllToModelsByBrandName();
        }

        return this.modelsByBrandName;
    }

    @Override
    public Model getByName(String name) {
        return modelRepository.findByName(name).orElse(null);
    }

    private Model addModel(ModelImportDto dto, Brand brand) {
        Model model = new Model(dto, brand);
        this.addToModelsByBrandName(brand.getName(), model);
        return model;
    }

    private void addToModelsByBrandName(String brandName, Model model) {
        ModelViewDto dto = new ModelViewDto(model.getName(), model.getCategoryType().getLabel());
        this.modelsByBrandName.add(brandName, dto);
    }

    @Transactional
    private void addAllToModelsByBrandName() {
        this.modelRepository.findAll()
                .forEach(m -> this.addToModelsByBrandName(m.getBrand().getName(), m));
    }

}
