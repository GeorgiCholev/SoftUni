package com.example.mobilelewebapp.models.dtos;

import com.example.mobilelewebapp.models.entities.enums.CategoryType;

public class ModelImportDto {

    private String name;

    private CategoryType categoryType;

    private String brandName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
