package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.TaxiRequestDTO;
import com.heytaksi.heytaksibackend.model.Taxi;

import java.util.List;
import java.util.Optional;

public interface TaxiService {
    Taxi saveTaxi(Taxi taxi);

    Optional<Taxi> getTaxiById(long id);

    List<Taxi> getTaxiList();

    Taxi updateTaxi(Taxi taxi, TaxiRequestDTO taxiRequestDTO);

    void deleteTaxi(long id);
}
