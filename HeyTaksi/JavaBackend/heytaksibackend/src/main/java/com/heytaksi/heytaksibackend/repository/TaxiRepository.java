package com.heytaksi.heytaksibackend.repository;

import com.heytaksi.heytaksibackend.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {
}
