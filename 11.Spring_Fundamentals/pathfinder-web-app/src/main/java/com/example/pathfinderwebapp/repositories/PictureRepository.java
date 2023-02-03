package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Picture;
import com.example.pathfinderwebapp.models.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findAllByRoute(Route route);
}
