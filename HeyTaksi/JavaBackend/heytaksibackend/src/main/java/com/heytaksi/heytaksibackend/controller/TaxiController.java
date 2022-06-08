package com.heytaksi.heytaksibackend.controller;

import com.heytaksi.heytaksibackend.dto.*;
import com.heytaksi.heytaksibackend.model.Taxi;
import com.heytaksi.heytaksibackend.service.TaxiService;
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
public class TaxiController extends BaseController{

    @Autowired
    TaxiService taxiService;

    public TaxiController(ModelMapper mapper) {
        super(mapper);
    }

    @PreAuthorize(ALL_ROLE)
    @GetMapping(value = "taxi/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TaxiResponseDTO> getTaxiById(@PathVariable(value = "id") Long id){
        final Optional<Taxi> fetchedTaxi = taxiService.getTaxiById(id);
        final TaxiResponseDTO taxiResponseDTO = map(fetchedTaxi, TaxiResponseDTO.class);
        return ResponseEntity.ok(taxiResponseDTO);
    }

    @PreAuthorize(ALL_ROLE)
    @GetMapping(value = "taxi/getTaxiList", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaxiResponseDTO>> getTaxiList() {
        final List<Taxi> taxiList = taxiService.getTaxiList();
        final List<TaxiResponseDTO> responseDTOList = mapAll(taxiList, TaxiResponseDTO.class);
        return ResponseEntity.ok(responseDTOList);
    }

    @PreAuthorize(ALL_ROLE)
    @PostMapping(value = "taxi/createNewTaxi", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TaxiResponseDTO> createNewTaxi(@Validated @RequestBody TaxiRequestDTO taxiRequestDTO) {

        final Taxi taxi = map(taxiRequestDTO, Taxi.class);

        final Taxi savedTaxi = taxiService.saveTaxi(taxi);
        final TaxiResponseDTO responseDTO = map(savedTaxi, TaxiResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize(ALL_ROLE)
    @PutMapping(value = "taxi/update/{taxiId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TaxiResponseDTO> updateTaxi(
            @PathVariable(value = "taxiId") long taxiId, @Validated @RequestBody TaxiRequestDTO taxiRequestDTO) {

        final Optional<Taxi> taxi = taxiService.getTaxiById(taxiId);
        if(taxi.isPresent()){
            final Taxi updatedTaxi = taxiService.updateTaxi(taxi.get(), taxiRequestDTO);
            final TaxiResponseDTO taxiResponseDTO = map(updatedTaxi, TaxiResponseDTO.class);
            return ResponseEntity.ok(taxiResponseDTO);

        }else{
            throw new RuntimeException("Trip doesn't exist.");
        }
    }

    @PreAuthorize(ALL_ROLE)
    @DeleteMapping(value = "taxi/delete/{taxiId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteResponseDTO> deleteTaxi(@PathVariable(value = "taxiId") long taxiId) {
        taxiService.deleteTaxi(taxiId);
        DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO();
        deleteResponseDTO.setResult(true);
        deleteResponseDTO.setDescription("Taxi Deleted Successfully.");
        return ResponseEntity.ok(deleteResponseDTO);
    }


}
