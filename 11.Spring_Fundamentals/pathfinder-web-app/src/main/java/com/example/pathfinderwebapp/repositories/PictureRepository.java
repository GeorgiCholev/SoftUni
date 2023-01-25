package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
