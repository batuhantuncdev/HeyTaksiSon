package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.StationRequestDTO;
import com.heytaksi.heytaksibackend.model.Station;
import com.heytaksi.heytaksibackend.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    StationRepository stationRepository;

    @Override
    public Station saveStation(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public Optional<Station> getStationById(long id) {
        return stationRepository.findById(id);
    }

    @Override
    public List<Station> getStationList() {
        return stationRepository.findAll();
    }

    @Override
    public Station updateStation(Station station, StationRequestDTO stationRequestDTO) {
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
        station.setUpdatedAt(zonedIST.toLocalDateTime());
        station.setTaxis(stationRequestDTO.getTaxis());
        return stationRepository.save(station);
    }

    @Override
    public void deleteStation(long id) {
        final Station willDeleteEntity = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station not found with id: " + id));
        stationRepository.delete(willDeleteEntity);
    }
}
