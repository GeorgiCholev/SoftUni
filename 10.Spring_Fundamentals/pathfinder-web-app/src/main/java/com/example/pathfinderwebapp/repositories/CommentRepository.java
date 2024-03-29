package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
