package com.example.mobilelewebapp.models.dtos;

import java.util.*;
import java.util.stream.Collectors;

public class ModelsByBrandName {

    private final Map<String, Set<ModelViewDto>> modelsByBrandName = new LinkedHashMap<>();

    public void add(String brandName, ModelViewDto model) {
        modelsByBrandName.computeIfAbsent(brandName, k -> new LinkedHashSet<>()).add(model);
    }

    public Map<String, Set<ModelViewDto>> get() {
        return modelsByBrandName;
    }


    public int modelCount() {
        return modelsByBrandName.values().stream().mapToInt(Set::size).sum();
    }
}
