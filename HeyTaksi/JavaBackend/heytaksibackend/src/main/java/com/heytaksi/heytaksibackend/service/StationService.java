package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.StationRequestDTO;
import com.heytaksi.heytaksibackend.model.Station;

import java.util.List;
import java.util.Optional;

public interface StationService {
    Station saveStation(Station station);

    Optional<Station> getStationById(long id);

    List<Station> getStationList();

    Station updateStation(Station station, StationRequestDTO stationRequestDTO);

    void deleteStation(long id);
}
