package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TripResponseDTO {
    private Long id;
    private LocalDateTime tripTime;
    private Long tripCost;
    private Long tripScore;
    private Long driverId;
    private Long customerId;
    private Long taxiId;
    private Long notificationId;
}
