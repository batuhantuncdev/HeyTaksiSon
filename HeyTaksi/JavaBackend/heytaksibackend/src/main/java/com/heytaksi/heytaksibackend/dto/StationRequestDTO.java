package com.heytaksi.heytaksibackend.dto;

import com.heytaksi.heytaksibackend.model.Taxi;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StationRequestDTO {
    private Long id;

    private String stationName;
    private String stationAddress;
    private String stationCity;
    private String stationTown;
    private String stationNeighborhood;
    private Long stationPhone;
    private String stationOwnerName;
    private String stationOwnerSurname;
    private Double stationAddressLocationX;
    private Double stationAddressLocationY;
    private List<Taxi> taxis;
}
