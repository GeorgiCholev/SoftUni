package com.example.shoppinglistapp.models.dtos;

import com.example.shoppinglistapp.models.entities.CategoryName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddDTO {

    @Size(min = 3, max = 20)
    @NotBlank
    private String name;

    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime before;

    @Positive
    private BigDecimal price;

    @NotNull
    private CategoryName category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getBefore() {
        return before;
    }

    public void setBefore(LocalDateTime before) {
        this.before = before;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }
}
