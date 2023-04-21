package com.example.mobilelewebapp.models.entities;

import com.example.mobilelewebapp.models.entities.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", unique = true, nullable = false)
    private RoleType roleType;

    public Role() {
    }

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return roleType == role.roleType && super.getId().equals(role.getId());
    }

    @Override
    public int hashCode() {
        return (super.getId().hashCode() * 31) + roleType.hashCode();
    }
}
