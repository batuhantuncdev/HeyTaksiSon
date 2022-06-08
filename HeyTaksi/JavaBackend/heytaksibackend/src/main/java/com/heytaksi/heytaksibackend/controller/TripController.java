package com.heytaksi.heytaksibackend.controller;

import com.heytaksi.heytaksibackend.dto.*;
import com.heytaksi.heytaksibackend.model.Trip;
import com.heytaksi.heytaksibackend.service.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(path = "/api/")
@Controller
@Validated
public class TripController extends BaseController {

    @Autowired
    TripService tripService;

    public TripController(ModelMapper mapper) {
        super(mapper);
    }

    @PreAuthorize(ALL_ROLE)
    @GetMapping(value = "trip/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripResponseDTO> getTripById(@PathVariable(value = "id") Long id){
        final Optional<Trip> fetchedTrip = tripService.getTripById(id);
        final TripResponseDTO tripResponseDTO = map(fetchedTrip, TripResponseDTO.class);
        return ResponseEntity.ok(tripResponseDTO);
    }

    @PreAuthorize(ALL_ROLE)
    @GetMapping(value = "trip/getTripList", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripResponseDTO>> getTripList() {
        final List<Trip> routeList = tripService.getTripList();
        final List<TripResponseDTO> responseDTOList = mapAll(routeList, TripResponseDTO.class);
        return ResponseEntity.ok(responseDTOList);
    }

    @PreAuthorize(ALL_ROLE)
    @PostMapping(value = "trip/createNewTrip", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripResponseDTO> createNewTrip(@Validated @RequestBody TripRequestDTO tripRequestDTO) {

        final Trip trip = map(tripRequestDTO, Trip.class);

        final Trip savedTrip = tripService.saveTrip(trip);
        final TripResponseDTO responseDTO = map(savedTrip, TripResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize(ALL_ROLE)
    @PutMapping(value = "trip/update/{tripId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripResponseDTO> updateTrip(
            @PathVariable(value = "tripId") long tripId, @Validated @RequestBody TripRequestDTO tripRequestDTO) {

        final Optional<Trip> trip = tripService.getTripById(tripId); //optional added
        if(trip.isPresent()){
            trip.get().setTripScore(tripRequestDTO.getTripScore());
            trip.get().setTaxiId(tripRequestDTO.getTaxiId());
            trip.get().setTripTime(tripRequestDTO.getTripTime());
            trip.get().setTripCost(tripRequestDTO.getTripCost());

            final Trip updatedTrip = tripService.updateTrip(trip.get(), tripRequestDTO);
            final TripResponseDTO tripResponseDTO = map(updatedTrip, TripResponseDTO.class);
            return ResponseEntity.ok(tripResponseDTO);

        }else{
            throw new RuntimeException("Trip doesn't exist.");
        }
    }

    @PreAuthorize(ALL_ROLE)
    @DeleteMapping(value = "trip/delete/{tripId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteResponseDTO> deleteTrip(@PathVariable(value = "tripId") long tripId) {
        tripService.deleteTrip(tripId);
        DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO();
        deleteResponseDTO.setResult(true);
        deleteResponseDTO.setDescription("Trip Deleted Successfully.");
        return ResponseEntity.ok(deleteResponseDTO);
    }
}
