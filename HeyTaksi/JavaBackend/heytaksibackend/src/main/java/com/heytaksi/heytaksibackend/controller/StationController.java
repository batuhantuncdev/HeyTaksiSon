package com.heytaksi.heytaksibackend.controller;

import com.heytaksi.heytaksibackend.dto.*;
import com.heytaksi.heytaksibackend.model.Station;
import com.heytaksi.heytaksibackend.service.StationService;
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
public class StationController extends BaseController {

    @Autowired
    StationService stationService;

    public StationController(ModelMapper mapper) {
        super(mapper);
    }

    @PreAuthorize(ALL_ROLE)
    @GetMapping(value = "station/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StationResponseDTO> getStationById(@PathVariable(value = "id") Long id){
        final Optional<Station> fetchedStation = stationService.getStationById(id);
        final StationResponseDTO stationResponseDTO = map(fetchedStation, StationResponseDTO.class);
        return ResponseEntity.ok(stationResponseDTO);
    }

    @PreAuthorize(ALL_ROLE)
    @GetMapping(value = "station/getStationList", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StationResponseDTO>> getStationList() {
        final List<Station> stationList = stationService.getStationList();
        final List<StationResponseDTO> responseDTOList = mapAll(stationList, StationResponseDTO.class);
        return ResponseEntity.ok(responseDTOList);
    }

    @PreAuthorize(ALL_ROLE)
    @PostMapping(value = "station/createNewStation", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StationResponseDTO> createNewStation(@Validated @RequestBody StationRequestDTO stationRequestDTO) {

        final Station station = map(stationRequestDTO, Station.class);

        final Station savedStation = stationService.saveStation(station);
        final StationResponseDTO responseDTO = map(savedStation, StationResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize(ALL_ROLE)
    @DeleteMapping(value = "station/delete/{stationId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteResponseDTO> deleteStation(@PathVariable(value = "stationId") long tripId) {
        stationService.deleteStation(tripId);
        DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO();
        deleteResponseDTO.setResult(true);
        deleteResponseDTO.setDescription("Station Deleted Successfully.");
        return ResponseEntity.ok(deleteResponseDTO);
    }
}
