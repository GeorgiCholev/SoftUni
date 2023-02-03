package com.example.pathfinderwebapp.repositories;

import com.example.pathfinderwebapp.models.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query(nativeQuery = true,
            value = "SELECT r.* " +
                    "FROM pathfinder.routes r " +
                    "LEFT JOIN pathfinder.comments c on r.id = c.route_id " +
                    "WHERE c.approved IS TRUE " +
                    "GROUP BY r.id " +
                    "ORDER BY count(c.id) DESC LIMIT 1;")
    Optional<Route> findMostCommentedRoute();

}
