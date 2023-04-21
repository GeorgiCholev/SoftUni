package com.example.mobilelewebapp.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

//    @Id
//@GeneratedValue(generator = "uuid")
//@GenericGenerator(name = "uuid", strategy = "uuid")
//@Column(name = "uuid", unique = true)
//private String uuid;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected BaseEntity() {}

    public Long getId() {
        return this.id;
    }
}
