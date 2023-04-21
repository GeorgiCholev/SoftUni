package com.example.mobilelewebapp.models.entities.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EngineType {

    GASOLINE("Gasoline"), DIESEL("Diesel"), ELECTRIC("Electric"), HYBRID("Hybrid");

    private final String label;

    EngineType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static EngineType getEngineType(String label) {
        return Enum.valueOf(EngineType.class, label.toUpperCase());
    }

    public static List<String> labels() {
        return Arrays.stream(EngineType.values()).map(e -> e.label).collect(Collectors.toList());
    }
}
