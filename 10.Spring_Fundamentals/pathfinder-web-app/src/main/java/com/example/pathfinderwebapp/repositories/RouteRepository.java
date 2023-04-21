package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
