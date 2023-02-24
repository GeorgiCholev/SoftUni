package com.example.mobilelewebapp.models.entities.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TransmissionType {

    MANUAL("Manual"), AUTOMATIC("Automatic");

    private final String label;

    TransmissionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static TransmissionType getTransmissionType(String label) {
        return Enum.valueOf(TransmissionType.class, label.toUpperCase());
    }

    public static List<String> labels() {
        return Arrays.stream(TransmissionType.values())
                .map(t -> t.label)
                .collect(Collectors.toList());
    }
}
