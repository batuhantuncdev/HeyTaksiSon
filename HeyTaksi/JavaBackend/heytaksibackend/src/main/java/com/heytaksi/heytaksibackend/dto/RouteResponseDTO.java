package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouteResponseDTO {
    private Long id;
    private Long appUserId;
    private String startingPoint;
    private String endingPoint;
    private String tripDistance;
    private String tripCost;

}
