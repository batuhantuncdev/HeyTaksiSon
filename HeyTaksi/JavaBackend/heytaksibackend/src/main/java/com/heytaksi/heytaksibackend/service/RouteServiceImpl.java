package com.heytaksi.heytaksibackend.service;


import com.heytaksi.heytaksibackend.dto.RouteRequestDTO;
import com.heytaksi.heytaksibackend.model.Route;
import com.heytaksi.heytaksibackend.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private static final String EXISTING_ROUTE = "Existing route with this id.";

    @Autowired
    RouteRepository routeRepository;

    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Optional<Route> getRouteById(long id) {
       return routeRepository.findById(id);
    }

    @Override
    public Optional<List<Route>> getRouteList(long appUserId) {
       return routeRepository.findAllByAppUserId(appUserId);
    }

    @Override
    public Route updateRoute(Route route, RouteRequestDTO routeRequestDTO) {
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
        route.setUpdatedAt(zonedIST.toLocalDateTime());
        // TODO: if needed route should be updated

        return routeRepository.save(route);
    }

    @Override
    public void deleteRoute(long id) {
        final Route willDeleteEntity = routeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Route not found with id: " + id));
        routeRepository.delete(willDeleteEntity);
    }
}
