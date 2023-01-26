package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
