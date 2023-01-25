package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
