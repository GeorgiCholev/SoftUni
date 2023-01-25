package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
