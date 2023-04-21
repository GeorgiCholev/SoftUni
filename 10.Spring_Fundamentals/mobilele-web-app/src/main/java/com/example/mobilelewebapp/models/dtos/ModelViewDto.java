package com.example.mobilelewebapp.models.dtos;

public class ModelViewDto {

    private String name;

    private String categoryType;

    public ModelViewDto(String name, String categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }

    public String getName() {
        return name;
    }

    public String getCategoryType() {
        return categoryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelViewDto that = (ModelViewDto) o;

        if (!name.equals(that.name)) return false;
        return categoryType.equals(that.categoryType);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + categoryType.hashCode();
        return result;
    }
}
