package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
