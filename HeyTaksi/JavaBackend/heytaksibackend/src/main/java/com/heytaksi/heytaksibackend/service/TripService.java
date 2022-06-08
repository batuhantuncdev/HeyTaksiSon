package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.TripRequestDTO;
import com.heytaksi.heytaksibackend.model.Trip;

import java.util.List;
import java.util.Optional;


public interface TripService {
    Trip saveTrip(Trip trip);

    Optional<Trip> getTripById(long id);

    List<Trip> getTripList();

    Trip updateTrip(Trip trip, TripRequestDTO tripRequestDTO);

    void deleteTrip(long id);
}
