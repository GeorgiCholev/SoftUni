package com.example.mobilelewebapp.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

//        @Id
//    @GeneratedValue
//    @UuidGenerator
//    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected BaseEntity() {
    }

    protected long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }
}
