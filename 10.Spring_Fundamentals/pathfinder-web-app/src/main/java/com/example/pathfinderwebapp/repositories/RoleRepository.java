package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Role;
import com.example.pathfinderwebapp.models.entities.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByType(RoleType type);
}
