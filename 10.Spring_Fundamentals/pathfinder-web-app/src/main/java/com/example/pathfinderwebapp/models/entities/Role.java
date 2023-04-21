package com.example.pathfinderwebapp.models.entities;

import com.example.pathfinderwebapp.models.entities.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleType type;

    public Role() {
    }

    public RoleType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return type == role.type && super.getId().equals(role.getId());
    }

    @Override
    public int hashCode() {
        int result = super.getId().hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
