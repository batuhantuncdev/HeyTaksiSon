package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.TripRequestDTO;
import com.heytaksi.heytaksibackend.model.Trip;
import com.heytaksi.heytaksibackend.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Override
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public Optional<Trip> getTripById(long id) {
        return tripRepository.findById(id);
    }

    @Override
    public List<Trip> getTripList() {
        return tripRepository.findAll();
    }

    @Override
    public Trip updateTrip(Trip trip, TripRequestDTO tripRequestDTO) {
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
        trip.setUpdatedAt(zonedIST.toLocalDateTime());
        trip.setTripTime(tripRequestDTO.getTripTime());
        trip.setTripCost(tripRequestDTO.getTripCost());
        trip.setTripScore(tripRequestDTO.getTripScore());
        trip.setDriverId(tripRequestDTO.getDriverId());
        trip.setNotificationId(tripRequestDTO.getNotificationId());
        trip.setTaxiId(tripRequestDTO.getTaxiId());
        return tripRepository.save(trip);
    }

    @Override
    public void deleteTrip(long id) {
    final Trip willDeleteEntity = tripRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));
    tripRepository.delete(willDeleteEntity);
    }
}
