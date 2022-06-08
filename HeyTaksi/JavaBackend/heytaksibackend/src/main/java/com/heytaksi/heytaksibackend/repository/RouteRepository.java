package com.heytaksi.heytaksibackend.repository;

import com.heytaksi.heytaksibackend.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<List<Route>> findAllByAppUserId(Long appUserId);
}
