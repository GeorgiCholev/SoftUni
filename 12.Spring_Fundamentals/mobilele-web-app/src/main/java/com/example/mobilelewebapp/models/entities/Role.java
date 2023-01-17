package com.example.mobilelewebapp.models.entities;

import com.example.mobilelewebapp.utils.enumarations.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
