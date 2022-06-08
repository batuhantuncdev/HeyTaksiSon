package com.heytaksi.heytaksibackend.repository;

import com.heytaksi.heytaksibackend.model.AppUser;
import com.heytaksi.heytaksibackend.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
