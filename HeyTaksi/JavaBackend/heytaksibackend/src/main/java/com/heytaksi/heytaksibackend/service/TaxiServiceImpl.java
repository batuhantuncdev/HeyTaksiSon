package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.TaxiRequestDTO;
import com.heytaksi.heytaksibackend.model.Taxi;
import com.heytaksi.heytaksibackend.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaxiServiceImpl implements TaxiService {

    @Autowired
    private TaxiRepository taxiRepository;


    @Override
    public Taxi saveTaxi(Taxi taxi) {
        return taxiRepository.save(taxi);
    }

    @Override
    public Optional<Taxi> getTaxiById(long id) {
        return taxiRepository.findById(id);
    }

    @Override
    public List<Taxi> getTaxiList() {
        return taxiRepository.findAll();
    }

    @Override
    public Taxi updateTaxi(Taxi taxi, TaxiRequestDTO taxiRequestDTO) {
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
        taxi.setUpdatedAt(zonedIST.toLocalDateTime());
        taxi.setTaxiPlate(taxiRequestDTO.getTaxiPlate());
        taxi.setCurrentLocationX(taxiRequestDTO.getCurrentLocationX());
        taxi.setCurrentLocationY(taxiRequestDTO.getCurrentLocationY());

        return taxiRepository.save(taxi);
    }

    @Override
    public void deleteTaxi(long id) {
        final Taxi willDeleteEntity = taxiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TAXI not found with id: " + id));
        taxiRepository.delete(willDeleteEntity);
    }
}
