package com.heytaksi.heytaksibackend.dto;

import com.heytaksi.heytaksibackend.model.Station;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxiResponseDTO {
    private Long id;

    private String taxiPlate;
    private String taxiModel;
    private String taxiBrand;
    private Double currentLocationX;
    private Double currentLocationY;
    private Station station;
    private Long driverId;
}
