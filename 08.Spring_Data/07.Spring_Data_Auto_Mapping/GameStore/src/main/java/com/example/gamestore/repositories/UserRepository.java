package com.example.gamestore.repositories;

import com.example.gamestore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByFullName(String fullName);

    boolean existsUserByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
