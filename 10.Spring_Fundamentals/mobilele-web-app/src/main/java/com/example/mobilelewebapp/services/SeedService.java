package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.dtos.BrandImportDto;
import com.example.mobilelewebapp.models.dtos.ModelImportDto;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SeedService {
    private static final Path BRANDS_JSON_PATH =
            new File("src/main/resources/json/brands-import.json").toPath();

    private static final Path MODELS_JSON_PATH =
            new File("src/main/resources/json/models-import.json").toPath();

    private final BrandService brandService;
    private final ModelService modelService;
    private final Gson gson;

    public SeedService(BrandService brandService, ModelService modelService, Gson gson) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.gson = gson;
    }

    @PostConstruct
    public void seed() throws IOException {
        if (brandService.repositoryIsEmpty()) this.seedBrands();
        if (modelService.repositoryIsEmpty()) this.seedModels();
    }

    private void seedBrands() throws IOException {
        String json = Files.readString(BRANDS_JSON_PATH);

        BrandImportDto[] dtos = this.gson.fromJson(json, BrandImportDto[].class);

        this.brandService.saveAll(dtos);
    }

    private void seedModels() throws IOException {
        String json = Files.readString(MODELS_JSON_PATH);

        ModelImportDto[] dtos = this.gson.fromJson(json, ModelImportDto[].class);

        this.modelService.addAll(dtos);
    }
}
