package com.example.pathfinderwebapp.models.entities;

import com.example.pathfinderwebapp.utils.enums.RoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleEnum role;

    public RoleEnum getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role other = (Role) o;

        return super.getId().equals(other.getId()) && this.role == other.role;
    }

    @Override
    public int hashCode() {
        return role.hashCode() + 31 * super.getId().hashCode();
    }
}
