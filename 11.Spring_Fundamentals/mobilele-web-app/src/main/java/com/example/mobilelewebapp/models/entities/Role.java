package com.example.mobilelewebapp.models.entities;

import com.example.mobilelewebapp.utils.enums.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
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
