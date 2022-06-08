package com.heytaksi.heytaksibackend.repository;

import com.heytaksi.heytaksibackend.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
