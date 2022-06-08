package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.RouteRequestDTO;
import com.heytaksi.heytaksibackend.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    Route saveRoute(Route route);

    Optional<Route> getRouteById(long id);

    Optional<List<Route>> getRouteList(long appUserId);

    Route updateRoute(Route route, RouteRequestDTO routeRequestDTO);

    void deleteRoute(long id);
}
