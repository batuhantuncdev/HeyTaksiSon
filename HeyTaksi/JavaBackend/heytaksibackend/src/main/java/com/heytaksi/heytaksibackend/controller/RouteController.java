package com.heytaksi.heytaksibackend.controller;


import com.heytaksi.heytaksibackend.dto.*;
import com.heytaksi.heytaksibackend.model.AppUser;
import com.heytaksi.heytaksibackend.model.Route;
import com.heytaksi.heytaksibackend.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(path = "/app/")
@Controller
@Validated
public class RouteController extends BaseController {

    @Autowired
    RouteService routeService;

    public RouteController(ModelMapper mapper) {
        super(mapper);
    }

    @GetMapping(value = "route/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteResponseDTO> getRouteById(@PathVariable(value = "id") Long id){
        final Optional<Route> fetchedRoute = routeService.getRouteById(id);
        final RouteResponseDTO routeResponseDTO = map(fetchedRoute, RouteResponseDTO.class);
        return ResponseEntity.ok(routeResponseDTO);
    }

    @GetMapping(value = "route/getRouteList/{appUserId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RouteResponseDTO>> getRouteListByAppUserId(@PathVariable(value = "appUserId") Long appUserId) {
        final Optional<List<Route>> routeList = routeService.getRouteList(appUserId);
        if(routeList.isPresent()) {
            final List<RouteResponseDTO> responseDTOList = mapAll(routeList.get(), RouteResponseDTO.class);
            return ResponseEntity.ok(responseDTOList);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping(value = "route/createNewRoute", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteResponseDTO> createNewRoute(@Validated @RequestBody RouteRequestDTO routeRequestDTO) {

        final Route route = map(routeRequestDTO, Route.class);

        final Route savedRoute = routeService.saveRoute(route);
        final RouteResponseDTO responseDTO = map(savedRoute, RouteResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(value = "route/update/{routeId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteResponseDTO> updateRoute(
            @PathVariable(value = "routeId") long routeId, @Validated @RequestBody RouteRequestDTO routeRequestDTO) {

        final Optional<Route> route = routeService.getRouteById(routeId); //optional added
        if(route.isPresent()){
            route.get().setStartingPoint(routeRequestDTO.getStartingPoint());
            route.get().setEndingPoint(routeRequestDTO.getEndingPoint());
            route.get().setTripDistance(routeRequestDTO.getTripDistance());
            route.get().setTripCost(routeRequestDTO.getTripCost());

            final Route updatedRoute = routeService.updateRoute(route.get(), routeRequestDTO);
            final RouteResponseDTO routeResponseDTO = map(updatedRoute, RouteResponseDTO.class);
            return ResponseEntity.ok(routeResponseDTO);

        }else{
            throw new RuntimeException("Route doesn't exist.");
        }
    }

    @DeleteMapping(value = "route/delete/{routeId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteResponseDTO> deleteRoute(@PathVariable(value = "routeId") long routeId) {
        routeService.deleteRoute(routeId);
        DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO();
        deleteResponseDTO.setResult(true);
        deleteResponseDTO.setDescription("Route Deleted Successfully.");
        return ResponseEntity.ok(deleteResponseDTO);
    }
}

































